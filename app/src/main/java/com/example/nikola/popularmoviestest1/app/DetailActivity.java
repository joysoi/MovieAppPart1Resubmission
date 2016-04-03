package com.example.nikola.popularmoviestest1.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nikola.popularmoviestest1.app.picasso.MyPicassoClass;


public class DetailActivity extends AppCompatActivity {

    public static String BASE_URL = "http://image.tmdb.org/t/p/";
    public static String SIZE = "w92";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String overview = intent.getStringExtra("overview");
        String releaseDate = intent.getStringExtra("release_date");
        String voteAverage = intent.getStringExtra("vote_average");
        String thumbnailUrl = intent.getStringExtra("poster_path");

        getSupportActionBar().setTitle(title);

        TextView txtOverview = (TextView) findViewById(R.id.txt_overview);
        TextView txtReleaseDate = (TextView) findViewById(R.id.txt_release_date);
        TextView txtVoteAverage = (TextView) findViewById(R.id.txt_vote_average);
        ImageView imageThumbnail = (ImageView) findViewById(R.id.img_thumb);

        txtOverview.setText(overview);
        txtReleaseDate.setText(releaseDate);
        txtVoteAverage.setText(voteAverage);

        MyPicassoClass.addImageWithPicasso(this, imageThumbnail, thumbnailUrl);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }
}















