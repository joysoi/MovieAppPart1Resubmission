package com.example.nikola.popularmoviestest1.app.picasso;

import android.content.Context;
import android.widget.ImageView;

import com.example.nikola.popularmoviestest1.app.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class MyPicassoClass {
    public static void addImageWithPicasso(final Context context, ImageView imageView, final String imageUrl) {

        Picasso.with(context)
                .load(imageUrl)
                .tag(context)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        Picasso.with(context).load(imageUrl);

                    }

                    @Override
                    public void onError() {
                        Picasso.with(context).load(R.drawable.android_error);

                    }
                });
    }
}
