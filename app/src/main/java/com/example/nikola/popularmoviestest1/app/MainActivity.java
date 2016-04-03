package com.example.nikola.popularmoviestest1.app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import com.example.nikola.popularmoviestest1.app.adapters.MyAdapter;
import com.example.nikola.popularmoviestest1.app.http.HttpGetManager;
import com.example.nikola.popularmoviestest1.app.models.Results;
import com.example.nikola.popularmoviestest1.app.parsers.MoviesParser;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TOP = "top_rated?api_key=";
    private static final String POPULAR = "popular?api_key=";
    private static final String URL = "http://api.themoviedb.org/3/movie/";
    private GridView gridView;
    private List<Results> modelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;

        gridView = (GridView) findViewById(R.id.gridview);

        displayContent(POPULAR);
        getSupportActionBar().setTitle(R.string.action_popular);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_popular) {
            displayContent(POPULAR);
            getSupportActionBar().setTitle(R.string.action_popular);
        } else {
            displayContent(TOP);
            getSupportActionBar().setTitle(R.string.action_top);
        }

        return super.onOptionsItemSelected(item);
    }


    private class MyTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            return HttpGetManager.getData(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            modelList = MoviesParser.parseFeed(result);
            updateDisplay();
        }

    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


    protected void displayContent(String sort) {
        if (isOnline()) {
            String APPID_PARAM = (BuildConfig.TMDB_API_KEY);
            requestData(URL + sort + APPID_PARAM);
        } else {
            Toast.makeText(this, "Network isn't available", Toast.LENGTH_LONG).show();
        }
    }


    protected void updateDisplay() {
        MyAdapter adapter = new MyAdapter(this, R.layout.list_view_item, modelList);
        gridView.setAdapter(adapter);
    }


    private void requestData(String uri) {
        new MyTask().execute(uri);

    }
}
