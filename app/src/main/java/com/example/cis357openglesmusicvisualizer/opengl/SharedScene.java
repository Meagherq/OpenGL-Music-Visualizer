package com.example.cis357openglesmusicvisualizer.opengl;

import android.content.Context;
import com.example.cis357openglesmusicvisualizer.R;

public class SharedScene extends GLScene {
    private int mProgram;
    private int mAudioTextureId;
    private int mTexttureWidth;


    public SharedScene(Context context, int audioTextureId, int textureWidth, int programResId) {
        mProgram = Helpers.buildProgram(context, R.raw.vertext, programResId);
        mAudioTextureId = audioTextureId;
        mTexttureWidth = textureWidth;
    }

    @Override
    public void onDraw(int canvasWidth, int canvasHeight) {
        runShandertoyProgram(
                mProgram,
                new int[]{canvasWidth, canvasHeight},
                new int[]{mAudioTextureId},
                new int[][]{new int[]{mTexttureWidth, 2}}
        );
    }
}
