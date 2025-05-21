package hu.locomotiveexam2.dto;

import hu.locomotiveexam2.enumeration.Driving;
import lombok.Data;

@Data
public class LocomotiveRead {
    private int id;
    private String name;
    private int lengthCm;
    private double maxSpeed;
    private Driving driving;
}
