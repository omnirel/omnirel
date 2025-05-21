package hu.planepractise.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PlaneRead {
    private int id;
    private String typeName;
    private Date productionTime;
    private String airline;
    private int travelledDistance;
    private int passengers;
}
