package eu.ase.ro.lab10.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import eu.ase.ro.lab10.R;

public class MovieAdapter extends ArrayAdapter<Movie> {
    TextView tvTitle, tvReleaseDate, tvProfit, tvMovieGenre, tvPlatform;
    private int resource;
    private List<Movie> objects;
    private LayoutInflater inflater;

    //the constructot of the custom adapter received four parameters:
    //1. the context
    //2. resource -> the id of the layout resource associated to the custom adapter
    //3. objects -> the list of objects that needs to be passed to the AdapterView via the custome Adapter
    //4. LayoutInflater -> the object that binds the java class of the adapter with the associated layout file
    public MovieAdapter(@NonNull Context context, int resource, @NonNull List<Movie> objects, LayoutInflater inflater) {
        super(context, resource, objects);

        this.resource = resource;
        this.objects = objects;
        this.inflater = inflater;
    }

    //method from the BaseAdapter class (ArrayAdapter extends the BaseAdapter class)
    //it is responsible for converting the Java object in a visual component configured via
    //xml files from layout (either custom files defined in res/layout or predefined in the Android framework
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //binding the xml layout file that will be filled in with data with the java class
        View row = inflater.inflate(this.resource, parent, false);
        //initialize the visual components
        initVisualComponents(row);
        //get the object from a certain position from the object list
        Movie movie = objects.get(position);
        //populate the visual components
        populateVisualComponents(movie);

        return row;
    }

    private void initVisualComponents(View row) {
        tvTitle = row.findViewById(R.id.tvMovieTitle);
        tvReleaseDate = row.findViewById(R.id.tvReleaseDate);
        tvMovieGenre = row.findViewById(R.id.tvMovieGenre);
        tvProfit = row.findViewById(R.id.tvProfit);
        tvPlatform = row.findViewById(R.id.tvPlatform);
    }

    private void populateVisualComponents(Movie movie) {
        tvTitle.setText(movie.getTitle());
        tvReleaseDate.setText(getContext().getString(R.string.releaseDate, DateConverter.fromDate(movie.getReleaseDate())));
        tvProfit.setText(getContext().getString(R.string.profitString, movie.getProfit()));
        tvMovieGenre.setText(movie.getMovieGenre());
        tvPlatform.setText(movie.getPlatform());
    }
}
