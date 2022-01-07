// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.vision;

import org.opencv.core.Scalar;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

/** Add your docs here. */
public class Vars {
    protected static Scalar lowerHSV = new Scalar(SmartDashboard.getNumber("calibratiob-lower-h", Constants.lowerH),
        SmartDashboard.getNumber("calibratiob-lower-s", Constants.lowerS),
        SmartDashboard.getNumber("calibratiob-lower-v", Constants.lowerV));
    protected static Scalar upperHSV = new Scalar(SmartDashboard.getNumber("calibratiob-upper-h", Constants.upperH),
            SmartDashboard.getNumber("calibratiob-upper-s", Constants.upperS),
            SmartDashboard.getNumber("calibratiob-upper-v", Constants.upperV));

    protected static double angleOfVision = Math.toRadians(Constants.angleOfVision);
    protected static int cameraHeight = Constants.cameraHeight;
    protected static int cameraWidth = Constants.cameraWidth;

    protected static double ballRadius = Constants.ballRadius;
}
