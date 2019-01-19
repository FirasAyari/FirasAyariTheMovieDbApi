package com.firas.TheMovieDbApp.data.api;

        import com.firas.TheMovieDbApp.data.api.entity.GenreListingDTO;
        import com.firas.TheMovieDbApp.data.api.entity.MovieDTO;
        import com.firas.TheMovieDbApp.data.api.entity.MovieListingDTO;
        import retrofit.Call;
        import retrofit.http.GET;
        import retrofit.http.Path;
        import retrofit.http.Query;

/**
 * Retrieves information from online database through {@code Retrofit}.
 */
public interface ITMDbServiceAPI {
    //TODO: change this to a gradle property?
    String API_KEY = "dafea907931e328283a2937c520b76dd";
    String BASE_URL = "http://api.themoviedb.org";
    String BASE_IMAGES_URL = "http://image.tmdb.org/t/p/";
    String POSTER_SIZE = "w185";
    String BACKDROP_SIZE = "w780";

    /**
     * Retrieves a specific movie through his {@code id}
     *
     * @param id      movie id
     * @param api_key api_key used to retrieve information
     * @param lang    to retrieve in this language (ISO 639-1 code)
     * @return
     */
    @GET("/3/movie/{id}")
    Call<MovieDTO> getMovie(@Path("id") int id,
                            @Query("api_key") String api_key,
                            @Query("language") String lang);




    /**
     * Retrieves a list of movies that are the most popular on TMDb.
     *
     * @param API_KEY  api_key used to retrieve information
     * @param page     page (Minimum 1, maximum 1000)
     * @param language to retrieve in this language (ISO 639-1 code)
     * @return
     */
    @GET("/3/movie/popular")
    Call<MovieListingDTO> getMostPopular(@Query("api_key") String API_KEY,
                                         @Query("page") int page,
                                         @Query("language") String language);


    /**
     * Retrieves a list of movies that are the most popular on TMDb.
     *
     * @param API_KEY  api_key used to retrieve information
     * @param id     page (Minimum 1, maximum 1000)
     * @param language to retrieve in this language (ISO 639-1 code)
     * @return
     */
    @GET("/3/movie/{id}/similar")
    Call<MovieListingDTO> getSimilarMovies(@Path("id") int id,
                                         @Query("api_key") String API_KEY,
                                         @Query("language") String language);



}