package hu.locomotiveexam2.controller;

import hu.locomotiveexam2.dto.LocomotiveCreate;
import hu.locomotiveexam2.dto.LocomotiveList;
import hu.locomotiveexam2.dto.LocomotiveRead;
import hu.locomotiveexam2.model.Locomotive;
import hu.locomotiveexam2.service.LocomotiveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locomotive")
@Tag(name = "Manage locomotives")
public class LocomotiveController {

    @Autowired
    private LocomotiveService service;

    @PostMapping("/")
    @Operation(summary = "Create a new locomotive")
    @ResponseStatus(HttpStatus.CREATED)
    public LocomotiveRead createLocomotive(@RequestBody @Valid LocomotiveCreate create) {
        return service.createLocomotive(create);
    }

    @GetMapping("/list")
    @Operation(summary = "List locomotives")
    public List<LocomotiveList> listLocomotive() {
        return service.listLocomotive();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get locomotive by id")
    public LocomotiveRead getLocomotive(@PathVariable int id) {
        return service.getLocomotive(id);
    }
}
