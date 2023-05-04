package org.firstinspires.ftc.teamcode.yitzhak;


import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

@TeleOp
public class fieldOrianted extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor frontRight = hardwareMap.dcMotor.get("frontRight");
        DcMotor frontLeft  = hardwareMap.dcMotor.get("frontLeft" );
        DcMotor backRight  = hardwareMap.dcMotor.get("backRight" );
        DcMotor backLeft   = hardwareMap.dcMotor.get("backLeft"  );
        BNO055IMU imu      = hardwareMap.get(BNO055IMU.class, "imu");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft .setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        if(isStopRequested()){ return; }
        resetRuntime();
        
        double L
        double t;
        double x1;
        double y1;
        double x2;
        double y2;
        double a;
        double b = 0;

        double angle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle;

        while (opModeIsActive()){
            y1 = -gamepad1.left_stick_y;
            x1 =  gamepad1.left_stick_x;
            t =  gamepad1.right_stick_x;
            L = Math.sqrt(x1 * x1 + y1 * y1);
            a = Math.atan2(y1, x1);
           y2 = L * (Math.sin(a + b));
           x2 = L * (Math.cos(a + b));

            // region SECOND PART
            double fR = (y1-x1-t);
            double fL = (y1+x1+t);
            double bR = (y1+x1-t);
            double bL = (y1-x1+t);

            double max1 = Math.max(fR , fL);
            double max2 = Math.max(bR , bL);
            double maxF = Math.max(max2 , max1);

            fR /= maxF;
            fL /= maxF;
            bR /= maxF;
            bL /= maxF;

            frontRight.setPower(fR);
            frontLeft .setPower(fL);
            backRight .setPower(bR);
            backLeft  .setPower(bL);
            // endregion
        }
    }
}
