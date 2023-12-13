package eu.ase.ro.lab10.network;

import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import java.io.IOException;

public class DownloadRunnableTask implements Runnable {
    private final TextView tvResult;
    private final HttpConnection httpConnection;

    public DownloadRunnableTask(TextView tvResult, HttpConnection httpConnection) {
        this.tvResult = tvResult;
        this.httpConnection = httpConnection;
    }

    @Override
    public void run() {
        try {
            String result = httpConnection.readFromHttp();
            //bringing the background task to the UI thread via a handler defined within the thread
            //Option1:
            Handler threadHandler = new Handler(Looper.getMainLooper());
            threadHandler.post(new Runnable() {
                @Override
                public void run() {
                    tvResult.setText(result);
                }
            });

            //Option2:
            /*tvResult.post(new Runnable() {
                @Override
                public void run() {
                    tvResult.setText(result);
                }
            });*/

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
