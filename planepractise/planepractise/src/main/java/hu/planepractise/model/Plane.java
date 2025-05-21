package hu.planepractise.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private PlaneType type;
    private Date productionTime;
    private String airline;
    private int travelledDistance;
    private int passengers;
}
