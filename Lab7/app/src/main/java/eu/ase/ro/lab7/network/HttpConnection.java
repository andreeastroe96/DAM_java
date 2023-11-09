package eu.ase.ro.lab7.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnection {
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
