package com.firas.TheMovieDbApp.data.api;

import java.io.IOException;
import java.util.Locale;

import com.firas.TheMovieDbApp.data.api.entity.GenreListingDTO;
import com.firas.TheMovieDbApp.data.api.entity.MovieDTO;
import com.firas.TheMovieDbApp.data.api.entity.MovieListingDTO;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Concrete implementation of TMDb Service API
 * Used to get Movie Data DTO's from HTTP Requests Synchronously
 * !!Coupled to Retrofit!!
 */
public class TMDbApiSync {

    private ITMDbServiceAPI api;
    private String language;

    public TMDbApiSync() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ITMDbServiceAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.api = retrofit.create(ITMDbServiceAPI.class);
        this.language = Locale.getDefault().getLanguage();
    }




    /**
     * Get the top movies in theaters
     * Synchronously
     *
     * @throws IOException
     */
    public MovieListingDTO getTopMovies(int page) throws IOException {
        return this.api.getMostPopular(ITMDbServiceAPI.API_KEY, page, this.language).execute()
                       .body();
    }

    public MovieListingDTO getSimilarMovies(int id) throws IOException {
        return this.api.getSimilarMovies(id,ITMDbServiceAPI.API_KEY,this.language).execute()
                .body();
    }

    /**
     * Get a movie details
     * Synchronously
     *
     * @throws IOException
     */
    public MovieDTO getMovie(int id) throws IOException {
        return this.api.getMovie(id, ITMDbServiceAPI.API_KEY, this.language)
                       .execute().body();
    }





}
