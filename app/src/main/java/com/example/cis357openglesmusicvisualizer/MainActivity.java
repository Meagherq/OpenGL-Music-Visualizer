package com.example.cis357openglesmusicvisualizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static int SETTINGS_RESULT = 1;
    private Button displayButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayButton = findViewById(R.id.button);

        displayButton.setOnClickListener(v -> {
            doConversion();
        });
    }
}


