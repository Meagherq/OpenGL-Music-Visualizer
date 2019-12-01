package com.example.openglmusicvisualizer;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.media.audiofx.Visualizer;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Visualizer.OnDataCaptureListener{

    private static final int CAPTURE_SIZE = 256;
//            probably dont need these two lines below
//    private static final int REQUEST_CODE = 0;
//    static final String[] PERMISSIONS = new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.MODIFY_AUDIO_SETTINGS};

    private Visualizer mVisualizer;
    private MusicView musicView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        setContentView(R.layout.activity_main);
        musicView =(MusicView) findViewById(R.id.musicView);
        //startVisualiser();
    }

    @Override
    protected void onResume() {
        super.onResume();
        musicView.onResume();
        //startVisualiser();
    }

    @Override
    protected void onPause() {
        if (mVisualizer != null) {
            mVisualizer.setEnabled(false);
            mVisualizer.release();
            mVisualizer.setDataCaptureListener(null, 0, false, false);
        }
        super.onPause();
        musicView.onPause();
    }

    @Override
    public void onWaveFormDataCapture(Visualizer visualizer, byte[] waveform, int samplingRate) {
        //send waveform data to the view for the renderer to do something with.
        //make a function to handle this in the view
        musicView.setWaveform(waveform);
    }

    @Override
    public void onFftDataCapture(Visualizer visualizer, byte[] fft, int samplingRate) {
        //        nuthin. don'eedit
    }

    private void startVisualiser() {
        mVisualizer = new Visualizer(0);
        mVisualizer.setDataCaptureListener(this, Visualizer.getMaxCaptureRate(), true, false);
        mVisualizer.setCaptureSize(CAPTURE_SIZE);
        mVisualizer.setEnabled(true);
    }
}
