package com.tfirst.driverfriendproject.services;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.tfirst.driverfriendproject.R;

/**
 * Created by Stanislav Trushin on 18.11.2016.
 */

public class Services extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.services_activity);
    }
}
