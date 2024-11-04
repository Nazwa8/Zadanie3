package com.example.zadanie3b;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_COUNT = "count";
    public static final String KEY_TEXT = "text";
    public static final String KEY_CHECKBOX = "checkbox";
    public static final String KEY_SWITCH = "switch";

    private TextView licznik, zaznaczono;
    private EditText wpisztext;
    private CheckBox Box;
    private Switch motyw;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        licznik = findViewById(R.id.licznik);
        zaznaczono = findViewById(R.id.zaznaczono);
        wpisztext = findViewById(R.id.wpisztext);
        Box = findViewById(R.id.Box);
        motyw = findViewById(R.id.motyw);
        Button zwienksz = findViewById(R.id.zwienksz);

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt(KEY_COUNT);
            wpisztext.setText(savedInstanceState.getString(KEY_TEXT));
            Box.setChecked(savedInstanceState.getBoolean(KEY_CHECKBOX));
            motyw.setChecked(savedInstanceState.getBoolean(KEY_SWITCH));
        }

        updateCountText();
        toggleOptionSelectedText();

        zwienksz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                updateCountText();
            }
        });

        Box.setOnCheckedChangeListener((buttonView, isChecked) -> toggleOptionSelectedText());

        motyw.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_COUNT, count);
        outState.putString(KEY_TEXT, wpisztext.getText().toString());
        outState.putBoolean(KEY_CHECKBOX, Box.isChecked());
        outState.putBoolean(KEY_SWITCH, motyw.isChecked());
    }

    private void updateCountText() {
        licznik.setText("Licznik: " + count);
    }

    private void toggleOptionSelectedText() {
        if (Box.isChecked()) {
            zaznaczono.setVisibility(View.VISIBLE);
        } else {
            zaznaczono.setVisibility(View.GONE);
        }
    }
}
