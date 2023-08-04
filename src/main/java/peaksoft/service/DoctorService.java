package peaksoft.service;

import peaksoft.entity.Doctor;
import peaksoft.entity.Patient;

public interface DoctorService {
    String saveDoctor(Long hospitalId, Doctor doctor);

    String updateDoctor(Long id,Doctor doctor);

    String deleteDoctor(Long id);

    String getAveragePatientAge(Long id);


}
