package peaksoft;

import com.sun.source.doctree.DocCommentTree;
import peaksoft.config.DatabaseConnection;
import peaksoft.entity.Doctor;
import peaksoft.entity.Hospital;
import peaksoft.entity.Patient;
import peaksoft.enums.Gender;
import peaksoft.service.DoctorService;
import peaksoft.service.HospitalService;
import peaksoft.service.PatientService;
import peaksoft.service.serviceImpl.DoctorServiceImpl;
import peaksoft.service.serviceImpl.HospitalServiceImpl;
import peaksoft.service.serviceImpl.PatientServiceImpl;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        //DatabaseConnection.getEntityManagerFactory();
        Scanner scannerNum = new Scanner(System.in);
        Scanner scannerWord = new Scanner(System.in);
        DoctorService doctorService = new DoctorServiceImpl();
        PatientService patientService = new PatientServiceImpl();
        HospitalService hospitalService = new HospitalServiceImpl();


        int num;
        while (true) {
            System.out.println("""
                    Hospital                                    Doctor                          Patient
                    1-save hospital                             10-save doctor                  20-save patient
                    2-update hospital                           11-update doctor                21-update patient
                    3-delete hospital                           12-delete doctor                22-delete patient
                    4-get all doctors by hospital id            13-get average patient age      23-get doctors with most patients
                    5-get doctor and hospital by doctor id      14-                             24-get age difference with doctor
                    6-get busiest hospital
                                        
                    """);
            switch (num = scannerNum.nextInt()) {
                case 1 -> {
                    System.out.println("Input hospital name: ");
                    String name = scannerWord.nextLine();
                    System.out.println("Input hospital address: ");
                    String address = scannerWord.nextLine();
                    System.out.println(hospitalService.saveHospital(new Hospital(name, address)));
                }
                case 2 -> {
                    System.out.println("Input hospital id to update: ");
                    Long id = scannerNum.nextLong();
                    System.out.println("Input new hospital name: ");
                    String name = scannerWord.nextLine();
                    System.out.println("Input new  hospital address: ");
                    String address = scannerWord.nextLine();
                    System.out.println(hospitalService.updateHospitalById(id, new Hospital(name, address)));
                }
                case 3 -> {
                    System.out.println("Input hospital id to delete: ");
                    Long id = scannerNum.nextLong();
                    System.out.println(hospitalService.deleteHospitalById(id));
                }
                case 4 -> {
                    System.out.println("Input hospital id to get all doctors: ");
                    Long id = scannerNum.nextLong();
                    hospitalService.getAllDoctorsByHospitalId(id).forEach(System.out::println);
                }
                case 5 -> {
                    System.out.println("Input doctor id: ");
                    Long id = scannerNum.nextLong();
                    System.out.println(hospitalService.getDoctorAndHospitalByDoctorId(id));
                }
                case 6 -> System.out.println(hospitalService.getBusiestHospital());

                case 10 -> {
                    System.out.println("Input doctor name: ");
                    String name = scannerWord.nextLine();
                    System.out.println("Input doctor's gender: ");
                    String gender = scannerWord.nextLine();
                    System.out.println("Input doctor's year of birth: ");
                    int year = scannerNum.nextInt();
                    System.out.println("Input doctor's month of birth: ");
                    int month = scannerNum.nextInt();
                    System.out.println("Input doctor's day of birth: ");
                    int day = scannerNum.nextInt();
                    System.out.println("Input hospital id to save with: ");
                    Long hospitalId = scannerNum.nextLong();
                    System.out.println(doctorService.saveDoctor(hospitalId,
                            new Doctor(name, Gender.valueOf(gender.toUpperCase()),
                                    LocalDate.of(year, month, day))));
                }
                case 11 -> {
                    System.out.println("Input doctor id you want to update: ");
                    Long id = scannerNum.nextLong();
                    System.out.println("Input new doctor name: ");
                    String name = scannerWord.nextLine();
                    System.out.println("Input new doctor's gender: ");
                    String gender = scannerWord.nextLine();
                    System.out.println("Input new doctor's year of birth: ");
                    int year = scannerNum.nextInt();
                    System.out.println("Input new doctor's month of birth: ");
                    int month = scannerNum.nextInt();
                    System.out.println("Input new doctor's day of birth: ");
                    int day = scannerNum.nextInt();
                    System.out.println(doctorService.updateDoctor(id,
                            new Doctor(name, Gender.valueOf(gender.toUpperCase()),
                                    LocalDate.of(year, month, day))));
                }
                case 12 -> {
                    System.out.println("Input doctor id you want to delete: ");
                    Long id = scannerNum.nextLong();
                    System.out.println(doctorService.deleteDoctor(id));
                }
                case 13 -> {
                    System.out.println("Input doctor id: ");
                    Long id = scannerNum.nextLong();
                    System.out.println(doctorService.getAveragePatientAge(id));
                }
                case 20 -> {
                    System.out.println("Input patient name: ");
                    String name = scannerWord.nextLine();
                    System.out.println("Input gender: ");
                    String gender = scannerWord.nextLine();
                    System.out.println("Input patient's year of birth: ");
                    int year = scannerNum.nextInt();
                    System.out.println("Input patient's month of birth: ");
                    int month = scannerNum.nextInt();
                    System.out.println("Input patient's day of birth: ");
                    int day = scannerNum.nextInt();
                    System.out.println("Input doctor id to save with patient: ");
                    Long doctorId = scannerNum.nextLong();
                    System.out.println(patientService.savePatient(doctorId, new Patient(name, Gender.valueOf(gender.toUpperCase()),
                            LocalDate.of(year, month, day))));
                }
                case 21 -> {
                    System.out.println("Input patient id you want to update: ");
                    Long id = scannerNum.nextLong();
                    System.out.println("Input new patient's name: ");
                    String name = scannerWord.nextLine();
                    System.out.println("Input new patient's gender: ");
                    String gender = scannerWord.nextLine();
                    System.out.println("Input new patient's year of birth: ");
                    int year = scannerNum.nextInt();
                    System.out.println("Input new patient's month of birth: ");
                    int month = scannerNum.nextInt();
                    System.out.println("Input new patient's day of birth: ");
                    int day = scannerNum.nextInt();
                    System.out.println(patientService.updatePatient(id, new Patient(name, Gender.valueOf(gender.toUpperCase()),
                            LocalDate.of(year, month, day))));
                }
                case 22 -> {
                    System.out.println("Input patient id you want to delete: ");
                    Long id = scannerNum.nextLong();
                    System.out.println(patientService.deletePatient(id));
                }
                case 23 -> System.out.println(patientService.getDoctorsWithMostPatients());
                case 24 -> {
                    System.out.println("Input doctor id: ");
                    Long doctorId=scannerNum.nextLong();
                    System.out.println("Input patient id: ");
                    Long patientId=scannerNum.nextLong();
                    System.out.println(patientService.getAgeDifferenceWithDoctor(doctorId,patientId));


                }


            }

        }
    }
}
