package com.example.cis357openglesmusicvisualizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //public static int SETTINGS_RESULT = 1;
    private Button displayButton;

    private RadioGroup radioGroup;
    private RadioButton radioButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayButton = findViewById(R.id.button);
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup = findViewById(R.id.radioGroup2);
        displayButton.setOnClickListener(v -> {
            radioButton  = findViewById(radioGroup.getCheckedRadioButtonId());
            String yourVote = radioButton.getText().toString();
            Toast.makeText(MainActivity.this, "Selected Radio Button is:" + yourVote , Toast.LENGTH_LONG).show();
            //startRenderer();
        });
    }

    public void startRenderer() {
        Intent intent = new Intent(this, MusicActivity.class);
        startActivity(intent);
    }

}


