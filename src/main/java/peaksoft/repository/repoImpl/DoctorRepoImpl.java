package peaksoft.repository.repoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import peaksoft.config.DatabaseConnection;
import peaksoft.entity.Doctor;
import peaksoft.entity.Hospital;
import peaksoft.entity.Patient;
import peaksoft.repository.DoctorRepo;

public class DoctorRepoImpl implements DoctorRepo {
    private final EntityManagerFactory entityManagerFactory = DatabaseConnection.getEntityManagerFactory();

    @Override
    public void saveDoctor(Long hospitalId, Doctor doctor) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Hospital hospital = entityManager.find(Hospital.class, hospitalId);
        doctor.setHospital(hospital);
        entityManager.persist(doctor);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public void updateDoctor(Long id, Doctor doctor) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Doctor doctor1 = entityManager.find(Doctor.class, id);
        doctor1.setName(doctor.getName());
        doctor1.setGender(doctor.getGender());
        doctor1.setDateOfBirth(doctor.getDateOfBirth());
        entityManager.merge(doctor1);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteDoctor(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Doctor doctor = entityManager.find(Doctor.class, id);
        doctor.setHospital(null);
        entityManager.remove(doctor);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public double getAveragePatientAge(Long doctorId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Object result = entityManager.createQuery("""        
                select avg(year (current date )-year (p.dateOfBirth))
                           from Doctor d join d.patients p on d.id = :newDoctorId 
                           """).setParameter("newDoctorId", doctorId).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return (double) result;

    }
}
