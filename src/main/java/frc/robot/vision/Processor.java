package frc.robot.vision;

import java.util.LinkedList;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;

import frc.robot.Constants;
import frc.robot.utils.VisionProcessor;

public class Processor extends VisionProcessor{

    public static final Mat kernel = Mat.ones(Constants.kernelSize, Constants.kernelSize, CvType.CV_32F);
    
    public Processor(){
        super(Vars.cameraWidth, Vars.cameraHeight);
    }

    /**
     * calculates the distance to the ball
     * @param radius the radius of the ball in pixels
     * @return the distance in the unit of {@link Vars#ballRadius}
     */
    private double distance(double radius){
        return Vars.ballRadius / Math.sin(radius * Vars.angleOfVision / Vars.cameraWidth);
    }

    /**
     * calculates the angle to the ball
     * @param x the x value of the center ball in the image
     * @return the angle to the ball in degrees
     */
    private double angle(double x){
        return (Vars.cameraWidth / 2 - x) * Math.toDegrees(Vars.angleOfVision) / Vars.cameraWidth;
    }

    @Override
    public void calculate(Mat frame) {

        LinkedList<MatOfPoint> contours = new LinkedList<MatOfPoint>();
        Mat mask = new Mat();
        Point center = new Point(0,0);
        float[] radius = new float[1];

        Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGR2HSV);
        Core.inRange(frame, Vars.lowerHSV, Vars.upperHSV, mask);
        Imgproc.erode(mask, mask, kernel);
        Imgproc.dilate(mask, mask, kernel);

        Imgproc.findContours(mask, contours, null, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        MatOfPoint contour = new MatOfPoint();
        double area = 0;

        for (MatOfPoint con : contours){
            double newArea = Imgproc.contourArea(con);
            if (newArea > area) {
                contour = con;
                area = newArea;
            }
        }
        
        MatOfPoint2f con = new MatOfPoint2f();
        contour.convertTo(con, CvType.CV_32F);
        Imgproc.minEnclosingCircle(con, center, radius);

        if (radius[0] <= 10) return;
        
        put("distance", distance(radius[0]));
        put("angle", angle(center.x));
    }
}
