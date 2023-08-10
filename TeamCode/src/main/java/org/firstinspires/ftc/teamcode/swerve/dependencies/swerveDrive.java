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
        // get the angle from the imu
        robotAngle = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle / 2 / Math.PI;

        // normalize the angle
        if (robotAngle < 0) {
            robotAngle += 1;
        }
        robotAngle = 1 - robotAngle;
    }
    // endregion

    // region MODULES
    private static swerveModule moduleFR;
    private static swerveModule moduleFL;
    private static swerveModule moduleBR;
    private static swerveModule moduleBL;
    // endregion

    // region MOVEMENT VECTOR
    private static final Vector vectorFR = new Vector();
    private static final Vector vectorFL = new Vector();
    private static final Vector vectorBR = new Vector();
    private static final Vector vectorBL = new Vector();
    // endregion
    public static void initialize(HardwareMap hm){
        // region IMU
        imu = hm.get(BNO055IMU .class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);
        // endregion

        // region MODULES
        moduleFR = new swerveModule(hm, "frontRight", 0.793, 0.125);
        moduleFL = new swerveModule(hm, "frontLeft" , 0.2  , 0.875);
        moduleBR = new swerveModule(hm, "backRight" , 0.395, 0.375);
        moduleBL = new swerveModule(hm, "backLeft"  , 0.365, 0.625);
        // endregion
    }

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
            double maxLength = maxLength(vectorFR, vectorFL, vectorBR, vectorBL);

            if (maxLength > 1){
                vectorFR.normalize(maxLength);
                vectorFL.normalize(maxLength);
                vectorBR.normalize(maxLength);
                vectorBL.normalize(maxLength);
            }
            // endregion

            // region APPLY THE MOVEMENT VECTORS
            if (maxLength > 0.1) {
                moduleFR.setVector(vectorFR);
                moduleFL.setVector(vectorFL);
                moduleBR.setVector(vectorBR);
                moduleBL.setVector(vectorBL);
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

    // region HELPERS
    private static double maxLength(Vector v1, Vector v2, Vector v3, Vector v4){
        return Math.max(Math.max(v1.getLength(), v2.getLength()), Math.max(v3.getLength(), v4.getLength()));
    }
    // endregion
}
