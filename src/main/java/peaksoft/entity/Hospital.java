package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="hospitals")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "hospital_gen")
    @SequenceGenerator(name = "hospital_gen",sequenceName = "hospital_seq",allocationSize = 1)
    private Long id;
    private String name;
    private String address;


    @OneToMany(mappedBy = "hospital",fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Doctor> doctors;


    public Hospital(String name, String address) {
        this.name = name;
        this.address = address;
    }

}
