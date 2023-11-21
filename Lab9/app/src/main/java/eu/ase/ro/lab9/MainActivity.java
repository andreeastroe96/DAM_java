package eu.ase.ro.lab9;

import static eu.ase.ro.lab9.utils.Constants.MOVIE_KEY;
import static eu.ase.ro.lab9.utils.Constants.USER_AGE_KEY;
import static eu.ase.ro.lab9.utils.Constants.USER_INFO_KEY;
import static eu.ase.ro.lab9.utils.Constants.USER_NAME_KEY;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import eu.ase.ro.lab9.utils.DateConverter;
import eu.ase.ro.lab9.utils.Movie;
import eu.ase.ro.lab9.utils.MovieAdapter;

public class MainActivity extends AppCompatActivity {
    //declare an FloatingActionButton object
    FloatingActionButton fab;
    private ListView lvMovies;
    private List<Movie> movies = new ArrayList<>();

    private ActivityResultLauncher<Intent> addLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        addLauncher = registerAddLauncher();

        fab.setOnClickListener(new View.OnClickListener() {
            //onClick method -> write the logic that will happen at button click
            @Override
            public void onClick(View view) {
                //initialize an Intent object
                Intent intentAdd = new Intent(getApplicationContext(), AddMovieActivity.class);
                //start a new activity and waiting for a response
                //startActivity(intentAdd);
                addLauncher.launch(intentAdd);
            }
        });
    }

    /**
     * In order to be able to display a simple menu, it is mandatory to attach an ActionBar to the screen
     * The simplest way to do this is to edit the app theme from themes.xml
     * and add "Theme.MaterialComponents.DayNight.DarkActionBar" as parent theme
     */

    //attach the menu from the resource files to the toolbar of the main activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //method that will implement the logic associated to pressing each option menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //get the menu option based on the associated id from the menu.xml file
        if (item.getItemId() == R.id.menu_option_1) {
            String aboutInfo = "These are the username's info";
            String userName = "userApp";
            int userAge = 22;

            Intent intentAbout = new Intent(getApplicationContext(), AboutActivity.class);
            intentAbout.putExtra(USER_INFO_KEY, aboutInfo);
            intentAbout.putExtra(USER_NAME_KEY, userName);
            intentAbout.putExtra(USER_AGE_KEY, userAge);

            startActivity(intentAbout);
        }

        if (item.getItemId() == R.id.menu_option_2) {
            Intent network = new Intent(getApplicationContext(), NetworkActivity.class);
            startActivity(network);
        }

        if (item.getItemId() == R.id.menu_option_3) {
            Intent json = new Intent(getApplicationContext(), JsonParserActivity.class);
            startActivity(json);
        }

        if (item.getItemId() == R.id.menu_option_4) {
            Intent xml = new Intent(getApplicationContext(), XMLParserActivity.class);
            startActivity(xml);
        }
        return true;
    }

    //register the Launcher
    //two parameters are needed: a callback and a contract that says the type of action that wil be launched
    private ActivityResultLauncher<Intent> registerAddLauncher() {
        ActivityResultCallback<ActivityResult> callback = getCallback();
        return registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), callback);
    }

    //building the callback -> the object that will receives the information passed between the two activities
    private ActivityResultCallback<ActivityResult> getCallback() {
        return new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Movie movie = (Movie) result.getData().getSerializableExtra(MOVIE_KEY);
                    movies.add(movie);
                    ArrayAdapter adapter = (ArrayAdapter) lvMovies.getAdapter();
                    adapter.notifyDataSetChanged();
                }
            }
        };
    }

    private void initComponents() {
        //initialize an object
        fab = findViewById(R.id.fabAddMovie);
        lvMovies = findViewById(R.id.lvMovies);

        Movie movie = new Movie("Bohemian Rapsody", DateConverter.fromString("24-10-2018"), 67, "Drama", "Netflix");
        movies.add(movie);

        //initialize an array adapter using a predefined layout
        //ArrayAdapter<Movie> movieArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, movies);

        //initialize a custom array adapter
        MovieAdapter movieArrayAdapter = new MovieAdapter(getApplicationContext(), R.layout.lv_movies_row, movies, getLayoutInflater());
        lvMovies.setAdapter(movieArrayAdapter);
    }
}