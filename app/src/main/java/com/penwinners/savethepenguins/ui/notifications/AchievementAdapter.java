package com.penwinners.savethepenguins.ui.notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.penwinners.savethepenguins.R;

import java.util.ArrayList;
import java.util.Random;

public class AchievementAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
private ArrayList<Achievement> mDataset;
private static final String[] DONE = new String[] {
        "Great! You're saving the penguins!",
        "Well done! Your penguin is happier now!",
        "Wonderful! Penguins love you!",
        "Keep going! You are doing very well!"
};
private static final String[] MISSED = new String[] {
        "Oh! You'll do better next time!",
        "Don't worry, next time you can do better!",
        "Keep trying, the penguins need you!"
};
// Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder
class MyViewHolder extends RecyclerView.ViewHolder {
    // each data item is just a string in this case
    public TextView textView;
    public int bad;
    public int good;
    public MyViewHolder(View v) {
        super(v);
        textView = v.findViewById(R.id.description);
        v.findViewById(R.id.button_fail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(v, MISSED[new Random().nextInt(MISSED.length)], Snackbar.LENGTH_LONG);
                snackbar.show();
                SharedPreferences sharedPref = v.getContext().getSharedPreferences(
                        v.getContext().getResources().getString(R.string.prf_prefs_key), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                int xp = sharedPref.getInt(v.getContext().getResources().getString(R.string.prf_player_xp), 50);
                xp -= bad;
                if (xp < 0) xp = 0; if (xp > 100) xp = 100;
                editor.putInt(v.getContext().getResources().getString(R.string.prf_player_xp), xp);
                editor.apply();
                editor.commit();
            }
        });

        v.findViewById(R.id.button_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar =  Snackbar.make(v, DONE[new Random().nextInt(DONE.length)], Snackbar.LENGTH_LONG);
                snackbar.getView().setBackgroundColor(v.getResources().getColor(R.color.colorAccent));
                snackbar.show();
                SharedPreferences sharedPref = v.getContext().getSharedPreferences(
                        v.getContext().getResources().getString(R.string.prf_prefs_key), Context.MODE_PRIVATE);
                int xp = sharedPref.getInt(v.getContext().getResources().getString(R.string.prf_player_xp), 50);
                SharedPreferences.Editor editor = sharedPref.edit();
                xp += good;
                if (xp < 0) xp = 0; if (xp > 100) xp = 100;
                editor.putInt(v.getContext().getResources().getString(R.string.prf_player_xp), xp);
                editor.apply();
                editor.commit();
            }
        });
    }
}

class TitleViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;

    public TitleViewHolder(View v) {
        super(v);
        textView = v.findViewById(R.id.description);
    }
}

    // Provide a suitable constructor (depends on the kind of dataset)
    public AchievementAdapter(ArrayList<Achievement> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        // create a new view
        if (viewType == 1){
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.achievement_1, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;}
        else {
            LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.achievement_2, parent, false);
            TitleViewHolder vh = new TitleViewHolder(v);
            return vh;
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        if (holder.getItemViewType() == 1) {
            MyViewHolder vh = (MyViewHolder) holder;
            vh.textView.setText(mDataset.get(position).text);
            vh.bad = mDataset.get(position).negative_weight;
            vh.good = mDataset.get(position).positive_weight;
        } else {
            TitleViewHolder vh = (TitleViewHolder) holder;
            vh.textView.setText(mDataset.get(position).text);
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mDataset.get(position).is_title ? 2 : 1;
    }
}
