package com.example.cis357openglesmusicvisualizer.opengl;

import android.content.Context;
import com.example.cis357openglesmusicvisualizer.R;

public class SimpleBarScene extends SharedScene {

    public SimpleBarScene(Context context, int audioTextureId, int textureWidth) {
        super(context, audioTextureId, textureWidth, R.raw.basic_spectrum);
    }
}