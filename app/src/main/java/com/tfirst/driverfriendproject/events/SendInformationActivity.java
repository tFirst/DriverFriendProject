package com.tfirst.driverfriendproject.events;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.tfirst.driverfriendproject.R;
import com.tfirst.driverfriendproject.connections.ConnectionWithServer;

/**
 * Created by Stanislav Trushin on 18.11.2016.
 */

public class SendInformationActivity extends Activity {

    private Spinner spinnerTypesOfEvents;
    private Button buttonSendInformationSI;
    private EditText editTextYourLocation;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.send_information);

        spinnerTypesOfEvents = (Spinner) findViewById(R.id.spinnerTypesOfEvents);
        editTextYourLocation = (EditText) findViewById(R.id.editTextYourLocation);
        buttonSendInformationSI = (Button) findViewById(R.id.buttonSendInformationSI);

        fillingSpinner();
    }

    private String[] connection() {
        ConnectionWithServer connectionWithServer = new ConnectionWithServer();
        connectionWithServer.execute("fillingspinner");
        return connectionWithServer.result.split(",");
    }

    private void fillingSpinner() {
        String[] resultsFromServer = connection();
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<>(this, R.layout.send_information, resultsFromServer);
        spinnerTypesOfEvents.setAdapter(arrayAdapter);
    }
}
