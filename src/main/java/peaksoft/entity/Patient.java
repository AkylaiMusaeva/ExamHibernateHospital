package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;
import peaksoft.enums.Gender;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="patients")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "patient_gen")
    @SequenceGenerator(name = "patient_gen",sequenceName = "patient_seq",allocationSize = 1)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate dateOfBirth;

    @ManyToMany(mappedBy = "patients",cascade = {
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.DETACH,
           // CascadeType.REMOVE,
            CascadeType.MERGE
    })

    @ToString.Exclude
    private List<Doctor> doctors;





    public Patient(String name, Gender gender, LocalDate dateOfBirth) {
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }
}
