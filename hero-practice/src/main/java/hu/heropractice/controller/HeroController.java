package hu.heropractice.controller;

import hu.heropractice.dto.HeroCreate;
import hu.heropractice.dto.HeroRead;
import hu.heropractice.dto.HeroUpdate;
import hu.heropractice.service.HeroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hero")
@Tag(name = "Manage heroes")
public class HeroController {

    @Autowired
    private HeroService service;

    @GetMapping("/{id}")
    @Operation(summary = "Get hero by id")
    public HeroRead getHero(@PathVariable int id) {
        return service.getHero(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add a new hero")
    public HeroRead addHero(@RequestBody @Valid HeroCreate create) {
        return service.addHero(create);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update properties of a hero")
    public HeroRead updateHero(@PathVariable int id, @RequestBody @Valid HeroUpdate update) {
        return service.updateHero(id, update);
    }
}
