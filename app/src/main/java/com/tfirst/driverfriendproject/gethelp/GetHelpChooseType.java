package com.tfirst.driverfriendproject.gethelp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;

import com.tfirst.driverfriendproject.R;

/**
 * Created by Stanislav Trushin on 19.11.2016.
 */

public class GetHelpChooseType extends Activity {

    private Button buttonWheel;
    private Button buttonAccumulator;
    private Button buttonFuel;
    private Button buttonZastr;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.get_help_define_type);

        buttonAccumulator = (Button) findViewById(R.id.buttonAccumulator);
        buttonFuel = (Button) findViewById(R.id.buttonFuel);
        buttonWheel = (Button) findViewById(R.id.buttonWheel);
        buttonZastr = (Button) findViewById(R.id.buttonZastr);
    }
}
