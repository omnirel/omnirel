package org.example.tvseries.service;

import jakarta.validation.Valid;
import org.example.tvseries.converter.TvSeriesConverter;
import org.example.tvseries.dto.TvSeriesCreate;
import org.example.tvseries.dto.TvSeriesList;
import org.example.tvseries.dto.TvSeriesRead;
import org.example.tvseries.repository.TvSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TvSeriesService {

    @Autowired
    private TvSeriesRepository repository;

    public TvSeriesRead getTvSeries(int id) {
        if (!repository.existsById(id))
            throw new RuntimeException("Tv series not found");
        return TvSeriesConverter.convertModelToRead(repository.getReferenceById(id));
    }

    public TvSeriesRead addTvSeries(@Valid TvSeriesCreate create) {
        return TvSeriesConverter.convertModelToRead(repository.save(TvSeriesConverter.convertCreateToModel(create)));
    }

    public List<TvSeriesList> getTvSeriesList() {
        return TvSeriesConverter.convertModelToList(repository.findAll());
    }
}
