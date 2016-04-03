package com.example.nikola.popularmoviestest1.app.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.nikola.popularmoviestest1.app.DetailActivity;
import com.example.nikola.popularmoviestest1.app.R;
import com.example.nikola.popularmoviestest1.app.models.Results;
import com.example.nikola.popularmoviestest1.app.picasso.MyPicassoClass;

import java.util.List;

public class MyAdapter extends ArrayAdapter<Results> {

    private Context context;
    private List<Results> modelList;

    public MyAdapter(Context context, int resource, List<Results> objects) {
        super(context, resource, objects);
        this.context = context;
        this.modelList = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Results results = getItem(position);

        final String valueString = String.valueOf(results.getPoster_path());

        MyPicassoClass.addImageWithPicasso(this.getContext(), viewHolder.image, valueString);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("title", results.getTitle());
                intent.putExtra("poster_path", results.getPoster_path());
                intent.putExtra("overview", results.getOverview());
                intent.putExtra("vote_average", results.getVote_average());
                intent.putExtra("release_date", results.getRelease_date());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);
            }
        });
        return convertView;
    }


}