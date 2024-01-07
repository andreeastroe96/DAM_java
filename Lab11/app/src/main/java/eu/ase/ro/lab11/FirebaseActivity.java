package eu.ase.ro.lab11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import eu.ase.ro.lab11.firebase.FirebaseController;
import eu.ase.ro.lab11.utils.DateConverter;
import eu.ase.ro.lab11.utils.Movie;
import eu.ase.ro.lab11.utils.MovieAdapter;

public class FirebaseActivity extends AppCompatActivity {
    ListView lvFirebase;
    List<Movie> movieList = new ArrayList<>();
    FirebaseController firebaseController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        initComponent();
        Movie m1 = new Movie("Harry Potter and the Philosopher's Stone", DateConverter.fromString("16-11-2001"), 974, "Fantasy", "HBO");
        Movie m2 = new Movie("Zodiac", DateConverter.fromString("02-03-2007"), 84, "Drama", "Netflix");
        Movie m3 = new Movie("1917", DateConverter.fromString("25-12-2019"), 27, "War film", "Netflix");

        firebaseController.addMovieInFirebase(m1);
        firebaseController.addMovieInFirebase(m2);
        firebaseController.addMovieInFirebase(m3);

        firebaseController.loadMoviesFromFirebase(loadMovies());

        lvFirebase.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                Movie movie1 = movieList.get(position);
                firebaseController.removeMovie(movie1);
                MovieAdapter adapter = new MovieAdapter(getApplicationContext(),
                        R.layout.lv_movies_row,
                        movieList,
                        getLayoutInflater());

                lvFirebase.setAdapter(adapter);
                return true;
            }
        });


    }

    void initComponent() {
        lvFirebase = findViewById(R.id.lvFirebase);

        firebaseController = new FirebaseController();
    }


    private ValueEventListener loadMovies() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                movieList.clear();
                //passing through every instance from Firebase
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    //getting the object from Firebase
                    Movie movie = ds.getValue(Movie.class);
                    movieList.add(movie);
                }

                MovieAdapter movieAdapter = new MovieAdapter(getApplicationContext(),
                        R.layout.lv_movies_row,
                        movieList,
                        getLayoutInflater());
                lvFirebase.setAdapter(movieAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Firebase", "Error while reading from firebase");

            }
        };
    }
}