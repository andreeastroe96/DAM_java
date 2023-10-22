package eu.ase.ro.lab4_depricated_parcelable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {


    private TextView tvUserInfo, tvUserName, tvUserAge;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        initComponents();

        //hasExtra() -> checks if there is something stored at the key that is received as a parameter
        if(intent.hasExtra(MainActivity.USER_INFO_KEY)) {
            //method from Intent class that is used to get a custom object from the intent
            //its paramter is the key that the object was saved at through the call of putExtra() method
            String userInfo = intent.getStringExtra(MainActivity.USER_INFO_KEY);
            tvUserInfo.setText(userInfo);
        }

        if(intent.hasExtra(MainActivity.USER_NAME_KEY)) {
            String userName = intent.getStringExtra(MainActivity.USER_NAME_KEY);
            tvUserName.setText(userName);
        }

        if(intent.hasExtra(MainActivity.USER_AGE_KEY)) {
            String userAge = String.valueOf(intent.getIntExtra(MainActivity.USER_AGE_KEY, 0));
            tvUserAge.setText(userAge);
        }
    }

    private void initComponents() {
        tvUserInfo = findViewById(R.id.tvInfo);
        tvUserName = findViewById(R.id.tvUserName);
        tvUserAge = findViewById(R.id.tvUserAge);

        intent = getIntent();
    }
}