package hu.locomotiveexam2.dto;

import hu.locomotiveexam2.enumeration.Driving;
import lombok.Data;

@Data
public class LocomotiveList {
    private String name;
    private Driving driving;
}
