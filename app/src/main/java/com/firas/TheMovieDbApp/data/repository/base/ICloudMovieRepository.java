package com.firas.TheMovieDbApp.data.repository.base;

import java.util.List;

import com.firas.TheMovieDbApp.data.exception.FailedGettingDataException;
import com.firas.TheMovieDbApp.model.Movie;
import com.firas.TheMovieDbApp.model.MovieDetails;

/**
 * This interface defines the contract with the data layer
 * and the presentation layer for requesting data synchronously
 */
public interface ICloudMovieRepository {


    /**
     * Get top movies list synchronously
     * And convert to a model entity
     *
     * @param page api page
     * @return Entity model
     * @throws FailedGettingDataException
     */
    List<Movie> getTopMovies(int page) throws FailedGettingDataException;


    /**
     * Get movie by id synchronously
     * And convert to a model entity
     *
     * @param id
     * @return Entity model
     * @throws FailedGettingDataException
     */
    MovieDetails getMovieById(int id) throws FailedGettingDataException;
    /**
     * Get movie by id synchronously
     * And convert to a model entity
     *
     * @param id
     * @return Entity model
     * @throws FailedGettingDataException
     */
    List<Movie> getSimilarMovies(int id) throws FailedGettingDataException;

    /**
     * Get movie credits synchronously
     * And convert to a model entity
     *
     * @param id
     * @return
     * @throws FailedGettingDataException
     */

}
