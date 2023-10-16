package eu.ase.ro.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

//each activity will extend AppCompatActivity for having access to newer features of the plaftorm when using older Android versions
public class MainActivity extends AppCompatActivity {

    //onCreate() method is overriden by default, it is similar to a constructor, initializing the activity and its components
    //it is the only mandatory method from the lifecycle that an activity need to implement
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding the xml file from res/layout associated to the user interface of the activity
        setContentView(R.layout.activity_main);

        //Log class -> used for creating logs
        //the logs can be seen in the LogCat console and they help during the debug sessions, in case something is not working properly in the application
        //Log.i -> info logs
        //the structure of a log: TAG (used for filterng the logs in the LogCat console) and the message that will be shown
        //

        Log.i("MainActivity", "onCreate");

        //Toast -> class through which alerts / temporary messages are displayed on the screen
        //it needs 3 parameters: the application context, the string we want to display and the duration
        //the call of the show() method is mandatory for displaying the message
        Toast.makeText(getApplicationContext(), R.string.hello_world, Toast.LENGTH_LONG).show();

        //in the case of a parametrized string, it will be populated through getString() method, which receives two parameters:
        //first: the used resource from string.xml, second:the parameters of the parametrized string
        Toast.makeText(getApplicationContext(), getString(R.string.method_call, "onCreate"), Toast.LENGTH_LONG).show();
    }

    //called method after the activity was created and it becomes visible for the user
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity", "onStart");
        Toast.makeText(getApplicationContext(), getString(R.string.method_call, "onStart"), Toast.LENGTH_LONG).show();


    }

    //called method when the activity is visible and the user can interact with the screen
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity", "onResume");
    }

    //called method when the activity passes in background and it is no longer visible for the user
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity", "onPause");
    }

    //called method before an activity is destroyed;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity", "onDestroy");
    }
}