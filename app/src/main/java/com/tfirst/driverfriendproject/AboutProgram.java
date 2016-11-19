package com.tfirst.driverfriendproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by Stanislav Trushin on 19.11.2016.
 */

public class AboutProgram extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.about_program);
    }
}
