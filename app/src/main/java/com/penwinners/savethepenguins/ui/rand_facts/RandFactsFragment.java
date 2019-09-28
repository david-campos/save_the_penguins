package com.penwinners.savethepenguins.ui.rand_facts;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextSwitcher;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.penwinners.savethepenguins.R;

public class RandFactsFragment extends Fragment {

    private RandFactsViewModel randFactsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        randFactsViewModel =
                ViewModelProviders.of(this).get(RandFactsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_rand_facts, container, false);
        final TextSwitcher textView = root.findViewById(R.id.text_dashboard);
        textView.setOutAnimation(getContext(), android.R.anim.slide_out_right);
        textView.setInAnimation(getContext(), android.R.anim.slide_in_left);
        Resources res = getResources();
        randFactsViewModel.setFacts(res.getStringArray(R.array.facts_array));
        randFactsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        final ImageButton buttonNextFact = root.findViewById(R.id.btn_next_fact);
        buttonNextFact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                randFactsViewModel.nextRandomFact();
            }
        });
        return root;
    }
}