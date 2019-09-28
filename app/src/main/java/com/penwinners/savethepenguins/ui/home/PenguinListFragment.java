package com.penwinners.savethepenguins.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.penwinners.savethepenguins.R;

public class PenguinListFragment extends Fragment {
    public PenguinListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_penguin_list, container, false);
        GridView gridView = root.findViewById(R.id.penguins_grid);
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
                });
        gridView.setAdapter(adapter);
        return root;
    }
}
