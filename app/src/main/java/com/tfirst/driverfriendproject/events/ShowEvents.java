package com.tfirst.driverfriendproject.events;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.tfirst.driverfriendproject.R;

/**
 * Created by Stanislav Trushin on 19.11.2016.
 */

public class ShowEvents extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.show_events);
    }
}
