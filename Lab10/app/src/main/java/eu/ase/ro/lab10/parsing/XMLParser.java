package eu.ase.ro.lab10.parsing;

import static eu.ase.ro.lab10.utils.Constants.KEY_MOVIE_MOVIE_GENRE;
import static eu.ase.ro.lab10.utils.Constants.KEY_MOVIE_PLATFORM;
import static eu.ase.ro.lab10.utils.Constants.KEY_MOVIE_PROFIT;
import static eu.ase.ro.lab10.utils.Constants.KEY_MOVIE_RELEASE_DATE;
import static eu.ase.ro.lab10.utils.Constants.KEY_MOVIE_TITLE;
import static eu.ase.ro.lab10.utils.Constants.KEY_NODE_XML;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import eu.ase.ro.lab10.utils.DateConverter;
import eu.ase.ro.lab10.utils.Movie;

public class XMLParser {
    private final List<Movie> movies = new ArrayList<>();
    private Movie movie;
    private String text;

    public List<Movie> parse(InputStream is) {

        try {
            //initializing a XmlPullParser object
            XmlPullParser parser = Xml.newPullParser();
            //setting the information that the parser needs to process
            parser.setInput(is, "utf-8");

            //getting the current state of the parser
            int eventType = parser.getEventType();
            //the parsing will be finished once the position / state of the parser is END_DOCUMENT
            while (eventType != XmlPullParser.END_DOCUMENT) {
                //getting the name of the starting / ending tag
                String tagName = parser.getName();

                switch (eventType) {
                    //the parser is on a starting tag
                    case XmlPullParser.START_TAG:
                        if (tagName.equalsIgnoreCase(KEY_NODE_XML)) {
                            //a new Movie object is created
                            movie = new Movie();
                        }
                        break;
                    //the parser is on a value, not an XML element
                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;
                    //the parser is on an ending tag
                    case XmlPullParser.END_TAG:
                        if (tagName.equalsIgnoreCase(KEY_NODE_XML)) {
                            //the movie is added to the list
                            movies.add(movie);
                        } else if (tagName.equalsIgnoreCase(KEY_MOVIE_TITLE)) {
                            movie.setTitle(text);
                        } else if (tagName.equalsIgnoreCase(KEY_MOVIE_RELEASE_DATE)) {
                            movie.setReleaseDate(DateConverter.fromString(text));
                        } else if (tagName.equalsIgnoreCase(KEY_MOVIE_PROFIT)) {
                            movie.setProfit(Integer.parseInt(text));
                        } else if (tagName.equalsIgnoreCase(KEY_MOVIE_MOVIE_GENRE)) {
                            movie.setMovieGenre(text);
                        } else if (tagName.equalsIgnoreCase(KEY_MOVIE_PLATFORM)) {
                            movie.setPlatform(text);
                        }
                        break;
                    default:
                        break;
                }
                //the next element is to be processed
                eventType = parser.next();
            }

        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return movies;
    }
}
