package eu.ase.ro.lab6_recyclerview.utils;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import eu.ase.ro.lab6_recyclerview.R;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movies;
    Context context;

    //the constructor of the custom adapter. it receives two parameters:
    //1. the context
    //2. the list of objects that will be passed to the AdapterView -> in this case, a RecyclerView
    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    //method that creates the ViewHolder object associated to the adapter
    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //initialize a LayoutInflater object
        LayoutInflater inflater = LayoutInflater.from(context);
        //bind the xml layout file with the ViewHolder
        View row = inflater.inflate(R.layout.rv_movies_row, parent, false);
        return new MovieViewHolder(row);
    }

    //method used for displaying the data in a RecyclerView at a certain position
    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.tvTitle.setText(movie.getTitle());
        holder.tvReleaseDate.setText(context.getString(R.string.releaseDate, DateConverter.fromDate(movie.getReleaseDate())));
        holder.tvProfit.setText(context.getString(R.string.profitString, movie.getProfit()));
        holder.tvGenre.setText(movie.getMovieGenre());
        holder.tvPlatform.setText(movie.getPlatform());
    }

    //the method that counts the elements of the object lists that is passed to the AdapterView
    @Override
    public int getItemCount() {
        return movies.size();
    }

    //inner class derived from RecyclerView.ViewHolder that represents the ViewHolder of the custom adapter
    //ViewHolder -> an object that describes the way an item from the list looks like
    public class MovieViewHolder extends RecyclerView.ViewHolder {
        //declare the TextVuew objects that the xml file contains
        TextView tvTitle, tvReleaseDate, tvProfit, tvPlatform, tvGenre;

        //the constructor that creates the ViewHolder
        //within the constructor, all the visual components from the xml layout files are initialized through findViewById() method
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvMovieTitle);
            tvReleaseDate = itemView.findViewById(R.id.tvReleaseDate);
            tvProfit = itemView.findViewById(R.id.tvProfit);
            tvPlatform = itemView.findViewById(R.id.tvPlatform);
            tvGenre = itemView.findViewById(R.id.tvMovieGenre);
        }
    }
}
