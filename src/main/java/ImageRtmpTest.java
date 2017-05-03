import com.silence.util.BufferImageTest;

/**
 * Created by WangRupeng on 2017/4/28.
 */
public class ImageRtmpTest {
    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir").replaceAll("\\\\", "/");
        String imageLocation = userDir + "/resources/pictures/The_Nut_Job_trailer_20170428170804.JPG";

        //this is your rtmp server url,like  rtmp://ip:port/appName
        String rtmpServer = "";

        BufferImageTest bufferImageTest = new BufferImageTest(imageLocation,rtmpServer);
        bufferImageTest.publish();
    }
}
