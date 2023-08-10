package org.firstinspires.ftc.teamcode.swerve;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

import org.firstinspires.ftc.teamcode.swerve.dependencies.*;


@TeleOp
public class swerveDrive extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // region IMU
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);

        double robotAngle;
        // endregion

        // region MODULES
        swerveModule moduleFR = new swerveModule(hardwareMap, "frontRight", 0.793, 0.125);
        swerveModule moduleFL = new swerveModule(hardwareMap, "frontLeft" , 0.2  , 0.875);
        swerveModule moduleBR = new swerveModule(hardwareMap, "backRight" , 0.395, 0.375);
        swerveModule moduleBL = new swerveModule(hardwareMap, "backLeft"  , 0.365, 0.625);
        // endregion

        // region VECTOR INITIALIZATION
        Vector vectorFR = new Vector();
        Vector vectorFL = new Vector();
        Vector vectorBR = new Vector();
        Vector vectorBL = new Vector();
        // endregion

        waitForStart();
        if(isStopRequested()) { return; }
        resetRuntime();

        while (opModeIsActive()){
            // region UPDATE THE MOVEMENT VECTORS
            vectorFR.set(gamepad1.left_stick_x, gamepad1.left_stick_y);
            vectorFL.set(gamepad1.left_stick_x, gamepad1.left_stick_y);
            vectorBR.set(gamepad1.left_stick_x, gamepad1.left_stick_y);
            vectorBL.set(gamepad1.left_stick_x, gamepad1.left_stick_y);
            // endregion

            // region ORIENT IT TO THE FIELD
            robotAngle = getRobotAngle();

            vectorFR.addAngle(robotAngle);
            vectorFL.addAngle(robotAngle);
            vectorBR.addAngle(robotAngle);
            vectorBL.addAngle(robotAngle);
            // endregion

            // region ADD TURNING FORCES
            vectorFR.add( gamepad1.right_stick_x,  gamepad1.right_stick_x);
            vectorFL.add( gamepad1.right_stick_x, -gamepad1.right_stick_x);
            vectorBR.add(-gamepad1.right_stick_x,  gamepad1.right_stick_x);
            vectorBL.add(-gamepad1.right_stick_x, -gamepad1.right_stick_x);
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
                moduleFR.stop();
                moduleFL.stop();
                moduleBR.stop();
                moduleBL.stop();
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

    // region HELPERS
    private static BNO055IMU imu;
    private static double getRobotAngle() {
        // get the angle from the imu
        double angle = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle / 2 / Math.PI;

        // normalize the angle
        if (angle < 0) {
            angle += 1;
        }
        angle = 1 - angle;

        return angle;
    }
    private static double maxLength(Vector v1, Vector v2, Vector v3, Vector v4){
        return Math.max(Math.max(v1.getLength(), v2.getLength()), Math.max(v3.getLength(), v4.getLength()));
    }
    // endregion
}

