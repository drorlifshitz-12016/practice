package org.firstinspires.ftc.teamcode.swerve.dependencies;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

public class swerveDrive {
    private static boolean active = false;

    // region IMU
    private static BNO055IMU imu;
    private static double robotAngle;
    private static void updateRobotAngle() {
        // get the angle from the imu and normalize it to be between 0 and 1 in the wanted direction
        robotAngle = 1 - (imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle / 2 / Math.PI + 1) % 1;
    }
    // endregion

    // region MODULES
    private static swerveModule moduleFR;
    private static swerveModule moduleFL;
    private static swerveModule moduleBR;
    private static swerveModule moduleBL;
    // endregion

    // region MOVEMENT VECTORS
    private static final Vector vectorFR = new Vector();
    private static final Vector vectorFL = new Vector();
    private static final Vector vectorBR = new Vector();
    private static final Vector vectorBL = new Vector();
    // endregion
    public static void initialize(HardwareMap hm){
        // region IMU
        // get the imu
        imu = hm.get(BNO055IMU .class, "imu");

        // create the parameter list
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;

        // set the parameters onto the imu
        imu.initialize(parameters);
        // endregion

        // region MODULES
        moduleFR = new swerveModule(hm, "frontRight", 0.207, 0.125);
        moduleFL = new swerveModule(hm, "frontLeft" , 0.8  , 0.875);
        moduleBR = new swerveModule(hm, "backRight" , 0.605, 0.375);
        moduleBL = new swerveModule(hm, "backLeft"  , 0.635, 0.625);
        // endregion
    }

    // make me into a separated thread please ;(
    public static void start(Gamepad gamepad){
        active = true;
        while (active){
            updateRobotAngle();

            // region UPDATE THE MOVEMENT VECTORS
            vectorFR.set(gamepad.left_stick_x, gamepad.left_stick_y);
            vectorFL.set(gamepad.left_stick_x, gamepad.left_stick_y);
            vectorBR.set(gamepad.left_stick_x, gamepad.left_stick_y);
            vectorBL.set(gamepad.left_stick_x, gamepad.left_stick_y);
            // endregion

            // region ORIENT IT TO THE FIELD
            vectorFR.addAngle(robotAngle);
            vectorFL.addAngle(robotAngle);
            vectorBR.addAngle(robotAngle);
            vectorBL.addAngle(robotAngle);
            // endregion

            // region ADD TURNING FORCES
            vectorFR.add( gamepad.right_stick_x,  gamepad.right_stick_x);
            vectorFL.add( gamepad.right_stick_x, -gamepad.right_stick_x);
            vectorBR.add(-gamepad.right_stick_x,  gamepad.right_stick_x);
            vectorBL.add(-gamepad.right_stick_x, -gamepad.right_stick_x);
            // endregion

            // region NORMALIZE THE MOVEMENT VECTORS
            double maxStrength = Math.max(
                    Math.max(vectorFR.getLength(),
                             vectorFL.getLength()),
                    Math.max(vectorBR.getLength(),
                             vectorBL.getLength())
            );

            // we can only set the motor power between -1 and 1
            if (maxStrength > 1){
                vectorFR.normalize(maxStrength);
                vectorFL.normalize(maxStrength);
                vectorBR.normalize(maxStrength);
                vectorBL.normalize(maxStrength);
            }
            // endregion

            // region APPLY THE MOVEMENT VECTORS
            if (maxStrength > 0.1) {
                moduleFR.setByVector(vectorFR);
                moduleFL.setByVector(vectorFL);
                moduleBR.setByVector(vectorBR);
                moduleBL.setByVector(vectorBL);
            } else {
                moduleFR.idle();
                moduleFL.idle();
                moduleBR.idle();
                moduleBL.idle();
            }
            // endregion

            // region UPDATE THE MODULES
            moduleFR.update();
            moduleFL.update();
            moduleBR.update();
            moduleBL.update();
            // endregion
        }
    }
    public static void stop(){
        active = false;
    }
}
