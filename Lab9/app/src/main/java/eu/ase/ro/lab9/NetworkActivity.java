package eu.ase.ro.lab9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import eu.ase.ro.lab9.network.DownloadCallableTask;
import eu.ase.ro.lab9.network.DownloadRunnableTask;
import eu.ase.ro.lab9.network.HttpConnection;

public class NetworkActivity extends AppCompatActivity {
    Button btnRunnable, btnCallable;
    TextView tvResult;
    String urlRunnable = "https://pastebin.com/raw/aPNZpzMB";
    String urlCallable = "https://pastebin.com/raw/uvAniicc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        initComponents();
        btnRunnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpConnection httpConnection = new HttpConnection(urlRunnable);
                DownloadRunnableTask runnableTask = new DownloadRunnableTask(tvResult, httpConnection);
                Thread download = new Thread(runnableTask);
                download.start();
            }
        });

        btnCallable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpConnection httpConnection = new HttpConnection(urlCallable);
                DownloadCallableTask downloadCallableTask = new DownloadCallableTask(httpConnection);
                ExecutorService executorService = Executors.newCachedThreadPool();
                Future<String> submit = executorService.submit(downloadCallableTask);
                try {
                    tvResult.setText(submit.get());
                } catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void initComponents() {
        btnRunnable = findViewById(R.id.btnRunnable);
        btnCallable = findViewById(R.id.btnCallable);
        tvResult = findViewById(R.id.tvResult);
    }
}