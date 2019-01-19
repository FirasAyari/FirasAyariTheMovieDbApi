package com.firas.TheMovieDbApp.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.firas.TheMovieDbApp.R;
import com.firas.TheMovieDbApp.ui.activity.base.ToolbarActivity;
import com.firas.TheMovieDbApp.ui.fragment.SimilarMoviesListFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class SimilarMovies extends ToolbarActivity implements ViewPager.OnPageChangeListener {

    protected ViewPager viewPager;
    protected int tabPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setPager();
    }

    @Override
    protected int getToolbarLayout() {
        return R.layout.activity_similar;
    }




    private void setPager() {
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(),
                FragmentPagerItems.with(this)
                        .add("Similar Movies",
                                SimilarMoviesListFragment.class)
                        .create());

        //Set the fragments pager
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(adapter);

        //Set the tabs names pager
        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.tabs);
        viewPagerTab.setViewPager(viewPager);
        viewPagerTab.setOnPageChangeListener(this);
    }






    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d(TAG, "onPageSelected: " + position);
        tabPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    @Override
    protected void onDestroy() {


        super.onDestroy();
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        int pos = savedInstanceState.getInt("tab_pos");
        //this.viewPager.setCurrentItem(pos);
    }

}
