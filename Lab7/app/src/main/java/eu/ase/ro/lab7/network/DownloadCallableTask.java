package eu.ase.ro.lab7.network;

import android.widget.TextView;

import java.util.concurrent.Callable;

public class DownloadCallableTask implements Callable<String> {
    private final HttpConnection httpConnection;
    public DownloadCallableTask(HttpConnection httpConnection) {
        this.httpConnection = httpConnection;
    }

    @Override
    public String call() throws Exception {
        return httpConnection.readFromHttp();
    }
}
