package eu.ase.ro.lab4_parcelable.util;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;
//by implementing Parcelable interface, the object becomes of Parcelable type, so serializable
//therefore, it can be passed between activities
public class Movie implements Parcelable {
    private String title;
    private Date releaseDate;
    private int profit;
    private String movieGenre;
    private String platform;

    public Movie(String title, Date releaseDate, int profit, String movieGenre, String platform) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.profit = profit;
        this.movieGenre = movieGenre;
        this.platform = platform;
    }

    //the constructor used to convert the Parcel object into a Movie object
    //the Parcel object is the one sent between activities
    //!!! pay attention to the order of the fields: the order of fields at rebuilding the object must be the same at the savin into Parcel object
    protected Movie(Parcel in) {
        title = in.readString();
        releaseDate = DateConverter.fromString(in.readString());
        profit = in.readInt();
        movieGenre = in.readString();
        platform = in.readString();
    }

    //the method that uses the constructor used to convert the Parcel object into a Movie object
    //it is a mandatory object when the Parcelable interface is implemented
    //it is called when the object is received in the second activity
    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    //the method used to save a Movie object into a Parcel object
    //it is called between passing the object between activities
    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(DateConverter.fromDate(releaseDate));
        parcel.writeInt(profit);
        parcel.writeString(movieGenre);
        parcel.writeString(platform);
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