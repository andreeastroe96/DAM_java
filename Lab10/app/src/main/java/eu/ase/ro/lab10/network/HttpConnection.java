package eu.ase.ro.lab10.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import eu.ase.ro.lab10.parsing.XMLParser;
import eu.ase.ro.lab10.utils.Movie;

public class HttpConnection {
    private List<Movie> movies = new ArrayList<>();
    private final String urlString;
    private HttpURLConnection httpURLConnection;
    private InputStream inputStream;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;

    public HttpConnection(String url) {
        this.urlString = url;
    }

    public String readFromHttp() throws IOException {
        StringBuilder result = new StringBuilder();
        openConnection();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            result.append(line);
        }
        closeConnection();
        return result.toString();
    }

    public List<Movie> getXmlContent() throws IOException {
        try {
            URL url = new URL(urlString);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            inputStream = httpURLConnection.getInputStream();
            XMLParser xmlParser = new XMLParser();
            movies = xmlParser.parse(inputStream);
            inputStream.close();
            httpURLConnection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return movies;
    }

    //method dedicated to create the http connection
    private void openConnection() throws IOException {
        //to get the data stored at a certain http address, an URL objet is needed
        //its constructor received a parameter, the http address as a String
        URL url = new URL(urlString);
        //open an http connection
        httpURLConnection = (HttpURLConnection) url.openConnection();

        //reading the data
        //Input Stream -> utility class that takes a chunk of data from a certain location
        inputStream = httpURLConnection.getInputStream();
        //InputStreamReader -> makes sure that an InputStream is devided into smaller pieces to be processed
        inputStreamReader = new InputStreamReader(inputStream);
        //BufferedReader -> devides an InputStreamReader into smaller chunks
        // so that TimeOut ot OutOfMemory errors will be prevented
        bufferedReader = new BufferedReader(inputStreamReader);
    }

    private void closeConnection() throws IOException {
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        httpURLConnection.disconnect();
    }
}
