package hu.heropractice.dto;

import lombok.Data;

@Data
public class HeroRead {
    private int id;
    private String name;
    private String nationality;
    private boolean canFly;
    private String weaponName;
}
