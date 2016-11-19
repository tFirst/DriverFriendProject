package com.tfirst.driverfriendproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.tfirst.driverfriendproject.events.SendInformationActivity;
import com.tfirst.driverfriendproject.events.SetLocationMapActivity;

/**
 * Created by Stanislav Trushin on 18.11.2016.
 */

public class MainMenuActivity extends Activity {

    private Button buttonSendInformation;
    final int REQUEST_PLACE_PICKER = 1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_menu);

        buttonSendInformation = (Button) findViewById(R.id.buttonSendInformationMain);
    }

    public void onClickToSendInformation(View v) {
        Intent intent = new Intent(this, SetLocationMapActivity.class);
        startActivity(intent);
    }
}
