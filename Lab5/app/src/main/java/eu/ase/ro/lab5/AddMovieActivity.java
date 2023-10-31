package eu.ase.ro.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;

import eu.ase.ro.lab5.util.DateConverter;
import eu.ase.ro.lab5.util.Movie;

public class AddMovieActivity extends AppCompatActivity {

    //the key used by the intent to store the movie object that will be sent to MainActivity
    public static final String MOVIE_KEY = "sendMovie";
    EditText etTitle, etReleaseDate, etProfit;
    Spinner spinnerGenre;
    RadioGroup rgPlatform;
    Button btnSave;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //method that associates the .xml file from layout with the correspondent .java file
        setContentView(R.layout.activity_add_movie);

        initComponents();
        intent = getIntent();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValid()) {
                    Movie movie = buildMovieFromComponents();
                    intent.putExtra(MOVIE_KEY, movie);
                    //method used to return the results expected by the other activity
                    //it has two parameters
                    //1. resultCode -> marks the type of response (for a correct result, the RESULT_OK constant is used
                    //2. data -> the Intent object that was used to open the activity and that contains the information we want to pass between activities
                    setResult(RESULT_OK, intent);
                    //finish() -> method used to finalize an activity lifecycle
                    finish();
                }
            }
        });
    }

    //method used to initialize all visual components
    private void initComponents() {
        etTitle = findViewById(R.id.etMovieTitle);
        etReleaseDate = findViewById(R.id.etDate);
        etProfit = findViewById(R.id.etProfit);
        initSpinner();
        rgPlatform = findViewById(R.id.rgPlatform);
        btnSave = findViewById(R.id.btnSave);
    }

    //method used to initialize a Spinner object
    private void initSpinner() {
        spinnerGenre = findViewById(R.id.spinnerMovieGenre);

        //create an adapter
        ArrayAdapter<CharSequence> adapterMovieGenre = ArrayAdapter.createFromResource(getApplicationContext(),R.array.movie_genre,
                android.R.layout.simple_spinner_item);
        //attach an adapter to a spinner
        //creating the adapter is not enough; it is mandatory to attach the adapter to the spinner so that the data will be available on the interface
        spinnerGenre.setAdapter(adapterMovieGenre);
    }

    //method used to validate if the user input is valid
    private boolean isValid() {
        //trim() -> Java method that removes the spaces from the beginning and ending of a String
        if(etTitle.getText() == null || etTitle.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.add_movie_title_error, Toast.LENGTH_SHORT).show();
            return false;
        }

        if(etReleaseDate.getText() == null || etReleaseDate.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.add_movie_date_error, Toast.LENGTH_SHORT).show();
            return false;
        }

        if(etProfit.getText() == null || etProfit.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.add_movie_profit_error, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    //method used to take user input and build the Movie object
    private Movie buildMovieFromComponents() {
        String title = etTitle.getText().toString();
        Date releaseDate = DateConverter.fromString(etReleaseDate.getText().toString());
        int profit = Integer.parseInt(etProfit.getText().toString());
        String movieGenre = spinnerGenre.getSelectedItem().toString();
        RadioButton checkedButton = findViewById(rgPlatform.getCheckedRadioButtonId());
        String platform = checkedButton.getText().toString();
        return new Movie(title, releaseDate, profit, movieGenre, platform);
    }
}