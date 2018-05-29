package com.mehta.android.preference;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtPassword;
    CheckBox chkLockScreen;
    EditText txtReminder;
    Button btnSave;
    Button btnShow;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtPassword = findViewById(R.id.txtPassword);
        chkLockScreen = findViewById(R.id.chkLockScreen);
        txtReminder = findViewById(R.id.txtReminder);
        btnSave = findViewById(R.id.btnSave);
        btnShow = findViewById(R.id.btnShow);



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("preference",MainActivity.this.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Password", txtPassword.getText().toString());
                editor.putString("Reminder", txtReminder.getText().toString());
                editor.putBoolean("LockScreen", chkLockScreen.isChecked());
            }
        });



            btnShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        try{
                            String strPassword="Not Set";
                            String strReminder="Not Set";
                            Boolean blnScreenLock = false;

                            SharedPreferences sharedPreferences = getSharedPreferences("preference",MainActivity.this.MODE_PRIVATE);
                            if (savedInstanceState.containsKey("Password"))
                                strPassword = sharedPreferences.getString("Password","Not Set");
                            if (savedInstanceState.containsKey("Reminder"))
                                strReminder = sharedPreferences.getString("Reminder","Not Set");
                            if (savedInstanceState.containsKey("LockScreen"))
                                blnScreenLock = sharedPreferences.getBoolean("LockScreen",false);



                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("Saved Preferences");
                            builder.setMessage("Password :" + strPassword + " and Reminder : " + strReminder + " and LockScreen : " + blnScreenLock.toString());
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            builder.show();
                        }
                        catch(Exception ex)
                        {
                            Toast.makeText(MainActivity.this,ex.getMessage(),Toast.LENGTH_LONG);
                        }
                }
            });


    }
}
