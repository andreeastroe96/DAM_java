package eu.ase.ro.lab10.utils;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

//@Entity annotation -> this class represents a table from the database
//the table has the name "movies" and there is a foreign key to "cinemas" table
@Entity(tableName = "movies", foreignKeys = @ForeignKey(entity = Cinema.class, parentColumns = "id", childColumns = "idCinema"))
public class Movie implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int idCinema;
    private String title;
    private Date releaseDate;
    private int profit;
    private String movieGenre;
    private String platform;

    public Movie() {}

    public Movie(String title, Date releaseDate, int profit, String movieGenre, String platform) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.profit = profit;
        this.movieGenre = movieGenre;
        this.platform = platform;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCinema() {
        return idCinema;
    }

    public void setIdCinema(int idCinema) {
        this.idCinema = idCinema;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", profit=" + profit +
                ", movieGenre='" + movieGenre + '\'' +
                ", platform='" + platform + '\'' +
                '}';
    }
}
