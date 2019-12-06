package com.example.cis357openglesmusicvisualizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.Console;

public class MainActivity extends AppCompatActivity {

    private Button displayButton;
    private RadioGroup radioGroupShape;
    private RadioGroup radioGroupColor;
    private RadioButton radioShapeButton;
    private RadioButton radioColorButton;
    private String shape;
    private String color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayButton = findViewById(R.id.button);
        radioGroupShape = findViewById(R.id.radioGroup);
        radioGroupColor = findViewById(R.id.radioGroup2);

        displayButton.setOnClickListener(v -> {
            radioShapeButton  = findViewById(radioGroupShape.getCheckedRadioButtonId());
            radioColorButton  = findViewById(radioGroupColor.getCheckedRadioButtonId());
            Log.d("STATE", "I made it!");
            shape = radioShapeButton.getText().toString();
            if (shape.equals("Bars") || shape.equals("Waves"))
            {
                color = radioColorButton.getText().toString();
            }
            Log.d("STATE", "I made it past!");
            Log.d("STATE", shape);
            //Toast.makeText(MainActivity.this, "Selected Radio Button is:" + yourVote , Toast.LENGTH_LONG).show();
            startRenderer();
        });

        radioGroupShape.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioButton5) {
                for(int i = 0; i < radioGroupColor.getChildCount(); i++){
                    (radioGroupColor.getChildAt(i)).setEnabled(true);
                }
            } else if (checkedId == R.id.radioButton2) {
                for(int i = 0; i < radioGroupColor.getChildCount(); i++){
                    (radioGroupColor.getChildAt(i)).setEnabled(true);
                }
            } else if (checkedId == R.id.radioButton) {
                for(int i = 0; i < radioGroupColor.getChildCount(); i++){
                    (radioGroupColor.getChildAt(i)).setEnabled(false);
                }
            }
        });
        radioGroupShape.check(R.id.radioButton5);
    }

    public void startRenderer() {
        Intent intent = new Intent(this, MusicActivity.class);
        intent.putExtra("EXTRA_SHAPE", shape);
        if (shape.equals("Bars") || shape.equals("Waves"))
        {
            intent.putExtra("EXTRA_COLOR", color);
        }
        startActivity(intent);
    }
}


