package com.penwinners.savethepenguins.ui.home;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.penwinners.savethepenguins.R;

public class PictureUrlsAdapter extends BaseAdapter {
    public int[] ids;
    private final SharedPreferences sharedPreferences;
    private final HomeFragment homeFragment;

    public PictureUrlsAdapter(int[] ids, SharedPreferences sharedPreferences,
                              HomeFragment fragment) {
        this.ids = ids.clone();
        this.sharedPreferences = sharedPreferences;
        this.homeFragment = fragment;
    }

    @Override
    public int getCount() {
        return ids.length;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return ids[position];
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView dummyImageView;

        if (convertView == null) {
            dummyImageView = (ImageView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.penguin_display, parent, false);
        } else {
            dummyImageView = (ImageView) convertView;
        }
        dummyImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(
                        v.getContext().getResources().getString(R.string.prf_selected_penguin),
                        ids[position]);
                editor.apply();
                editor.commit();
                if (homeFragment != null) {
                    homeFragment.changeToMyPenguin();
                }
            }
        });
        dummyImageView.setImageResource(ids[position]);
        return dummyImageView;
    }
}
