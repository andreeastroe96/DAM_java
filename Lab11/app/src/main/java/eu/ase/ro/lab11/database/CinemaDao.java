package eu.ase.ro.lab11.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import eu.ase.ro.lab11.utils.Cinema;

@Dao
public interface CinemaDao {
    @Insert
    void insert(Cinema cinema);

    @Query("select * from cinemas")
    List<Cinema> getAll();

    @Query("delete from cinemas")
    void deleteAll();
}