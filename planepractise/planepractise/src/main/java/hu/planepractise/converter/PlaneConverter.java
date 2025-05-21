package hu.planepractise.converter;

import hu.planepractise.dto.PlaneCreate;
import hu.planepractise.dto.PlaneRead;
import hu.planepractise.model.Plane;
import hu.planepractise.model.PlaneType;

public class PlaneConverter {

    public static PlaneRead convertModelToRead(Plane model) {
        PlaneRead read = new PlaneRead();
        read.setId(model.getId());
        read.setTypeName(model.getType().getName());
        read.setProductionTime(model.getProductionTime());
        read.setAirline(model.getAirline());
        read.setTravelledDistance(model.getTravelledDistance());
        read.setPassengers(model.getPassengers());
        return read;
    }

    public static Plane convertCreateToModel(PlaneCreate create, PlaneType type) {
        Plane plane = new Plane();
        plane.setType(type);
        plane.setProductionTime(create.getProductionTime());
        plane.setAirline(create.getAirline());
        plane.setTravelledDistance(create.getTravelledDistance());
        plane.setPassengers(create.getPassengers());
        return plane;
    }
}
