package peaksoft.repository;

import peaksoft.entity.Doctor;

public interface DoctorRepo {
    void saveDoctor(Long hospitalId, Doctor doctor);

    void updateDoctor(Long id,Doctor doctor);

    void deleteDoctor(Long id);

    double getAveragePatientAge(Long doctorId);
}
