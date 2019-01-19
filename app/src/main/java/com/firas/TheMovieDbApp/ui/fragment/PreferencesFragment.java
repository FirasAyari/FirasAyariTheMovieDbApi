package com.firas.TheMovieDbApp.ui.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.firas.TheMovieDbApp.R;

/**
 * Preferences fragment, loading the preferences.xml
 * For Android 3.0 and higher, you should instead use this fragment instead of PreferencesActivity
 */
public class PreferencesFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }
}
