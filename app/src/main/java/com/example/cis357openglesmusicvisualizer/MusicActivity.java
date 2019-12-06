package com.example.cis357openglesmusicvisualizer;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.cis357openglesmusicvisualizer.opengl.Frame;
import com.example.cis357openglesmusicvisualizer.opengl.GLScene;
import com.example.cis357openglesmusicvisualizer.opengl.MusicVisualization;
import com.example.cis357openglesmusicvisualizer.opengl.RawMusicScene;
import com.example.cis357openglesmusicvisualizer.opengl.SceneController;
import com.example.cis357openglesmusicvisualizer.opengl.SimpleBarScene;
import com.example.cis357openglesmusicvisualizer.opengl.SimpleWaveScene;
import com.example.cis357openglesmusicvisualizer.opengl.WaveForm;

import java.util.ArrayList;
import java.util.List;

public class MusicActivity extends AppCompatActivity implements Visualizer.OnDataCaptureListener {
    private static final int REQUEST_PERMISSION = 101;
    private FrameLayout mContainerView;
    private MusicVisualization mRender;
    private SceneController mSceneController;
    private List<Pair<String, ? extends GLScene>> mSceneList;
    private Visualizer mVisualizer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mContainerView = new FrameLayout(this));

        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)) {
                Toast.makeText(this, "RECORD_AUDIO permission is required.", Toast.LENGTH_SHORT).show();

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO},
                        REQUEST_PERMISSION);
            }

        } else {
            start();
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {

        switch (requestCode) {
            case REQUEST_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    start();
                }
            }
        }
    }

    private void start() {
        int captureSize = Visualizer.getCaptureSizeRange()[1];
        captureSize = captureSize > 512 ? 512 : captureSize;

        final TextureView textureView = new TextureView(this);
        mContainerView.addView(textureView);
        textureView.setSurfaceTextureListener(mRender = new MusicVisualization(this, captureSize / 2));
        textureView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(
                    View v, int left, int top, int right, int bottom,
                    int oldLeft, int oldTop, int oldRight, int oldBottom) {

                mRender.onSurfaceTextureSizeChanged(null, v.getWidth(), v.getHeight());
            }
        });
        textureView.requestLayout();

        mRender.setSceneController(mSceneController = new SceneController() {
            @Override
            public void onSetup(Context context, int audioTextureId, int textureWidth) {
                mSceneList = new ArrayList<>();
                GLScene defaultScene = new SimpleBarScene(context, audioTextureId, textureWidth);
                mSceneList.add(Pair.create("Simple Bar Equalizer", defaultScene));
                mSceneList.add(Pair.create("Simple Wave Equalizer", new SimpleWaveScene(context, audioTextureId, textureWidth)));
                mSceneList.add(Pair.create("Raw Music Output", new RawMusicScene(context, audioTextureId, textureWidth)));

                changeScene(defaultScene);

                invalidateOptionsMenu();
            }
        });

        mVisualizer = new Visualizer(0);
        mVisualizer.setCaptureSize(captureSize);
        mVisualizer.setDataCaptureListener(this, Visualizer.getMaxCaptureRate(), true, true);
        mVisualizer.setEnabled(true);
    }

    @Override
    public void onWaveFormDataCapture(Visualizer visualizer, byte[] waveform, int samplingRate) {
        mRender.updateWaveForm(new WaveForm(waveform, 0, waveform.length / 2));
    }

    @Override
    public void onFftDataCapture(Visualizer visualizer, byte[] fft, int samplingRate) {
        mRender.updateFrame(new Frame(fft, 0, fft.length / 2));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        if (mSceneList != null) {
            int id = 0;
            for (Pair<String, ? extends GLScene> pair : mSceneList) {
                menu.add(0, id, id, pair.first);
                id ++;
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mSceneList != null && mSceneController != null) {
            final GLScene scene = mSceneList.get(item.getItemId()).second;
            mSceneController.changeScene(scene);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVisualizer.setEnabled(false);
    }
}
