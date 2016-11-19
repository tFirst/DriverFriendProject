package com.tfirst.driverfriendproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.tfirst.driverfriendproject.map.GeneralMapActivity;
import com.tfirst.driverfriendproject.map.SetLocationMapActivity;

/**
 * Created by Stanislav Trushin on 18.11.2016.
 */

public class MainMenuActivity extends Activity {

    private Button buttonSendInformation;

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

    public void onClickToMapActivity(View v){
        Intent intent = new Intent(this, GeneralMapActivity.class);
        startActivity(intent);
    }
}
