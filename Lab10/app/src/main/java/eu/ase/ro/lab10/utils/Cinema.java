package eu.ase.ro.lab10.utils;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cinemas")
public class Cinema {
    @PrimaryKey
    private int id;

    private String cinemaName;
    private String location;
    private int noRooms;

    public Cinema() {}

    public Cinema(int id, String cinemaName, String location, int noRooms) {
        this.id = id;
        this.cinemaName = cinemaName;
        this.location = location;
        this.noRooms = noRooms;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNoRooms() {
        return noRooms;
    }

    public void setNoRooms(int noRooms) {
        this.noRooms = noRooms;
    }
}
