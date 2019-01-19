package com.firas.TheMovieDbApp.ui.fragment.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.List;

import com.firas.TheMovieDbApp.R;
import com.firas.TheMovieDbApp.model.Movie;
import com.firas.TheMovieDbApp.ui.activity.MovieActivity;
import com.firas.TheMovieDbApp.ui.adapter.MovieRecyclerAdapter;
import com.firas.TheMovieDbApp.ui.fragment.base.LoadDataFragment;
import com.firas.TheMovieDbApp.ui.presenter.base.ListablePresenter;

/**
 * Common code for the movies list fragments
 */
public abstract class MovieListableFragment extends LoadDataFragment<List<Movie>> {

    public static final String RETRY_VIEW = "retry_view";
    private boolean loading = true;
    protected LinearLayout refreshLayout;

    protected RecyclerView listView;

    protected MovieRecyclerAdapter adapter;

    protected List<Movie> data;

    private int page;
    private boolean isFetchingMovies;
    private int currentPage = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View viewContainer = super.onCreateView(inflater, container, savedInstanceState);

        this.refreshLayout = (LinearLayout) this.mainView;

        this.listView = (RecyclerView) this.mainView.findViewById(R.id.list_view);

        this.setupListView();
        this.setListViewAdapter();

        // if no connection, retry_view retains state on rotate
        if(savedInstanceState != null && savedInstanceState.getBoolean(RETRY_VIEW)) {
            showNoConnection();
        } else {
            //first time creating the fragment? ask for data
            if(data == null) {
                Log.d(TAG, "onCreateView: execute presenter!");
                this.presenter.execute();
            } else {
                //there is already data? screen must be rotating or tab switching
                this.setData(data);
            }
        }

        return viewContainer;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //save state if retry_view is active
        outState.putBoolean(RETRY_VIEW, data == null);
    }

    /**
     * Setup the RecyclerView
     */

    protected void setupListView() {
        listView.setHasFixedSize(true);

        // use a linear layout manager
        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        listView.setLayoutManager(mLayoutManager);
        listView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int total = linearLayoutManager.getItemCount();
                int lastVisibleItemCount = linearLayoutManager.findLastVisibleItemPosition();
                Log.d(TAG, "**********"+String.valueOf(total));
                if (total > 0) {
                    //Load new page when 1 elements left for end
                    if (loading && ((total - 1) == lastVisibleItemCount)) {
                        loading = false;
                        ((ListablePresenter)presenter).getMoreData(++page);


                    }

                }





            }
        });
    }





    /**
     * Setup the adapter
     */
    protected void setListViewAdapter() {
         this.adapter = new MovieRecyclerAdapter(this.getActivity());

        adapter.setListener(new MovieRecyclerAdapter.IClickListener() {
            @Override
            public void onItemClick(Movie movie) {
                Intent i = MovieActivity.createIntent(getActivity(), movie.getId());
                getActivity().startActivity(i);
            }
        });

        this.listView.setAdapter(adapter);
    }

    public void addMoreData(List<Movie> data){
        this.showResults();
        this.data.addAll(data);
        Log.d(TAG, "addMoreData: data size: "+this.data.size());
        this.adapter.setData(this.data);
        loading = true;
    }

    @Override
    public void setData(List<Movie> data) {
        Log.d(TAG, "setData: data! size: " + data.size());
        this.showResults();
        this.page = 1;
        this.data = data;
        this.adapter.setData(data);

    }

    @Override
    protected int getLayout() {
        return R.layout.list_movies_layout;
    }

    @Override
    public void showResults() {
        this.frameLayout.removeAllViews();
        this.frameLayout.addView(refreshLayout);
     //   refreshLayout.setRefreshing(false);
    }

    @Override
    public void showError(String message) {
        super.showError(message);
       // refreshLayout.setRefreshing(false);
    }
}
