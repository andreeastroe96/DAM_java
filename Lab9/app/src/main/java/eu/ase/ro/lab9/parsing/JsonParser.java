package eu.ase.ro.lab9.parsing;

import static eu.ase.ro.lab9.utils.Constants.KEY_JSON_ARRAY;
import static eu.ase.ro.lab9.utils.Constants.KEY_MOVIE_MOVIE_GENRE;
import static eu.ase.ro.lab9.utils.Constants.KEY_MOVIE_PLATFORM;
import static eu.ase.ro.lab9.utils.Constants.KEY_MOVIE_PROFIT;
import static eu.ase.ro.lab9.utils.Constants.KEY_MOVIE_RELEASE_DATE;
import static eu.ase.ro.lab9.utils.Constants.KEY_MOVIE_TITLE;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import eu.ase.ro.lab9.utils.DateConverter;
import eu.ase.ro.lab9.utils.Movie;

public class JsonParser {
    private final List<Movie> movieList = new ArrayList<>();

    public List<Movie> getMoviesFromJson(String json) {
        if (json != null) {
            try {
                //JSONObject -> Java class used for manipulating a JSON object
                JSONObject jsonObject = new JSONObject(json);
                //JSONArray -> Java class used to get an array from a JSON object
                JSONArray moviesArray = jsonObject.getJSONArray(KEY_JSON_ARRAY);
                //iterating though a JSONArray object
                //the foreach is not supported in case of JSONArray
                for (int i = 0; i < moviesArray.length(); i++) {
                    //getting the JSONObject from position i
                    JSONObject jsonMovie = moviesArray.getJSONObject(i);
                    //getting the String from a json
                    String title = jsonMovie.getString(KEY_MOVIE_TITLE);
                    String releaseDate = jsonMovie.getString(KEY_MOVIE_RELEASE_DATE);
                    //getting an int from a json
                    int profit = jsonMovie.getInt(KEY_MOVIE_PROFIT);
                    String movieGenre = jsonMovie.getString(KEY_MOVIE_MOVIE_GENRE);
                    String platform = jsonMovie.getString(KEY_MOVIE_PLATFORM);

                    Movie movie = new Movie(title, DateConverter.fromString(releaseDate),
                            profit, movieGenre, platform);

                    movieList.add(movie);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("parseJson", "JSON object is null");
        }

        return movieList;
    }
}
