package peaksoft.service;

import peaksoft.entity.Doctor;
import peaksoft.entity.Hospital;

import java.util.List;
import java.util.Map;

public interface HospitalService {
    String saveHospital(Hospital hospital);

    String updateHospitalById(Long id, Hospital hospital);

    String deleteHospitalById(Long id);

    List<Doctor> getAllDoctorsByHospitalId(Long id);

    Map<Doctor,Hospital> getDoctorAndHospitalByDoctorId(Long id);

    Hospital getBusiestHospital();
}
