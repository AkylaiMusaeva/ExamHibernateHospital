package peaksoft.config;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import peaksoft.entity.Doctor;
import peaksoft.entity.Hospital;
import peaksoft.entity.Patient;

import java.util.Properties;

public class DatabaseConnection {
    public static SessionFactory getSessionFactory(){
        Properties properties=new Properties();
        properties.setProperty(Environment.DRIVER,"org.postgresql.Driver");
        properties.setProperty(Environment.URL,"jdbc:postgresql://localhost:5432/hibernateHospital");
        properties.setProperty(Environment.USER,"postgres");
        properties.setProperty(Environment.PASS,"postgres");

        properties.setProperty(Environment.HBM2DDL_AUTO,"update");
        properties.setProperty(Environment.DIALECT,"org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty(Environment.SHOW_SQL,"true");

        Configuration configuration=new Configuration();
        configuration.setProperties(properties);
        configuration.addAnnotatedClass(Hospital.class);
        configuration.addAnnotatedClass(Doctor.class);
        configuration.addAnnotatedClass(Patient.class);
        return configuration.buildSessionFactory();

    }
    public static EntityManagerFactory getEntityManagerFactory(){
        EntityManagerFactory entityManagerFactory = getSessionFactory().unwrap(EntityManagerFactory.class);
        return entityManagerFactory;
    }
}
