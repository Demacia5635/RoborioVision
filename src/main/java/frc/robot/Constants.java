// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    //Vision
    public static final int cameraDefaultFps = 20;
    public static final int kernelSize = 5;

    public static final int cameraHeight = 480;//in pixels
    public static final int cameraWidth = 640;//in pixels
    public static final int angleOfVision = 50;//in degrees
    public static final double ballRadius = 7.86;//cm

    public static final int lowerH = 20;
    public static final int lowerS = 40;
    public static final int lowerV = 80;

    public static final int upperH = 30;
    public static final int upperS = 255;
    public static final int upperV = 255;

}
