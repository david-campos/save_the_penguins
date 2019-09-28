package com.penwinners.savethepenguins.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.penwinners.savethepenguins.R;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {}
        });

        Context context = getContext();

        if (context  != null) {
            SharedPreferences sharedPref = getContext().getSharedPreferences(
                    getString(R.string.prf_prefs_key), Context.MODE_PRIVATE);
            int selected = sharedPref.getInt(getString(R.string.prf_selected_penguin), 0);
            if (selected != 0) {

            } else {

            }
        }
        return root;
    }
}