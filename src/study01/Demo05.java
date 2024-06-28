package study01;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * @author: aikang
 * @create_time: 2024/6/28-下午1:05
 * @description:
 */
public class Demo05 {
    static {
        // 加载C++库文件
        String property = System.getProperty("user.dir");
        System.load(property + "/lib/opencv/opencv_java4100.dll");
    }

    public static void main(String[] args) {
        // 读取图片
        Mat imread = Imgcodecs.imread("image/person_and_dog_min.png");
        Imgproc.GaussianBlur(imread, imread, new Size(13, 13),10,10);
        // 显示图片
        HighGui.imshow("result", imread);
        HighGui.waitKey();
    }
}
