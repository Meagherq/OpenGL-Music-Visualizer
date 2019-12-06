package com.example.cis357openglesmusicvisualizer.opengl;

import android.content.Context;

import com.example.cis357openglesmusicvisualizer.R;

public class BlueWaveScene extends SharedScene {

    public BlueWaveScene(Context context, int audioTextureId, int textureWidth) {
        super(context, audioTextureId, textureWidth, R.raw.blue_sound);
    }
}