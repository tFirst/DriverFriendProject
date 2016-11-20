package com.tfirst.driverfriendproject.gethelp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.tfirst.driverfriendproject.R;
import com.tfirst.driverfriendproject.connections.ConnectionWithServer;
import com.tfirst.driverfriendproject.events.SendInformationActivity;

public class GetHelp extends Activity {

    private Button buttonWheel;
    private Button buttonAccumulator;
    private Button buttonFuel;
    private Button buttonZastr;
    final int REQUEST_PLACE_PICKER = 1;
    private String address;
    private String latlng;
    final String description = " ";
    private GoogleApiClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.get_help_define_address);

        buttonAccumulator = (Button) findViewById(R.id.buttonAccumulator);
        buttonFuel = (Button) findViewById(R.id.buttonFuel);
        buttonWheel = (Button) findViewById(R.id.buttonWheel);
        buttonZastr = (Button) findViewById(R.id.buttonZastr);

        buttonAccumulator.setEnabled(false);
        buttonFuel.setEnabled(false);
        buttonWheel.setEnabled(false);
        buttonZastr.setEnabled(false);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void onPickButtonClick(View v) {
        try {
            PlacePicker.IntentBuilder intentBuilder =
                    new PlacePicker.IntentBuilder();
            Intent intent = intentBuilder.build(this);
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

            final Place place = PlacePicker.getPlace(data, this);

            latlng = place.getLatLng().latitude + ":" + place.getLatLng().longitude;
            final String address = place.getAddress().toString();

            System.out.println(latlng);
            this.address = address;

            buttonAccumulator.setEnabled(true);
            buttonFuel.setEnabled(true);
            buttonWheel.setEnabled(true);
            buttonZastr.setEnabled(true);

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void onClickButtonWheel(View v) {
        ConnectionWithServer connectionWithServer =
                new ConnectionWithServer(this);
        connectionWithServer
                .execute("insert:events:wheel:" + address + ":" + description + ":" + latlng);
    }

    public void onClickButtonFuel(View view) {
        ConnectionWithServer connectionWithServer =
                new ConnectionWithServer(this);
        connectionWithServer
                .execute("insert:events:fuel:" + address + ":" + description + ":" + latlng);
    }

    public void onClickButtonAccum(View view) {
        ConnectionWithServer connectionWithServer =
                new ConnectionWithServer(this);
        connectionWithServer
                .execute("insert:events:accum:" + address + ":" + description + ":" + latlng);
    }

    public void onClickButtonZastr(View view) {
        ConnectionWithServer connectionWithServer =
                new ConnectionWithServer(this);
        connectionWithServer
                .execute("insert:events:zastr:" + address + ":" + description + ":" + latlng);
    }

    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("GetHelp Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
