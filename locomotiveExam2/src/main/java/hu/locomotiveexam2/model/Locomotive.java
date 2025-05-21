package hu.locomotiveexam2.model;

import hu.locomotiveexam2.enumeration.Driving;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Locomotive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private int lengthCm;
    private double maxSpeed;
    @Enumerated(EnumType.STRING)
    @Column(name = "driving_id")
    private Driving driving;
}
