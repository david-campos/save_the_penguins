package com.penwinners.savethepenguins.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.penwinners.savethepenguins.R;

import java.util.Objects;

public class HomeFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        Context context = getContext();
        FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        if (context  != null && fragmentManager != null) {
            SharedPreferences sharedPref = getContext().getSharedPreferences(
                    getString(R.string.prf_prefs_key), Context.MODE_PRIVATE);
            int selected = sharedPref.getInt(getString(R.string.prf_selected_penguin), 0);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment destFragment;
            if (selected == 0) {
                PenguinListFragment fragment = new PenguinListFragment();
                fragment.setParentFragment(this);
                destFragment = fragment;
            } else {
                destFragment = new MyPenguinFragment();
            }
            fragmentTransaction.replace(R.id.fragment, destFragment);
            fragmentTransaction.commit();
        }
        return root;
    }

    public void changeToMyPenguin() {
        FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment destFragment = new MyPenguinFragment();
        fragmentTransaction.replace(R.id.fragment, destFragment);
        fragmentTransaction.commit();
    }
}