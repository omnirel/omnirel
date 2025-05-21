package hu.locomotiveexam2.converter;

import hu.locomotiveexam2.dto.LocomotiveCreate;
import hu.locomotiveexam2.dto.LocomotiveList;
import hu.locomotiveexam2.dto.LocomotiveRead;
import hu.locomotiveexam2.model.Locomotive;

import java.util.ArrayList;
import java.util.List;

public class LocomotiveConverter {


    public static LocomotiveRead convertModelToRead(Locomotive model) {
        LocomotiveRead read = new LocomotiveRead();
        read.setId(model.getId());
        read.setName(model.getName());
        read.setLengthCm(model.getLengthCm());
        read.setMaxSpeed(model.getMaxSpeed());
        read.setDriving(model.getDriving());
        return read;
    }

    public static Locomotive convertCreateToModel(LocomotiveCreate create) {
        Locomotive model = new Locomotive();
        model.setName(create.getName());
        model.setLengthCm(create.getLengthCm());
        model.setMaxSpeed(create.getMaxSpeed());
        model.setDriving(create.getDriving());
        return model;
    }


    public static List<LocomotiveList> convertModelToList(List<Locomotive> models) {
        List<LocomotiveList> list = new ArrayList<>();

        models.forEach( model -> {list.add(convertModelsToListItem(model));});

        return list;
    }

    private static LocomotiveList convertModelsToListItem(Locomotive model) {
        LocomotiveList list = new LocomotiveList();
        list.setName(model.getName());
        list.setDriving(model.getDriving());
        return list;
    }
}
