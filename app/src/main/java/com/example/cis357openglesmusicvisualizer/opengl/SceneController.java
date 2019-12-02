package com.example.cis357openglesmusicvisualizer.opengl;

import android.content.Context;

public abstract class SceneController {
    private GLScene mActivedScene;


    abstract public void onSetup(Context context, int audioTextureId, int textureWidth);

    public void changeScene(GLScene scene) {
        scene.reset();
        mActivedScene = scene;
    }

    GLScene getActivedScene() {
        return mActivedScene;
    }
}
