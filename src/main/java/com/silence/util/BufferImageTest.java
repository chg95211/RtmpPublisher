package com.silence.util;

import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by WangRupeng on 2017/4/28.
 */
public class BufferImageTest {
    private String imageLocation;
    private int width, height;
    private double frameRate = 23.0;
    private BufferedImage bufferedImage;
    private Frame frame;
    private PublishUtil publishUtil;
    private String rtmpServer;

    public BufferImageTest(String imageLocation, String rtmpServer) {
        this.imageLocation = imageLocation;
        this.rtmpServer = rtmpServer;
        publishUtil = new PublishUtil(rtmpServer);
        init();
    }

    private void init() {
        try {
            bufferedImage = ImageIO.read( new File( imageLocation) );
            width = bufferedImage.getWidth();
            height = bufferedImage.getHeight();
            publishUtil.setFrameRate(frameRate);
            publishUtil.setImageWidth(width);
            publishUtil.setImageHeight(height);

            Java2DFrameConverter converter = new Java2DFrameConverter();
            frame = converter.convert(bufferedImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void publish() {
        publishUtil.startRecording();
        while (true) {
            publishUtil.record(frame);
        }
    }
}
