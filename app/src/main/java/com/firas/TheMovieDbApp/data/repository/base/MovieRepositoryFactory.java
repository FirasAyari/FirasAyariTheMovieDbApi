package com.firas.TheMovieDbApp.data.repository.base;


import android.content.Context;

import com.firas.TheMovieDbApp.data.api.TMDbApiSync;
import com.firas.TheMovieDbApp.data.mapper.CursorModelEntitiesDataMapper;
import com.firas.TheMovieDbApp.data.mapper.DTOModelEntitiesDataMapper;
import com.firas.TheMovieDbApp.data.repository.LocalMovieRepository;
import com.firas.TheMovieDbApp.data.repository.TMDbMovieRepository;

/**
 * This class knows how to construct each type of the repositories
 */
public class MovieRepositoryFactory {

    /**
     * Create and return an instance of a TMDbMovieRepository
     * @return
     */
    public static ICloudMovieRepository getCloudRepository() {
        return new TMDbMovieRepository(new TMDbApiSync(), new DTOModelEntitiesDataMapper());
    }

    /**
     * Create and return an instance of a LocalMovieRepository
     * @param ctx
     * @return
     */
    public static ILocalMovieRepository getLocalRepository(Context ctx) {
        return new LocalMovieRepository(ctx, new CursorModelEntitiesDataMapper());
    }

}
