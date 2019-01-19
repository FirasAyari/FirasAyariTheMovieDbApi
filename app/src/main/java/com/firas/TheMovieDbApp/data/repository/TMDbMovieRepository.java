package com.firas.TheMovieDbApp.data.repository;

import java.io.IOException;
import java.util.List;

import com.firas.TheMovieDbApp.data.api.TMDbApiSync;
import com.firas.TheMovieDbApp.data.api.entity.MovieDTO;
import com.firas.TheMovieDbApp.data.api.entity.MovieListingDTO;
import com.firas.TheMovieDbApp.data.exception.FailedGettingDataException;
import com.firas.TheMovieDbApp.data.mapper.DTOModelEntitiesDataMapper;
import com.firas.TheMovieDbApp.data.repository.base.ICloudMovieRepository;
import com.firas.TheMovieDbApp.model.Movie;
import com.firas.TheMovieDbApp.model.MovieDetails;
import com.firas.TheMovieDbApp.ui.activity.SimilarMovies;

/**
 * Movies Repository, fetch list of movies and movies details
 * This class  uses an api and a mapper to retrieve the data and convert to a model entity
 * synchronously
 */
public class TMDbMovieRepository implements ICloudMovieRepository {

    protected final String TAG = "DEBUG_" + getClass().getSimpleName();

    private final TMDbApiSync api;
    private final DTOModelEntitiesDataMapper mapper;

    public TMDbMovieRepository(TMDbApiSync api,
                               DTOModelEntitiesDataMapper mapper) {
        this.api = api;
        this.mapper = mapper;
    }




    /**
     * Get top movies list synchronously
     * And convert to a model entity
     *
     * @param page api page
     * @return Entity model
     * @throws FailedGettingDataException
     */
   @Override
    public List<Movie> getTopMovies(int page) throws FailedGettingDataException {
        try {
            MovieListingDTO data = this.api.getTopMovies(page);
            return this.mapper.transform(data);
        } catch (IOException e) {
            throw new FailedGettingDataException(e);
        }
    }




    /**
     * Get movie by id synchronously
     * And convert to a model entity
     *
     * @return Entity model
     * @throws FailedGettingDataException
     */
    @Override
    public MovieDetails getMovieById(int id) throws FailedGettingDataException {
        try {
            MovieDTO data = this.api.getMovie(id);
            return this.mapper.transform(data);
        } catch (IOException e) {
            throw new FailedGettingDataException(e);
        }
    }





    /**
     * Get top movies list synchronously
     * And convert to a model entity
     *
     * @param page api page
     * @return Entity model
     * @throws FailedGettingDataException
     */
    @Override
    public List<Movie> getSimilarMovies(int page) throws FailedGettingDataException {
        try {
            MovieListingDTO data = this.api.getSimilarMovies(page);
            return this.mapper.transform(data);
        } catch (IOException e) {
            throw new FailedGettingDataException(e);
        }
    }



}
