package eu.ase.ro.lab4_depricated_parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import eu.ase.ro.lab4_depricated_parcelable.util.Movie;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 200;
    public static final String USER_INFO_KEY = "sendUserInfo";
    public static final String USER_NAME_KEY = "sendUserName";
    public static final String USER_AGE_KEY = "sendUserAge";

    //declare an FloatingActionButton object
    FloatingActionButton fab;
    private ListView lvMovies;
    private List<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();

        fab.setOnClickListener(new View.OnClickListener() {
            //onClick method -> write the logic that will happen at button click
            @Override
            public void onClick(View view) {
                //initialize an Intent object
                Intent intentAdd = new Intent(getApplicationContext(), AddMovieActivity.class);
                //start a new activity and waiting for a response - deprecated API
                //startActivity(intentAdd);
                startActivityForResult(intentAdd, REQUEST_CODE);
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
        } else if (item.getItemId() == R.id.menu_option_2) {
            Toast.makeText(getApplicationContext(), R.string.option2, Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private void initComponents() {
        //initialize an object
        fab = findViewById(R.id.fabAddMovie);
        lvMovies = findViewById(R.id.lvMovies);
        ArrayAdapter<Movie> movieArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, movies);
        lvMovies.setAdapter(movieArrayAdapter);
    }

    //method used to receive a result from a secondary activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //to receive the result, 3 conditions should be met:
        //1. the received requestCode is the same as the expected one
        //2.the resultCode has the RESULT_OK value
        //3. there is data passed between the two activities

        if(requestCode == REQUEST_CODE &&
                resultCode == RESULT_OK && data!=null){
            Movie movie = data.getParcelableExtra(AddMovieActivity.MOVIE_KEY);
            if(movie!=null){
                movies.add(movie);
                ArrayAdapter adapter = (ArrayAdapter) lvMovies.getAdapter();
                adapter.notifyDataSetChanged();
            }
        }
    }
}