package com.silence.util;

import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;

/**
 * Created by WangRupeng on 2017/4/28.
 */
public class PublishUtil {
    private FFmpegFrameRecorder recorder;

    private String rtmpServer;
    private int imageWidth;
    private int imageHeight;
    private String videoFormat = "flv";
    private int sampleAudioRateInHz = 44100;
    private double frameRate = 30.0 ;
    public PublishUtil(String rtmpLink) {
        this.rtmpServer = rtmpLink;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public double getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(double frameRate) {
        this.frameRate = frameRate;
    }

    private void initRecorder() {
        recorder = new FFmpegFrameRecorder(rtmpServer, imageWidth, imageHeight);
        recorder.setFormat(videoFormat);
        //recorder.setSampleRate(sampleAudioRateInHz);
        recorder.setFrameRate(frameRate);
    }

    public void startRecording() {
        initRecorder();
        try {
            recorder.start();

        } catch (FFmpegFrameRecorder.Exception e) {
            e.printStackTrace();
        }
    }

    public void record(Frame frame) {
        try {
            recorder.record(frame);
        }catch (FFmpegFrameRecorder.Exception e) {
            e.printStackTrace();
        }
    }
}
