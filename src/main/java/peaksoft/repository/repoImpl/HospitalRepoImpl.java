package peaksoft.repository.repoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import peaksoft.config.DatabaseConnection;
import peaksoft.entity.Doctor;
import peaksoft.entity.Hospital;
import peaksoft.repository.HospitalRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HospitalRepoImpl implements HospitalRepo {
    private final EntityManagerFactory entityManagerFactory = DatabaseConnection.getEntityManagerFactory();

    @Override
    public void saveHospital(Hospital hospital) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(hospital);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void updateHospitalById(Long id, Hospital hospital) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Hospital hospital1 = entityManager.find(Hospital.class, id);
        hospital1.setName(hospital.getName());
        hospital1.setAddress(hospital.getAddress());
        entityManager.persist(hospital1);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteHospitalById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Hospital hospital = entityManager.find(Hospital.class, id);
        hospital.setDoctors(null);
        entityManager.remove(hospital);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Hospital hospital = entityManager.find(Hospital.class, id);
        List<Doctor> doctors = hospital.getDoctors();
        entityManager.getTransaction().commit();
        entityManager.close();
        return doctors;

    }

    @Override
    public Map<Doctor, Hospital> getDoctorAndHospitalByDoctorId(Long id) {
        Map<Doctor, Hospital> map = new HashMap<>();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Doctor doctor = entityManager.find(Doctor.class, id);
        Hospital hospital = doctor.getHospital();
        map.put(doctor, hospital);
        entityManager.getTransaction().commit();
        entityManager.close();
        return map;
    }

    @Override
    public Hospital getBusiestHospital() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Hospital hospital = entityManager.createQuery("""
                select h from Doctor d join Hospital h on d.hospital.id=h.id
                group by h.id order by count(d) desc limit 1 
                                
                """, Hospital.class).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return hospital;
    }
}
