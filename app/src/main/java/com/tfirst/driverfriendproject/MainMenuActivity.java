package com.tfirst.driverfriendproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.tfirst.driverfriendproject.chat.Chat;
import com.tfirst.driverfriendproject.events.SendInformationActivity;
import com.tfirst.driverfriendproject.gethelp.GetHelp;
import com.tfirst.driverfriendproject.map.GeneralMapActivity;

public class MainMenuActivity extends Activity {

    private Button buttonSendInformation;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_menu);
        buttonSendInformation = (Button) findViewById(R.id.buttonSendInformationMain);
    }

    public void onClickToMapActivity(View v){
        Intent intent = new Intent(this, GeneralMapActivity.class);
        startActivity(intent);
    }

    public void onClickToSendInformation(View v){
        Intent intent = new Intent(this, SendInformationActivity.class);
        startActivity(intent);
    }

    public void onClickToChat(View v){
        Intent intent = new Intent(this, Chat.class);
        startActivity(intent);
    }

    public void onClickToGetHelp(View v){
        Intent intent = new Intent(this, GetHelp.class);
        startActivity(intent);
    }
}
