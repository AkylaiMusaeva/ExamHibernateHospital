package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;
import peaksoft.enums.Gender;

import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name="doctors")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString

public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "doctor_gen")
    @SequenceGenerator(name = "doctor_gen",sequenceName = "doctor_seq",allocationSize = 1)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate dateOfBirth;


    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.DETACH,
            // CascadeType.REMOVE,
            CascadeType.MERGE})
    @ToString.Exclude
    private List<Patient> patients;


    @ManyToOne(cascade = {
            PERSIST,
            //REMOVE,
            REFRESH,
            DETACH,
            MERGE
    })
    @ToString.Exclude
    private Hospital hospital;


    public Doctor(String name, Gender gender, LocalDate dateOfBirth) {
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

}
