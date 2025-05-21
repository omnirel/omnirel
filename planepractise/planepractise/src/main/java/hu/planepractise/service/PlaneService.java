package hu.planepractise.service;

import hu.planepractise.converter.PlaneConverter;
import hu.planepractise.dto.PlaneCreate;
import hu.planepractise.dto.PlaneRead;
import hu.planepractise.dto.PlaneUpdate;
import hu.planepractise.model.Plane;
import hu.planepractise.model.PlaneType;
import hu.planepractise.repository.PlaneRepository;
import hu.planepractise.repository.PlaneTypeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaneService {

    @Autowired
    private PlaneRepository repository;

    @Autowired
    private PlaneTypeRepository typeRepository;

    public PlaneRead getPlane(int id) {
        if (!repository.existsById(id))
            throw new RuntimeException("Plane with id " + id + " does not exist");
        return PlaneConverter.convertModelToRead(repository.getReferenceById(id));
    }

    public PlaneRead addPlane(@Valid PlaneCreate create) {
        PlaneType type = typeRepository.getReferenceById(create.getTypeId());
        if (!typeRepository.existsById(type.getId()))
            throw new RuntimeException("Plane type with id " + create.getTypeId() + " does not exist");
        Plane createdPlane = PlaneConverter.convertCreateToModel(create, type);
        return PlaneConverter.convertModelToRead(repository.save(createdPlane));
    }


    public PlaneRead updatePlane(int id, @Valid PlaneUpdate update) {
        if (!repository.existsById(id))
            throw new RuntimeException("Plane with id " + id + " does not exist");
        Plane updatingPlane = repository.getReferenceById(id);
        if (update.getAirline() != null){
            updatingPlane.setAirline(update.getAirline());
        }
        if (update.getTravelledDistance() != null){
            updatingPlane.setTravelledDistance(update.getTravelledDistance());
        }
        Plane updatedPlane = repository.save(updatingPlane);
        return PlaneConverter.convertModelToRead(updatedPlane);
    }
}
