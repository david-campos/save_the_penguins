package com.penwinners.savethepenguins.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.penwinners.savethepenguins.R;

import java.util.Objects;

public class PenguinListFragment extends Fragment {
    private HomeFragment parentFragment;

    public PenguinListFragment() {
        // Required empty public constructor
    }

    public void setParentFragment(HomeFragment parentFragment) {
        this.parentFragment = parentFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_penguin_list, container, false);
        GridView gridView = root.findViewById(R.id.penguins_grid);
        SharedPreferences sharedPref = getContext().getSharedPreferences(
                getString(R.string.prf_prefs_key), Context.MODE_PRIVATE);
        PictureUrlsAdapter adapter = new PictureUrlsAdapter(
                new int[] {
                        R.drawable.caldera,
                        R.drawable.gus,
                        R.drawable.hansueli,
                        R.drawable.iona,
                        R.drawable.jacki,
                        R.drawable.leo,
                        R.drawable.ruwa,
                        R.drawable.susi,
                        R.drawable.waru,
                        R.drawable.youngster
                }, sharedPref, parentFragment);
        gridView.setAdapter(adapter);
        return root;
    }
}
