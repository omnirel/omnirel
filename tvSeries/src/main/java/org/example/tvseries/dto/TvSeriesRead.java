package org.example.tvseries.dto;

import lombok.Data;
import org.example.tvseries.enumeration.Genre;

@Data
public class TvSeriesRead {

    private int id;
    private String title;
    private int premiere;
    private double imdbRating;
    private Genre genre;
}
