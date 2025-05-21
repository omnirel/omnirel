package org.example.tvseries.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import org.example.tvseries.enumeration.Genre;

@Data
public class TvSeriesCreate {
    @NotBlank
    private String title;
    @NotNull
    private int premiere;
    @DecimalMax("10.0")
    private double imdbRating;
    @NotNull
    private Genre genre;
}
