package com.penwinners.savethepenguins.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.penwinners.savethepenguins.R;

public class PictureUrlsAdapter extends BaseAdapter {
    int[] ids;

    public PictureUrlsAdapter(int[] ids) {
        this.ids = ids.clone();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView dummyImageView;
        if (convertView == null) {
            dummyImageView = (ImageView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.penguin_display, parent, false);
        } else {
            dummyImageView = (ImageView) convertView;
        }
        dummyImageView.setImageResource(ids[position]);
        return dummyImageView;
    }
}
