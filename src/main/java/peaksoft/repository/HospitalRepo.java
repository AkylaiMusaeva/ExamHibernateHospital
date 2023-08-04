package peaksoft.repository;

import peaksoft.entity.Doctor;
import peaksoft.entity.Hospital;

import java.util.List;
import java.util.Map;

public interface HospitalRepo {
    void saveHospital(Hospital hospital);

    void updateHospitalById(Long id, Hospital hospital);

    void deleteHospitalById(Long id);

    List<Doctor> getAllDoctorsByHospitalId(Long id);

    Map<Doctor, Hospital> getDoctorAndHospitalByDoctorId(Long id);

    Hospital getBusiestHospital();
}
