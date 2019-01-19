package com.firas.TheMovieDbApp.ui.fragment;

import com.firas.TheMovieDbApp.ui.fragment.common.MovieListableFragment;
import com.firas.TheMovieDbApp.ui.presenter.SimilarMoviesListPresenter;
import com.firas.TheMovieDbApp.ui.presenter.base.IPresenter;

public class SimilarMoviesListFragment  extends MovieListableFragment {

    @Override
    protected IPresenter createPresenter() {
        return new SimilarMoviesListPresenter(this);
    }
}
