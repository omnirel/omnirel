package hu.locomotiveexam2.dto;

import hu.locomotiveexam2.enumeration.Driving;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LocomotiveCreate {
    @NotNull
    private String name;
    @NotNull
    @Size(min = 1, max = 100000)
    private int lengthCm;
    @NotNull
    @Size(min = 1, max = 250)
    private double maxSpeed;
    @NotNull
    private Driving driving;
}
