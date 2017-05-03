package com.silence.util;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.*;

import java.awt.image.BufferedImage;

/**
 * This is a tool class, grab image and then publish each frame to rtmp server
 * Created by WangRupeng on 2017/4/28.
 */
public class FrameImageGrabber {
    private String mUrl;
    private FFmpegFrameGrabber mGrabber;
    private OpenCVFrameConverter.ToIplImage mIplImageConverter;
    private Java2DFrameConverter mImageConverter;

    //publish tool
    private PublishUtil publishUtil;


    public FrameImageGrabber(String url, String rtmpServer) throws Exception {

        //init frame grab
        this.mUrl = url;
        mGrabber = new FFmpegFrameGrabber(this.mUrl);
        mIplImageConverter = new OpenCVFrameConverter.ToIplImage();
        mImageConverter = new Java2DFrameConverter();
        mGrabber.start();

        //init publisher
        publishUtil = new PublishUtil(rtmpServer);
    }

    /**
     * Grab frame from video stream and publish to rtmp server
     */
    public void grabPublish() {
        Frame frame = null;
        opencv_core.IplImage image = null;
        double frameRate;
        int oldW;
        int oldH;
        int lastNumber = -1;
        int frameNumber = 0;
        try{
            frameRate = mGrabber.getFrameRate();
            System.out.println("FrameRate is " + frameRate);

            //init publish
            oldW = mGrabber.getImageWidth();
            oldH = mGrabber.getImageHeight();
            publishUtil.setFrameRate(frameRate);
            publishUtil.setImageWidth(oldW);
            publishUtil.setImageHeight(oldH);
            publishUtil.startRecording();

            int frameIndex = 0;
            int frameLength = 0;

            //from video files
            if(mUrl.startsWith("file://") || mUrl.startsWith("/")) {
                frameLength = mGrabber.getLengthInFrames();
            }
            while (frameLength <= 0 || frameIndex < frameLength) {
                try{
                    frame = mGrabber.grabImage();
                    frameIndex++;
                    if(frame == null) {
                        continue;
                    }

                    frameNumber = mGrabber.getFrameNumber();
                    //grab the same frame, continue
                    if(frameNumber == lastNumber) {
                        continue;
                    }
                    //grab different frame, record the frame number
                    lastNumber = frameNumber;
                } catch (org.bytedeco.javacv.FrameGrabber.Exception e) {
                    e.printStackTrace();
                }

                System.out.println("Video width is " + oldW + ", video height is " + oldH);

                //you can add frame picture processing algorithm here
                frame = frameProcess(frame);
                publishUtil.record(frame);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * process single frame
     * @param frame
     * @return processed frame
     */
    private Frame frameProcess(Frame frame) {
        return frame;
    }
}
