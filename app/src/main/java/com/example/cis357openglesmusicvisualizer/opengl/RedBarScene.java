package com.example.cis357openglesmusicvisualizer.opengl;

import android.content.Context;

import com.example.cis357openglesmusicvisualizer.R;

public class RedBarScene extends SharedScene {

    public RedBarScene(Context context, int audioTextureId, int textureWidth) {
        super(context, audioTextureId, textureWidth, R.raw.red_spectrum);
    }
}