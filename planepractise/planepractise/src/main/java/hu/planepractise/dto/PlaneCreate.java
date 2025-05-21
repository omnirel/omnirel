package hu.planepractise.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class PlaneCreate {
    @Min(value = 11)
    @Max(value = 13)
    private int typeId;
    @PastOrPresent
    private Date productionTime;
    @NotBlank
    private String airline;
    @Min(value = 1)
    private int travelledDistance;
    @Min(value = 1)
    private int passengers;
}
