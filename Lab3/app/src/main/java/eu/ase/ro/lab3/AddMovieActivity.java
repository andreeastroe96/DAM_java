package eu.ase.ro.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AddMovieActivity extends AppCompatActivity {

    //declare a Spinner object
    Spinner spinnerGenre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //method that associates the .xml file from layout with the correspondent .java file
        setContentView(R.layout.activity_add_movie);

        spinnerGenre = findViewById(R.id.spinnerMovieGenre);

        //create an adapter
        ArrayAdapter<CharSequence> adapterMovieGenre = ArrayAdapter.createFromResource(getApplicationContext(),R.array.movie_genre,
                android.R.layout.simple_spinner_item);
        //attach an adapter to a spinner
        //creating the adapter is not enough; it is mandatory to attach the adapter to the spinner so that the data will be available on the interface
        spinnerGenre.setAdapter(adapterMovieGenre);
    }
}