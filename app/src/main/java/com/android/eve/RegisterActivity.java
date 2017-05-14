package com.android.eve;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;

import java.util.Calendar;

public class RegisterActivity extends Activity {
    EditText txt_name, txt_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tools.setFullScreen(this);
        txt_name = (EditText) findViewById(R.id.txt_name);
        txt_age = (EditText) findViewById(R.id.txt_age);

    }

    public void cmd_calendar_Clicked(View view) {

    }


    public void cmd_confirm_Clicked(View view) {
        insertNewPlayer();


    }

    private void insertNewPlayer() {

        if (txt_name.getText().length() <= 0) {
            txt_name.setError("Name required");
        } else if (txt_age.getText().length() <= 0) {
            txt_age.setError("Age required");
        } else {

            int age = Integer.valueOf(txt_age.getText().toString());
            if (age < 12 || age > 18) {
              //  Snackbar.make(null, "Only 12-18 yrs old is allowed", Snackbar.LENGTH_LONG).show();
                txt_age.setError("Only 12-18 yrs old is allowed");
            } else {

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder
                        .setTitle("Confirm")
                        .setMessage("Do you want to save information?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                final PlayerHelper db = new PlayerHelper(RegisterActivity.this);
                                if (db.getCheckDuplicate(txt_name.getText().toString()) >= 1) {
                                    Snackbar.make(null, "Already Exist", Snackbar.LENGTH_LONG).show();
                                } else {
                                    db.addPlayer(new Player(0, txt_name.getText().toString(), txt_age.getText().toString(), ""));
                                    tools.ReadPlayer(RegisterActivity.this);
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    overridePendingTransition(0, 0);
                                    finish();
                                }
                            }
                        })
                        .setNegativeButton("No", null)                        //Do nothing on no
                        .show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), Splash.class));
        overridePendingTransition(0, 0);
        finish();
    }
}
