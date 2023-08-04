package peaksoft.service.serviceImpl;

import peaksoft.entity.Doctor;
import peaksoft.entity.Hospital;
import peaksoft.repository.HospitalRepo;
import peaksoft.repository.repoImpl.HospitalRepoImpl;
import peaksoft.service.HospitalService;

import java.util.List;
import java.util.Map;

public class HospitalServiceImpl implements HospitalService {
    HospitalRepo hospitalRepo=new HospitalRepoImpl();

    @Override
    public String saveHospital(Hospital hospital) {
        hospitalRepo.saveHospital(hospital);
        return hospital.getName()+" is successfully saved!";
    }

    @Override
    public String updateHospitalById(Long id, Hospital hospital) {
        hospitalRepo.updateHospitalById(id,hospital);
        return "Hospital with id="+id+" is successfully updated!";
    }

    @Override
    public String deleteHospitalById(Long id) {
        hospitalRepo.deleteHospitalById(id);
        return "Hospital with id="+id+" is successfully deleted!" ;
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        return hospitalRepo.getAllDoctorsByHospitalId(id);
    }

    @Override
    public Map<Doctor, Hospital> getDoctorAndHospitalByDoctorId(Long id) {
        return hospitalRepo.getDoctorAndHospitalByDoctorId(id);
    }

    @Override
    public Hospital getBusiestHospital() {
        return hospitalRepo.getBusiestHospital();
    }
}
