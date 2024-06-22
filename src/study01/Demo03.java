package study01;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


/**
 * @author: aikang
 * @create_time: 2024/6/22-18:56
 * @description:
 */
public class Demo03 {
    static {
        // 加载C++库文件
        String property = System.getProperty("user.dir");
        System.load(property + "/lib/opencv/opencv_java4100.dll");
    }

    /**
     * 图像的四个点对应的变换
     * @param args
     */
    public static void main(String[] args) {
        // 读取文件
        Mat m1 = Imgcodecs.imread("image/person_and_dog_min.png");
        // 定义结果
        Mat m3 = new Mat();
        // 定义输入点变换点
        Point[] p1 = new Point[4];
        p1[0] = new Point(0, 0);
        p1[1] = new Point(1920, 0);
        p1[2] = new Point(0, 1200);
        p1[3] = new Point(1920, 1200);

        Point[] p2 = new Point[4];
        p2[0] = new Point(0, 0);
        p2[1] = new Point(1920, 0);
        p2[2] = new Point(10, 1200);
        p2[3] = new Point(1920, 1200);

        // 创建变换矩阵
        MatOfPoint2f f1 = new MatOfPoint2f(p1);
        MatOfPoint2f f2 = new MatOfPoint2f(p2);
        // 获取矩阵变换
        Mat m2 = Imgproc.getPerspectiveTransform(f1, f2);
        // 开始变换
        Imgproc.warpPerspective(m1, m3, m2, m1.size());
        HighGui.imshow("png", m3);
        HighGui.waitKey(0);
    }
}
