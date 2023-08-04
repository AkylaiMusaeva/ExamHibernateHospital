package peaksoft.service;

import peaksoft.entity.Doctor;
import peaksoft.entity.Patient;

public interface PatientService {
    String savePatient(Long doctorId,Patient patient);

    String updatePatient(Long id, Patient patient);

    String deletePatient(Long id);

    Doctor getDoctorsWithMostPatients();


    String getAgeDifferenceWithDoctor(Long doctorId, Long patientId);
}
