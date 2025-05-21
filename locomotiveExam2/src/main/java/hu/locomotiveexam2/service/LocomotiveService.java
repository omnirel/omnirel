package hu.locomotiveexam2.service;

import hu.locomotiveexam2.converter.LocomotiveConverter;
import hu.locomotiveexam2.dto.LocomotiveCreate;
import hu.locomotiveexam2.dto.LocomotiveList;
import hu.locomotiveexam2.dto.LocomotiveRead;
import hu.locomotiveexam2.model.Locomotive;
import hu.locomotiveexam2.repository.LocomotiveRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocomotiveService {

    @Autowired
    private LocomotiveRepository repository;

    public LocomotiveRead createLocomotive(@Valid LocomotiveCreate create) {
        return LocomotiveConverter.convertModelToRead(repository.save(LocomotiveConverter.convertCreateToModel(create)));
    }

    public List<LocomotiveList> listLocomotive() {
        return LocomotiveConverter.convertModelToList(repository.findAll());
    }

    public LocomotiveRead getLocomotive(int id) {
        if (!repository.existsById(id))
            throw new RuntimeException("Locomotive with id " + id + " does not exist");
        return LocomotiveConverter.convertModelToRead(repository.getReferenceById(id));
    }
}
