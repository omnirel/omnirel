package org.example.tvseries.model;

import jakarta.persistence.*;
import lombok.Data;
import org.example.tvseries.enumeration.Genre;

@Entity
@Data
public class TvSeries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private int premiere;
    private double imdbRating;
    @Enumerated(EnumType.STRING)
    private Genre genre;
}
