package eu.ase.ro.lab3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    //declare an FloatingActionButton object
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize an object
        fab = findViewById(R.id.fabAddMovie);
        fab.setOnClickListener(new View.OnClickListener() {
            //onClick method -> write the logic that will happen at button click
            @Override
            public void onClick(View view) {
                //initialize an Intent object
                Intent intent = new Intent(getApplicationContext(), AddMovieActivity.class);
                //start a new activity
                startActivity(intent);
            }
        });

    }

    /** In order to be able to display a simple menu, it is mandatory to attach an ActionBar to the screen
     * The simplest way to do this is to edit the app theme from themes.xml
     * and add "Theme.MaterialComponents.DayNight.DarkActionBar" as parent theme
     * */

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
        if(item.getItemId() == R.id.menu_option_1) {
            Toast.makeText(getApplicationContext(),  R.string.option1, Toast.LENGTH_SHORT).show();
        } else if(item.getItemId() == R.id.menu_option_2) {
            Toast.makeText(getApplicationContext(), R.string.option2, Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}