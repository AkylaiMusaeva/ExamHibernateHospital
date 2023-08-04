package peaksoft.repository.repoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import peaksoft.config.DatabaseConnection;
import peaksoft.entity.Doctor;
import peaksoft.entity.Patient;
import peaksoft.repository.PatientRepo;

import java.util.List;

public class PatientRepoImpl implements PatientRepo {
    private final EntityManagerFactory entityManagerFactory = DatabaseConnection.getEntityManagerFactory();


    @Override
    public void savePatient(Long doctorId, Patient patient) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Doctor doctor = entityManager.find(Doctor.class, doctorId);
        List<Patient> patients = doctor.getPatients();
        patients.add(patient);
        doctor.setPatients(patients);
        entityManager.persist(patient);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void updatePatient(Long id, Patient patient) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Patient patient1 = entityManager.find(Patient.class, id);
        patient1.setName(patient.getName());
        patient1.setGender(patient.getGender());
        patient1.setDateOfBirth(patient.getDateOfBirth());
        entityManager.merge(patient1);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public void deletePatient(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Patient patient = entityManager.createQuery("""
                select p from Patient p where id=?1
                """, Patient.class).setParameter(1, id).getSingleResult();
        List<Doctor> doctors = patient.getDoctors();
        for (Doctor doctor : doctors) {
            doctor.getPatients().remove(patient);
        }
        patient.setDoctors(null);
        entityManager.remove(patient);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Doctor getDoctorsWithMostPatients() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Doctor doctor = entityManager.createQuery("""
                select d from Doctor d join d.patients p group by d.id order by count(p)desc limit 1
                """, Doctor.class).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return doctor;
    }

    @Override
    public int getAgeDifferenceWithDoctor(Long doctorId, Long patientId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Object result = entityManager.createQuery("""
                        select year(p.dateOfBirth)-year(d.dateOfBirth) from Doctor d join d.patients p
                         where d.id=:newDoctorId and p.id=:newPatientId
                        """)
                .setParameter("newDoctorId", doctorId)
                .setParameter("newPatientId", patientId)
                .getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return (int) result;

    }
}
