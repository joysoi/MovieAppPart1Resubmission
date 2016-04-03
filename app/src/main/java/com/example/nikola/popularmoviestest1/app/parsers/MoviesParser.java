package com.example.nikola.popularmoviestest1.app.parsers;

import com.example.nikola.popularmoviestest1.app.models.Results;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MoviesParser {
    public static String BASE_URL = "http://image.tmdb.org/t/p/";
    public static String SIZE = "w185";
    public static String RESULTS = "results";

    public static List<Results> parseFeed(String content) {

        try {
//           the content is an JSONObject check with debug
            JSONObject jsonObj = new JSONObject(content);
//           going inside the results JSONArray , String RESULTS = "results"
            JSONArray jsonArray = jsonObj.getJSONArray(RESULTS);

            List<Results> modelList = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject obj = jsonArray.getJSONObject(i);
                    Results model = new Results();
//           concatenating  BASE_URL = "http://image.tmdb.org/t/p/" AND SIZE = "w185" BEFORE poster_path
                    model.setPoster_path(BASE_URL + SIZE + obj.getString("poster_path"));
                    model.setTitle(obj.getString("title"));
                    model.setOverview(obj.getString("overview"));
                    model.setVote_average(obj.getString("vote_average"));
                    model.setRelease_date(obj.getString("release_date"));
                    modelList.add(model);

            }

            return modelList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


}
