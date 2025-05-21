package hu.heropractice.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HeroUpdate {
    @NotNull
    private Boolean canFly;
    @NotNull
    @Min(value = 111)
    @Max(value = 114)
    private Integer weaponId;
}
