package peaksoft.service.serviceImpl;

import peaksoft.entity.Doctor;
import peaksoft.entity.Patient;
import peaksoft.repository.PatientRepo;
import peaksoft.repository.repoImpl.PatientRepoImpl;
import peaksoft.service.PatientService;

public class PatientServiceImpl implements PatientService {
    PatientRepo patientRepo=new PatientRepoImpl();

    @Override
    public String savePatient(Long doctorId,Patient patient) {
        patientRepo.savePatient(doctorId, patient);
        return patient.getName()+" is successfully saved! ";
    }

    @Override
    public String updatePatient(Long id, Patient patient) {
        patientRepo.updatePatient(id,patient);
        return "Successfully updated patient with id="+id;
    }

    @Override
    public String deletePatient(Long id) {
        patientRepo.deletePatient(id);
        return "Successfully deleted patient with id="+id;
    }

    @Override
    public Doctor getDoctorsWithMostPatients() {
        System.err.println("Doctor with the most patients is: ");
        return patientRepo.getDoctorsWithMostPatients();
    }

    @Override
    public String getAgeDifferenceWithDoctor(Long doctorId, Long patientId) {
        return "Result: "+patientRepo.getAgeDifferenceWithDoctor(doctorId,patientId);
    }
}
