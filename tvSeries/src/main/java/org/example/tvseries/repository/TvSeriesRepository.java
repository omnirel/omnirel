package org.example.tvseries.repository;

import org.example.tvseries.model.TvSeries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TvSeriesRepository extends JpaRepository<TvSeries, Integer> {
}
