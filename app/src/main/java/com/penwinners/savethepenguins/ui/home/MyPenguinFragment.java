package com.penwinners.savethepenguins.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.penwinners.savethepenguins.R;

import java.util.Locale;

public class MyPenguinFragment extends Fragment {
    public MyPenguinFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root = inflater.inflate(R.layout.fragment_my_penguin, container, false);
        SharedPreferences sharedPref = getContext().getSharedPreferences(
                getString(R.string.prf_prefs_key), Context.MODE_PRIVATE);
        int selected = sharedPref.getInt(getString(R.string.prf_selected_penguin), 0);
        ((ImageView) root.findViewById(R.id.penguin_pic)).setImageResource(selected);
        int progress = sharedPref.getInt(getString(R.string.prf_player_xp), 50);
        ((ProgressBar) root.findViewById(R.id.xp_bar)).setProgress(progress);
        ((TextView) root.findViewById(R.id.percentage)).setText(
                String.format(Locale.ENGLISH, "%d%%", progress));
        String name = sharedPref.getString(getString(R.string.prf_penguin_name), null);
        final TextInputEditText textInputEditText = root.findViewById(R.id.name_input);
        final TextView penguinName = root.findViewById(R.id.penguin_name);
        if (name == null) {
            penguinName.setVisibility(View.INVISIBLE);
            root.findViewById(R.id.btn_save_name).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = textInputEditText.getText().toString();
                    if (name != null) {
                        SharedPreferences sharedPreferences = getContext().getSharedPreferences(
                                getString(R.string.prf_prefs_key), Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(
                                v.getContext().getResources().getString(R.string.prf_penguin_name),
                                name);
                        editor.apply();
                        editor.commit();
                        root.findViewById(R.id.name_input_layout).setVisibility(View.INVISIBLE);
                        penguinName.setText(name);
                        penguinName.setVisibility(View.VISIBLE);
                    }
                }
            });
        } else {
            penguinName.setText(name);
            root.findViewById(R.id.name_input_layout).setVisibility(View.INVISIBLE);
        }
        return root;
    }
}
