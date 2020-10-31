package com.example.triviaapp.baseui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.triviaapp.R;
import com.example.triviaapp.data.AppConstant;

public class BaseActivity extends AppCompatActivity implements AppConstant {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Method used to switch from current activity to other
     *
     * @param destinationActivity activity to open
     */
    public void switchActivity(Class destinationActivity) {

        startActivity(new Intent(this, destinationActivity));
    }

    /**
     * Method used to switch from current activity to other with data
     *
     * @param destinationActivity activity to open
     * @param bundle              data that carry to destination activity
     */
    public void switchActivity(Class destinationActivity, Bundle bundle) {

        Intent intent = new Intent(this, destinationActivity);
        intent.putExtra(KEY_BUNDLE_DATA, bundle);
        startActivity(intent);
    }
}
