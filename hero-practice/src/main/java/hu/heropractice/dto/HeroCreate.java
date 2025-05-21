package hu.heropractice.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HeroCreate {
    @NotBlank
    private String name;
    @NotBlank
    private String nationality;
    @NotNull
    private boolean canFly;
    @Min(value = 111)
    @Max(value = 114)
    private int weaponId;
}
