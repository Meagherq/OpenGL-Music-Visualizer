package com.example.cis357openglesmusicvisualizer.opengl;

import android.content.Context;
import com.example.cis357openglesmusicvisualizer.R;

public class BlueBarScene extends SharedScene {

    public BlueBarScene(Context context, int audioTextureId, int textureWidth) {
        super(context, audioTextureId, textureWidth, R.raw.blue_spectrum);
    }
}