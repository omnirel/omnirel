package hu.planepractise.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlaneUpdate {
    @NotNull
    @NotBlank
    private String airline;
    @NotNull
    @Min(value = 1)
    private Integer travelledDistance;
}
