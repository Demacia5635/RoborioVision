// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utils;

import java.util.HashMap;

import org.opencv.core.Mat;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;


public abstract class VisionProcessor extends Thread{

    private static final long secondsToNano = (long)1e9;

    private final UsbCamera camera;
    private final CvSink video;
    private final CvSource outputStream;
    private final HashMap<String,Double> values;
    private final int fps;
    private boolean on;

    public VisionProcessor(int cameraWidth, int cameraHeight, int fps){
        camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setResolution(cameraWidth, cameraHeight);
        camera.setFPS(fps);
        video = CameraServer.getInstance().getVideo();
        outputStream = CameraServer.getInstance().putVideo("output", cameraWidth, cameraHeight);
        values = new HashMap<String,Double>();
        this.fps = fps;
        on = false;
    }

    public VisionProcessor(int cameraWidth, int cameraHeight){
        camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setResolution(cameraWidth, cameraHeight);
        camera.setFPS(30);
        video = CameraServer.getInstance().getVideo();
        outputStream = CameraServer.getInstance().putVideo("output", cameraWidth, cameraHeight);
        values = new HashMap<String,Double>();
        fps = 30;
        on = false;
    }

    /**
     * return your calculation with {@link #put}
     */
    public abstract void calculate(Mat frame);

    public void put(String key, double value) {
        values.put(key, value);
    }

    public double get(String key) {
        return values.get(key);
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        Mat frame = new Mat();
        while (!isInterrupted()){

            long now = System.nanoTime();

            if (now - lastTime < secondsToNano / fps) continue;
            lastTime = now;
            
            if (video.grabFrame(frame) == 0) continue;
            
            outputStream.putFrame(frame);

            if (!on) continue;

            calculate(frame);
        }
    }

    public void enable() {
        on = true;
    }

    public void disable() {
        on = false;
    }
}
