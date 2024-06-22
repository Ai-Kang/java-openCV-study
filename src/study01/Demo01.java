package study01;

import org.opencv.core.Mat;
import org.opencv.core.Size;

/**
 * @author: aikang
 * @create_time: 2024/6/22-下午3:38
 * @description: 引入opencv
 */
public class Demo01 {

    public static void main(String[] args) {
        // 加载C++库文件
        String property = System.getProperty("user.dir");
        System.load(property+"/lib/opencv/opencv_java4100.dll");
        Mat mat = Mat.eye(new Size(10, 10), 10);
        System.out.println(mat);

    }

}
