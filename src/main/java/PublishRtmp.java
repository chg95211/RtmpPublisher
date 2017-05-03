import com.silence.util.FrameImageGrabber;
import org.bytedeco.javacv.FrameGrabber;

/**
 * Created by WangRupeng on 2017/4/28.
 */

public class PublishRtmp {
    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir").replaceAll("\\\\", "/");
        String url = "rtsp://mpv.cdn3.bigCDN.com:554/bigCDN/definst/mp4:bigbuckbunnyiphone_400.mp4";
        String url2 = "rtmp://live.hkstv.hk.lxdns.com/live/hks";
        String file = "" + userDir + "/resources/data/The_Nut_Job_trailer.mp4";

        //this is your rtmp server url,like  rtmp://ip:port/appName
        String rtmpServer = "";

        try {
            FrameImageGrabber frameImageGrabber = new FrameImageGrabber(url, rtmpServer);
            frameImageGrabber.grabPublish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
