package com.tfirst.driverfriendproject.events;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.tfirst.driverfriendproject.R;
import com.tfirst.driverfriendproject.connections.ConnectionWithServer;

/**
 * Created by Stanislav Trushin on 18.11.2016.
 */

public class SendInformationActivity extends Activity {

    private RadioGroup radioGroup;
    private Button buttonSendInformationSI;
    private EditText editTextYourLocation;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.send_information);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroupSendInfo);
        editTextYourLocation = (EditText) findViewById(R.id.editTextYourLocation);
        buttonSendInformationSI = (Button) findViewById(R.id.buttonSendInformationSI);
    }
}
