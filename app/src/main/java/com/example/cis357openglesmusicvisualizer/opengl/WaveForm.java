package com.example.cis357openglesmusicvisualizer.opengl;

public class WaveForm {
    private byte[] mRawWaveForm;


    public WaveForm(byte[] waveform, int offset, int len) {
        if (offset + len > waveform.length) throw new RuntimeException("Illegal offset and len");

        mRawWaveForm = new byte[len];
        System.arraycopy(waveform, offset, mRawWaveForm, 0, len);
    }

    public byte[] getRawWaveForm() {
        return mRawWaveForm;
    }
}
