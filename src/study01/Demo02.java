package study01;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * @author: aikang
 * @create_time: 2024/6/22-17:01
 * @description:
 */
public class Demo02 {
    static {
        // 加载C++库文件
        String property = System.getProperty("user.dir");
        System.load(property + "/lib/opencv/opencv_java4100.dll");
    }

    public static void main(String[] args) {
        String imagePath = "image/person_and_dog_min.png";
        // 彩色图
//        test1(imagePath);
        // 灰度图
//        test2(imagePath);
        // 生成矩阵
//        test3();
        test4();
    }

    /**
     * 直接输入图片
     *
     * @param imagePath
     */
    private static void test1(String imagePath) {
        Mat personAndDogMat = Imgcodecs.imread(imagePath);
        HighGui.imshow("lena", personAndDogMat);
        // 等待任意键退出
        HighGui.waitKey(0);
    }

    /**
     * 灰度图输出
     *
     * @param imagePath
     */
    private static void test2(String imagePath) {
        /**
         * 灰度图： IMREAD_GRAYSCALE
         */
        Mat personAndDogMat = Imgcodecs.imread(imagePath, Imgcodecs.IMREAD_GRAYSCALE);
        HighGui.imshow("lena", personAndDogMat);
        // 把文件输出
        Imgcodecs.imwrite("image/person_and_dog_min_GRAYSCALE.png", personAndDogMat);
        // 等待任意键退出
        HighGui.waitKey(0);
    }

    /**
     * 测试生成矩阵
     *  eye:
     *      [  1,   0,   0;
     *         0,   1,   0;
     *         0,   0,   1]
     *  zeros:
     *     [  0,   0,   0;
     *        0,   0,   0;
     *        0,   0,   0]
     *  ones:
     *     [  1,   1,   1;
     *        1,   1,   1;
     *        1,   1,   1]
     */
    private static void test3() {
        Mat m1 = Mat.ones(3, 3, CvType.CV_8UC1);

        System.out.println("m1 = " + m1.dump());
    }

    /**
     * 简单绘制
     */
    private static void test4() {
        Mat m1 = Mat.ones(800,1080, CvType.CV_8UC1);
        /*
         * img –图像。 pt1 – 线段的第一点。 pt2 – 线段的第二个点。 color – 线条颜色。 thickness – 线条粗细。
         */
        Imgproc.line(m1, new Point(1, 10), new Point(10, 10), new Scalar(140, 11, 24), 2);
        /*
        * img –图像。 pt1 – 矩形的顶点。 pt2 – 与 pt1 相对的矩形的顶点。 color – 矩形颜色或亮度（灰度图像）。 thickness – 构成矩形的线条的粗细。负值（如 #FILLED）表示函数必须绘制一个填充矩形。
        * */
        Imgproc.rectangle(m1, new Point(15, 10), new Point(30, 30), new Scalar(140, 11, 24), 1);
        /*
         * img – 绘制圆圈的图像。 center – 圆心。 radius – 圆的半径。 color – 圆形颜色。 thickness – 圆轮廓的厚度，如果为正。负值（如 #FILLED）表示要绘制一个实心圆。 lineType – 圆形边界的类型。请参阅 #LineTypes shift – 中心坐标和半径值中的小数位数。
         */
        Imgproc.circle(m1,new Point(40,40),10,new Scalar(140, 11, 24),1);

        Imgcodecs.imwrite("image/tets_line.png", m1);
    }

}
