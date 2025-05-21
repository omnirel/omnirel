package hu.heropractice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String nationality;
    private boolean canFly;
    @ManyToOne
    @JoinColumn(name = "weapon_id")
    private Weapon weapon;
}
