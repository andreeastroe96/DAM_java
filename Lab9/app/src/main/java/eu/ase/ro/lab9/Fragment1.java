package eu.ase.ro.lab9;

import static eu.ase.ro.lab9.utils.Constants.KEY_ACTIVITY_TO_FRAGMENT;
import static eu.ase.ro.lab9.utils.Constants.KEY_ACTIVITY_TO_FRAGMENT_INT;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment1 extends Fragment {
    TextView tvString, tvInt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        //initialize the TextView objects from the fragment
        tvString = view.findViewById(R.id.tvString);
        tvInt = view.findViewById(R.id.tvInt);

        //obtain the objects that were send to the fragment
        String dataReceived = getArguments().getString(KEY_ACTIVITY_TO_FRAGMENT);
        int value = getArguments().getInt(KEY_ACTIVITY_TO_FRAGMENT_INT, 0);

        tvString.setText(dataReceived);
        tvInt.setText(String.valueOf(value));

        return view;
    }
}