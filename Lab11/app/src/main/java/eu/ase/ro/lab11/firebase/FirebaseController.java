package eu.ase.ro.lab11.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import eu.ase.ro.lab11.utils.Movie;

//java class that is responsible with Firebase operations
public class FirebaseController {
    //represents a specific location in the database that can be used to either write or read data
    private DatabaseReference databaseReference;
    private static final String TABLE_NAME = "Movies";
    private static final String FIREBASE_TAG = "FirebaseEvent";

    public FirebaseController() {
        //FirebaseDatabase -> the point of entry in a Firebase realtime database, that gives us access to the location
        //initializing a FirebaseDatabase object
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        //getting access to the location in Firebase where data can be written to / read from.
        databaseReference = db.getReference(TABLE_NAME);
    }

    //method that assures writing films in a Firebase database
    public void addMovieInFirebase(Movie movie) {
        if(movie != null) {
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    //an id of the entry is created
                    movie.setGlobalId(databaseReference.push().getKey());
                    //the entry is saved as a child to the generated key
                    databaseReference.child(movie.getGlobalId()).setValue(movie);
                    Log.i(FIREBASE_TAG, "The movie has been added to firebase database");
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.w(FIREBASE_TAG, "Insert of the movie is not working");
                }
            });
        }
    }

    public void loadMoviesFromFirebase(ValueEventListener eventListener) {
        if(eventListener != null) {
            //addValueEventListener -> helps at detecting the changes of an entry from Firebase (the entry of a certain path and its children)
            //this listener listens for a longer period, not for a simgle entry
            databaseReference.addValueEventListener(eventListener);
        }
    }

    public void removeMovie(Movie movie) {
        if(movie != null) {
            databaseReference.child(movie.getGlobalId()).removeValue();
        }
    }
}
