# Java集成opencv学习
## openCV环境配置
### maven配置
```xml
<!--        1：在pom中引入opencv的依赖jar-->
        <dependency>
            <groupId>org.opencv</groupId>
            <artifactId>opencv</artifactId>
            <version>4.1.0.0</version>
            <scope>system</scope>
            <systemPath>${project.basedir}/lib/opencv/opencv-4100.jar</systemPath>
        </dependency>
```
### java文件中简单使用
```java
package com.aikang.study01;

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
```

## opencv模块介绍
```text
Core:核心模块，包括基础数据结构、绘图函数、数组操作函数等
Imgproc：图像处理模块，包括图像滤波、几何变换、直方图、形态学处理、边缘检测、特征及目标检测等
Highgui：顶层GUI模块，包括用户界面、读/写图像及视频等
video：视频模块，包括运动分析、 运动估计、背景分离、对象跟踪等视频处理相关内容。 
Calib3d：Calibration(校准)和3D这两个词的组合缩写。这个模块主要是相机校准和三维重建相关的内容，包括基本的多视角几何算法，单个立体摄像头标定，物体姿态估计，立体相似性算法， 3D信息的重建
contrib：也就是Contributed/Experimental Stuf(贡献、实验部分)的缩写， 该模块包含了一些最近添加的不太稳定的可选功能，不用去多管。2.4.8里的这个模块有新型人脸识别，立体匹配，人工视网膜模型等技术。
features2d：特征检测和描述、特征检测器（Feature Detectors）通用接口、描述符提取器（Descriptor Extractors）通用接口、描述符匹配器（Descriptor Matchers）通用接口、通用描述符（Generic Descriptor）匹配器通用接口、关键点绘制函数和匹配功能绘制函数
flann：Fast Library for Approximate Nearest Neighbors，高维的近似近邻快速搜索算法库，包含两个部分，快速近似最近邻搜索、聚类
gpu：也就是高层GUI图形用户界面，包含媒体的I / O输入输出，视频捕捉、图像和视频的编码解码、图形交互界面的接口等内容 
ml：Machine Learning，机器学习模块， 基本上是统计模型和分类算法，包含如下内容：
    统计模型 （Statistical Models）
    一般贝叶斯分类器 （Normal Bayes Classifier）
    K-近邻 （K-NearestNeighbors）
    支持向量机 （Support Vector Machines）
    决策树 （Decision Trees）
    提升（Boosting）
    梯度提高树（Gradient Boosted Trees）
    随机树 （Random Trees）
    超随机树 （Extremely randomized trees）
    期望最大化 （Expectation Maximization）
    神经网络 （Neural Networks）
    MLData
nonfree：也就是一些具有专利的算法模块 ，包含特征检测和`GPU`相关的内容。最好不要商用，可能会被告
objdetect： 目标检测模块，包含Cascade Classification（级联分类）和Latent SVM这两个部分
ocl：即`OpenCL-accelerated Computer Vision`，运用`OpenCL`加速的计算机视觉组件模块。
photo：也就是Computational Photography，包含图像修复和图像去噪两部分。 
stitching：images stitching，图像拼接模块，包含如下部分：
    拼接流水线
    特点寻找和匹配图像
    估计旋转
    自动校准
    图片歪斜
    接缝估测
    曝光补偿
    图片混合
superres： `SuperResolution`，超分辨率技术的相关功能模块。 
ts：`opencv`测试相关代码，不用去管他。 
videostab：Video stabilization，视频稳定相关的组件。
```
## 数字图像的本质
```text
数字图像的本质是【矩阵】
opencv中的Mat类（Matrix矩阵）
java中调用opencv函数需要加入模块名称，比如：
    显示图像：HighGui.imshow("lena",src);
    读取图像：Mat src = Imgcodecs.imread(lena.jpg);
    输出图像：Imgcodecs.imwrite("lena.jpg",mat);
要创建每个点只有一个通道的简单矩阵，可以用三个静态函数：zeros、eye、ones;
    CV_8UC1: 8位Unsigned（0-255）Channel(通道)=1（灰度图）
    CV_8UC3: 8位Unsigned Channel = 3(彩色图像)
    CV_64FC3: 64位浮点数；Channel = 3(彩色图)
    通道：彩色（RGB三个通道）有时候还有alpha通道(透明度)，形成(RGBA)
```
```java
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
        test3();
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

}
```
## 图形绘制
```text
绘制线：Imgproc.line();
绘制矩形：Imgproc.rectangle();
绘制圆：Imgproc.circle();
```
```java
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
```
