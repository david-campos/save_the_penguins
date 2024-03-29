package com.penwinners.savethepenguins.ui.notifications;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.penwinners.savethepenguins.R;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        // Add items to the recycler
        RecyclerView recyclerView = root.findViewById(R.id.recyclerview);

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        Resources res = getResources();
        String[] achievements = res.getStringArray(R.array.achievements);

        ArrayList<Achievement> achs = new ArrayList<>();
        for (String t : achievements) {
            achs.add(new Achievement(t));
        }
        AchievementAdapter mAdapter = new AchievementAdapter(achs);

        recyclerView.setAdapter(mAdapter);
        return root;
    }
}