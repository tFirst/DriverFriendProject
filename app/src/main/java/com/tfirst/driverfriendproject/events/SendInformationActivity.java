package com.tfirst.driverfriendproject.events;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.tfirst.driverfriendproject.R;
import com.tfirst.driverfriendproject.connections.ConnectionWithServer;

/**
 * Created by Stanislav Trushin on 18.11.2016.
 */

public class SendInformationActivity extends Activity {

    private Button buttonSendInformationSI;
    private EditText editTextYourLocation;
    private RadioGroup radioGroup;
    private TextView textView;
    private int num;
    private String result;
    final int REQUEST_PLACE_PICKER = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.send_information);

        editTextYourLocation = (EditText) findViewById(R.id.editTextYourLocation);
        buttonSendInformationSI = (Button) findViewById(R.id.buttonSendInformationSI);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroupSendInformation);
        textView = (TextView) findViewById(R.id.textViewYourLocation);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case -1:
                        Toast.makeText(getApplicationContext(), "Ничего не выбрано!",
                                Toast.LENGTH_LONG).show();
                        break;
                    case R.id.radioButtonDTP:
                        defineRadioButtonClick(1);
                        break;
                    case R.id.radioButtonDPS:
                        defineRadioButtonClick(2);
                        break;
                    case R.id.radioButtonRW:
                        defineRadioButtonClick(3);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void defineRadioButtonClick(int i) {
        this.num = i;
    }

    public void onPickButtonClick(View v) {
        try {
            PlacePicker.IntentBuilder intentBuilder =
                    new PlacePicker.IntentBuilder();
            Intent intent = intentBuilder.build(this);
            // Start the intent by requesting a result,
            // identified by a request code.
            startActivityForResult(intent, REQUEST_PLACE_PICKER);

        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            // ...
        }
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {

        if (requestCode == REQUEST_PLACE_PICKER
                && resultCode == Activity.RESULT_OK) {

            // The user has selected a place. Extract the name and address.
            final Place place = PlacePicker.getPlace(data, this);

            final CharSequence name = place.getName();
            final CharSequence address = place.getAddress();

            System.out.println(name);
            System.out.println(address);

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void onClickToSendInformation(View v) {
        ConnectionWithServer connectionWithServer =
                new ConnectionWithServer(this);
        switch (this.num) {
            case 1:
                connectionWithServer
                        .execute("select:events:types:address:crush");
                //.execute("insert:events:crash:address:description");
                break;
            case 2:
                connectionWithServer
                        .execute("insert:events:road_works:address:description");
                break;
            case 3:
                connectionWithServer
                        .execute("insert:events:rps:address:description");
                break;
            default:
                break;
        }
    }

    public void setToStringResult(String line) {
        this.result = line;
        Toast.makeText(getApplicationContext(), this.result, Toast.LENGTH_SHORT).show();
    }
}
