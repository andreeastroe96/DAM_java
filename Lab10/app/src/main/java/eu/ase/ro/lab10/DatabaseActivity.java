package eu.ase.ro.lab10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import eu.ase.ro.lab10.database.MovieDB;
import eu.ase.ro.lab10.utils.Cinema;
import eu.ase.ro.lab10.utils.DateConverter;
import eu.ase.ro.lab10.utils.Movie;
import eu.ase.ro.lab10.utils.MovieAdapter;

public class DatabaseActivity extends AppCompatActivity {
    ListView lvDatabase;
    List<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        lvDatabase = findViewById(R.id.lv_database);
        MovieAdapter movieAdapter = new MovieAdapter(getApplicationContext(),
                R.layout.lv_movies_row,
                movies,
                getLayoutInflater());
        lvDatabase.setAdapter(movieAdapter);

        Movie m1 = new Movie("Harry Potter and the Philosopher's Stone", DateConverter.fromString("16-11-2001"), 974, "Fantasy", "HBO");
        Movie m2 = new Movie("Zodiac", DateConverter.fromString("02-03-2007"), 84, "Drama", "Netflix");
        Movie m3 = new Movie("1917", DateConverter.fromString("25-12-2019"), 27, "War film", "Netflix");

        //insertMovieInDB(m1);
        //insertMovieInDB(m2);
        //insertMovieInDB(m3);

        //loadMovieList();
        deleteMovieFromDB("Zodiac");
        loadMovieList();
        //deleteMovies();
        //loadMovieList();

    }

    //method to insert a movie in the DB
    private void insertMovieInDB(Movie movie) {
        //an instance of DB is created
        MovieDB movieDB = MovieDB.getInstance(getApplicationContext());

        //intialize a Cinema object
        Random random = new SecureRandom();
        Cinema cinema = new Cinema(random.nextInt(), "CinemaCity", "AFI Cotroceni", 14);

        movie.setIdCinema(cinema.getId());

        //insert a cinema object into cinemas table
        movieDB.getCinemaDao().insert(cinema);
        //insert a movie object into movies table
        movieDB.getMovieDao().insert(movie);
    }

    private void loadMovieList() {
        //initialize the database
        MovieDB movieDB = MovieDB.getInstance(getApplicationContext());
        //call the method that was defined in the DataAccessObject for querying all the movies from DB
        List<Movie> moviesFromDb =movieDB.getMovieDao().getAllMovies();

        movies.addAll(moviesFromDb);
        MovieAdapter adapter = (MovieAdapter) lvDatabase.getAdapter();
        adapter.notifyDataSetChanged();
    }

    //method used to delete all the movies from DB
    private void deleteMovies(){
        MovieDB movieDB = MovieDB.getInstance(getApplicationContext());
        movieDB.getMovieDao().deleteAll();
        MovieAdapter adapter = (MovieAdapter) lvDatabase.getAdapter();
        adapter.notifyDataSetChanged();
    }

    //method to delete a movie from DB based on the movieTitle
    private void deleteMovieFromDB(String title) {
        MovieDB movieDB = MovieDB.getInstance(getApplicationContext());
        movieDB.getMovieDao().delete(title);
        MovieAdapter adapter = (MovieAdapter) lvDatabase.getAdapter();
        adapter.notifyDataSetChanged();
    }
}