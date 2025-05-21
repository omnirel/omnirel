package org.example.tvseries.converter;

import org.example.tvseries.dto.TvSeriesCreate;
import org.example.tvseries.dto.TvSeriesList;
import org.example.tvseries.dto.TvSeriesRead;
import org.example.tvseries.model.TvSeries;

import java.util.ArrayList;
import java.util.List;

public class TvSeriesConverter {

    public static TvSeriesRead convertModelToRead(TvSeries model) {
        TvSeriesRead read = new TvSeriesRead();
        read.setId(model.getId());
        read.setTitle(model.getTitle());
        read.setPremiere(model.getPremiere());
        read.setImdbRating(model.getImdbRating());
        read.setGenre(model.getGenre());
        return read;
    }

    public static TvSeries convertCreateToModel(TvSeriesCreate create) {
        TvSeries model = new TvSeries();
        model.setTitle(create.getTitle());
        model.setPremiere(create.getPremiere());
        model.setImdbRating(create.getImdbRating());
        model.setGenre(create.getGenre());
        return model;
    }

    public static List<TvSeriesList> convertModelToList(List<TvSeries> models) {
        List<TvSeriesList> list = new ArrayList<TvSeriesList>();

        models.forEach(model -> {list.add(convertModelsToListItem(model));});

        return list;
    }

    private static TvSeriesList convertModelsToListItem(TvSeries model) {
        TvSeriesList list = new TvSeriesList();
        list.setTitle(model.getTitle());
        list.setId(model.getId());
        list.setImdbRating(model.getImdbRating());
        return list;
    }
}
