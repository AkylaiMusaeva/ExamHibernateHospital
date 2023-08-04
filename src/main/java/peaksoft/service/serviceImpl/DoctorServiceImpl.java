package peaksoft.service.serviceImpl;

import peaksoft.entity.Doctor;
import peaksoft.entity.Patient;
import peaksoft.repository.DoctorRepo;
import peaksoft.repository.repoImpl.DoctorRepoImpl;
import peaksoft.service.DoctorService;

public class DoctorServiceImpl implements DoctorService {
    DoctorRepo doctorRepo=new DoctorRepoImpl();

    @Override
    public String saveDoctor(Long hospitalId, Doctor doctor) {
        doctorRepo.saveDoctor(hospitalId, doctor);
        return doctor.getName()+" doctor is successfully saved!";
    }

    @Override
    public String updateDoctor(Long id, Doctor doctor) {
        doctorRepo.updateDoctor(id,doctor);
        return "Doctor with id = "+id +" is successfully updated!";
    }

    @Override
    public String deleteDoctor(Long id) {
        doctorRepo.deleteDoctor(id);
        return  "Doctor with id = "+id +" is successfully deleted!";
    }

    @Override
    public String getAveragePatientAge(Long id) {
        return "Result: "+ doctorRepo.getAveragePatientAge(id);
    }

}
