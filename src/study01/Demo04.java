package study01;

import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * @author: aikang
 * @create_time: 2024/6/28-下午1:05
 * @description:
 */
public class Demo04 {
    static {
        // 加载C++库文件
        String property = System.getProperty("user.dir");
        System.load(property + "/lib/opencv/opencv_java4100.dll");
    }

    public static void main(String[] args) {
        // 读取图片
        Mat imread = Imgcodecs.imread("image/person_and_dog_min.png");
        // 原图转换灰度图
        Imgproc.cvtColor(imread, imread, Imgproc.COLOR_RGB2GRAY);
        // 创建结果集
        Mat result = new Mat();
        //
        /**
         * 二值化处理
         * Imgproc.THRESH_BINARY: 当像素值超过阈值thresh时取maxval，否则取0
         * Imgproc.THRESH_BINARY_INV: THRESH_BINARY的反向操作
         * Imgproc.THRESH_TRUNC: 大于阈值时设为阈值，否则不变
         * Imgproc.THRESH_TOZERO: 大于阈值时不变，否则设为0
         * Imgproc.THRESH_TOZERO_INV: THRESH_TOZERO的反向操作
         */
        Imgproc.threshold(imread, result, 125, 255, Imgproc.THRESH_BINARY);
        // 显示图片
        HighGui.imshow("result", result);
        HighGui.waitKey();
    }
}
