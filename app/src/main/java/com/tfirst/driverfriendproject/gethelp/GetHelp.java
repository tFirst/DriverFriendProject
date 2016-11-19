package com.tfirst.driverfriendproject.gethelp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.tfirst.driverfriendproject.R;

/**
 * Created by Stanislav Trushin on 18.11.2016.
 */

public class GetHelp extends Activity {
    private EditText editTextYourLocationGetHelp;
    private Button buttonNextActivityGetHelp;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.get_help_define_address);

        editTextYourLocationGetHelp = (EditText) findViewById(R.id.editTextYourLocationGetHelp);
        buttonNextActivityGetHelp = (Button) findViewById(R.id.buttonNextActivityGetHelp);
    }
}
