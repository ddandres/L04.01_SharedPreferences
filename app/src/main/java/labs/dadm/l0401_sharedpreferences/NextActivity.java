/*
 * Copyright (c) 2019. David de Andrés and Juan Carlos Ruiz, DISCA - UPV, Development of apps for mobile devices.
 */

package labs.dadm.l0401_sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

/*
    It just displays a message with information retrieved from the default SharedPreferences store
 */
public class NextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        // Get a reference to the TextView
        final TextView tvMessage = findViewById(R.id.tvMessage);
        // Get a reference to the default SharedPreference store of the application
        final SharedPreferences sharedPrefs =
                PreferenceManager.getDefaultSharedPreferences(NextActivity.this);
        // Update the information displayed in the TextView according to
        // the value retrieved (username) from the default SharedPreferences store
        tvMessage.setText(
                getString(R.string.message,
                        sharedPrefs.getString(Utils.USERNAME, getString(R.string.anonymous))));
    }
}
