package hu.planepractise.controller;

import hu.planepractise.dto.PlaneCreate;
import hu.planepractise.dto.PlaneRead;
import hu.planepractise.dto.PlaneUpdate;
import hu.planepractise.service.PlaneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plane")
@Tag(name = "Manage planes")
public class PlaneController {

    @Autowired
    private PlaneService service;

    @GetMapping("/{id}")
    @Operation(summary = "Get plane by id")
    public PlaneRead getPlane(@PathVariable int id) {
        return service.getPlane(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add a new plane")
    public PlaneRead addPlane(@RequestBody @Valid PlaneCreate create) {
        return service.addPlane(create);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update properties of a plane")
    public PlaneRead updatePlane(@PathVariable int id, @RequestBody @Valid PlaneUpdate update) {
        return service.updatePlane(id, update);
    }
}
