package eu.ase.ro.lab10.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import eu.ase.ro.lab10.utils.Movie;

@Dao
public interface MovieDao {
    @Insert
    void insert(Movie movie);

    @Query("SELECT * FROM movies")
    List<Movie> getAllMovies();

    @Query("DELETE FROM movies")
    void deleteAll();

    @Query("DELETE FROM movies WHERE title = :title")
    void delete(String title);
}
