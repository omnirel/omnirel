package org.example.tvseries.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.tvseries.dto.TvSeriesCreate;
import org.example.tvseries.dto.TvSeriesList;
import org.example.tvseries.dto.TvSeriesRead;
import org.example.tvseries.service.TvSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tvseries")
@Tag(name = "Manage TV serieses")
public class TvSeriesController {

    @Autowired
    private TvSeriesService service;

    @GetMapping("/{id}")
    @Operation(summary = "Get Tv series by id")
    public TvSeriesRead getTvSeries(@PathVariable int id) {
        return service.getTvSeries(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add a new Tv series")
    public TvSeriesRead addTvSeries(@RequestBody @Valid TvSeriesCreate create) {
        return service.addTvSeries(create);
    }

    @GetMapping("/list")
    @Operation(summary = "List every Tv series")
    public List<TvSeriesList> getTvSeriesList() {
        return service.getTvSeriesList();
    }
}
