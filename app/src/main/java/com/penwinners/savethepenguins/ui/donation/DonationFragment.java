package com.penwinners.savethepenguins.ui.donation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.penwinners.savethepenguins.R;

public class DonationFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_donation, container, false);
        v.findViewById(R.id.open_url).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bI = new Intent(Intent.ACTION_VIEW, Uri.parse("https://oceanites.org/support-oceanites/"));
                startActivity(bI);
            }
        });
        return v;
    }
}
