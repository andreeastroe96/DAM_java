package eu.ase.ro.lab9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import eu.ase.ro.lab9.network.DownloadCallableTask;
import eu.ase.ro.lab9.network.HttpConnection;
import eu.ase.ro.lab9.network.XmlCallableTask;
import eu.ase.ro.lab9.parsing.JsonParser;
import eu.ase.ro.lab9.parsing.XMLParser;
import eu.ase.ro.lab9.utils.Movie;
import eu.ase.ro.lab9.utils.MovieAdapter;

public class XMLParserActivity extends AppCompatActivity {
    ListView lvXML;
    String urlXML = "https://pastebin.com/raw/BUXXtTfx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xmlparser);

        lvXML = findViewById(R.id.lv_xml_parser);

        HttpConnection httpConnection = new HttpConnection(urlXML);
        XmlCallableTask xmlCallableTask = new XmlCallableTask(httpConnection);
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<List<Movie>> submit = executorService.submit(xmlCallableTask);
        try {
            List<Movie> result = submit.get();
            MovieAdapter movieArrayAdapter = new MovieAdapter(getApplicationContext(), R.layout.lv_movies_row, result, getLayoutInflater());
            lvXML.setAdapter(movieArrayAdapter);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}