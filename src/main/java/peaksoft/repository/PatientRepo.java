package peaksoft.repository;

import peaksoft.entity.Doctor;
import peaksoft.entity.Patient;

public interface PatientRepo {
    void savePatient(Long doctorId,Patient patient);

    void updatePatient(Long id, Patient patient);

    void deletePatient(Long id);

    Doctor getDoctorsWithMostPatients();

    int getAgeDifferenceWithDoctor(Long doctorId, Long patientId);
}
