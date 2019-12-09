package com.example.android.l0401_sharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

/*
    Displays a number of elements whose state is stored
    and later retrieved using the SharedPreferences mechanism.
 */
public class MainActivity extends AppCompatActivity {

    // Holds a reference to a SharedPreference object
    private SharedPreferences sharedPrefs;
    // Hold references for the Views
    private EditText etUsername;
    private Switch swBluetooth;
    private SeekBar sbVolume;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a reference to the default SharedPreference store of the application
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        // Get a reference to all the Views
        etUsername = findViewById(R.id.etUsername);
        swBluetooth = findViewById(R.id.swBluetooth);
        sbVolume = findViewById(R.id.sbVolume);
    }

    /*
        This method will be executed whenever the activity is going into the background.
        It will store the current state of its Views.
     */
    @Override
    protected void onPause() {
        super.onPause();

        // Get an Editor to be able to modify the default SharedPreference store
        SharedPreferences.Editor editor = sharedPrefs.edit();
        final String userName = etUsername.getText().toString();
        if (userName.isEmpty()) {
            // If the user name TextEdit is empty, then remove the element from the store
            editor.remove(Utils.USERNAME);
        } else {
            // If there is a user name written then update the store with this information
            editor.putString(Utils.USERNAME, etUsername.getText().toString());
        }
        // Update the store with the state of the Switch (Boolean) and SeekBar (Int)
        editor.putBoolean(Utils.BLUETOOTH, swBluetooth.isChecked());
        editor.putInt(Utils.VOLUME, sbVolume.getProgress());
        // Commit the changes asynchronously
        editor.apply();
    }

    /*
        This method will be executed whenever the activity is going to the foreground.
        It will retrieve the stored state of the Views and update them accordingly.
     */
    @Override
    protected void onResume() {
        super.onResume();

        // Update each View with the information stored in the default SharedPreferences store
        etUsername.setText(sharedPrefs.getString(Utils.USERNAME, ""));
        swBluetooth.setChecked(sharedPrefs.getBoolean(Utils.BLUETOOTH, false));
        sbVolume.setProgress(sharedPrefs.getInt(Utils.VOLUME, 0));
    }

    /*
        This method eill be executed whenever the Button is clicked.
        It will launch the NextActivity.
     */
    public void launchNextActivity(View view) {
        final Intent intent = new Intent(MainActivity.this, NextActivity.class);
        startActivity(intent);
    }
}
