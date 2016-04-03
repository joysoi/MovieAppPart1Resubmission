package com.example.nikola.popularmoviestest1.app.adapters;

import android.view.View;
import android.widget.ImageView;

import com.example.nikola.popularmoviestest1.app.R;

public class ViewHolder {

    public ImageView image;

    public ViewHolder(View convertView) {

        image = (ImageView) convertView.findViewById(R.id.img);

    }
}
