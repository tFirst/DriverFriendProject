package com.tfirst.driverfriendproject.fastcall;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.tfirst.driverfriendproject.R;

import java.security.AccessControlContext;

public class FastCall extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fast_call);
    }
}
