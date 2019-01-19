package com.firas.TheMovieDbApp.ui.fragment;

import com.firas.TheMovieDbApp.ui.fragment.common.MovieListableFragment;
import com.firas.TheMovieDbApp.ui.presenter.TopMoviesListPresenter;
import com.firas.TheMovieDbApp.ui.presenter.base.IPresenter;

/**
 * Top movies fragment
 */
public class TopMoviesListFragment extends MovieListableFragment {

    @Override
    protected IPresenter createPresenter() {
        return new TopMoviesListPresenter(this);
    }
}
