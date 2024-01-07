package eu.ase.ro.lab11.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import eu.ase.ro.lab11.utils.Cinema;
import eu.ase.ro.lab11.utils.DateConverter;
import eu.ase.ro.lab11.utils.Movie;

//exportSchema -> if we set it to false, we don't have a versioning of the database
@Database(entities = {Movie.class, Cinema.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class MovieDB extends RoomDatabase {
    public static final String DB_NAME = "movies.db";
    public static MovieDB INSTANCE;

    public abstract MovieDao getMovieDao();
    public abstract CinemaDao getCinemaDao();

    //this method is used to instantiate a database -> it follows the principles of a singleton
    //allowMainThreadQueries() -> deactivates the verification of database queries on UI Thread
    //it should be used only in tests because it can otherwise lead to memory issues for activities
    //fallbackToDestructiveMigration() -> allows Room to recreate the tables of the database
    public static MovieDB getInstance(Context context) {
        if(INSTANCE == null) {
            synchronized (MovieDB.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, MovieDB.class, DB_NAME)
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
