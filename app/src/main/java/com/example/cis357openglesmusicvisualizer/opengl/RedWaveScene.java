package com.example.cis357openglesmusicvisualizer.opengl;

import android.content.Context;

import com.example.cis357openglesmusicvisualizer.R;

public class RedWaveScene extends SharedScene {

    public RedWaveScene(Context context, int audioTextureId, int textureWidth) {
        super(context, audioTextureId, textureWidth, R.raw.red_sound);
    }
}