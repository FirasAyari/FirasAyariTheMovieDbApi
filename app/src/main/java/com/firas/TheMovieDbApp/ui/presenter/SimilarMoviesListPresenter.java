package com.firas.TheMovieDbApp.ui.presenter;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.firas.TheMovieDbApp.data.exception.FailedGettingDataException;
import com.firas.TheMovieDbApp.data.repository.base.ICloudMovieRepository;
import com.firas.TheMovieDbApp.data.repository.base.MovieRepositoryFactory;
import com.firas.TheMovieDbApp.model.Movie;
import com.firas.TheMovieDbApp.ui.contract.ILoadDataView;
import com.firas.TheMovieDbApp.ui.fragment.MovieDetailsFragment;
import com.firas.TheMovieDbApp.ui.fragment.common.MovieListableFragment;
import com.firas.TheMovieDbApp.ui.presenter.base.ListablePresenter;

import java.util.List;



public class SimilarMoviesListPresenter  extends ListablePresenter<List<Movie>> {
    boolean isFetchingMovies;
    public SimilarMoviesListPresenter(
            ILoadDataView<List<Movie>> view) {
        super(view);
    }


    @Override
    public void execute() {
        new SimilarMoviesListPresenter.LoadDataTask().execute();
    }

    @Override
    public void getMoreData(int page) {
        new SimilarMoviesListPresenter.LoadMorePagesTask().execute(page);

    }

    @Override
    public void refresh() {
        new SimilarMoviesListPresenter.LoadDataTask().execute();
    }


    private class LoadDataTask extends AsyncTask<Void, Void, List<Movie>> {


        @Override
        protected List<Movie> doInBackground(Void... params) {
            Log.d(TAG, "doInBackground: Getting movies from the api");
            ICloudMovieRepository repo = MovieRepositoryFactory.getCloudRepository();

            try {
                return repo.getSimilarMovies(MovieDetailsFragment.s);
            } catch (FailedGettingDataException e) {
                Log.d(TAG, "Failed getting data! Error: " + e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Movie> list) {
            super.onPostExecute(list);

            if(list != null)
                view.setData(list);
            else
                view.showNoConnection();
        }
    }

    /**
     * Download specific page top movie list and adds to the list in a worker thread using an AsyncTask
     * From cloud repo
     */
    private class LoadMorePagesTask extends AsyncTask<Integer, Void, List<Movie>> {

        @Override
        protected List<Movie> doInBackground(Integer... params) {
            Log.d(TAG, "doInBackground: Getting more movies from the api");
            ICloudMovieRepository repo = MovieRepositoryFactory.getCloudRepository();

            try {
                return repo.getSimilarMovies(params[0]);
            } catch (FailedGettingDataException e) {
                Log.d(TAG, "Failed getting data! Error: " + e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Movie> list) {
            super.onPostExecute(list);

            ((MovieListableFragment)view).addMoreData(list);
        }
    }
}
