package org.firstinspires.ftc.teamcode.itay;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class Test extends LinearOpMode {

    public static double Odometry(double IMU,double encoderX, double encoderY){
        //x = האיקס שיגיע מהחיישנים
        //y = הוואי שיגיע מהחיישנים
        double x = encoderX * (3 * Math.PI);
        double y = encoderY * (3 * Math.PI);
        double[] encoderi = {x, y};
        return 0;
    }

    @Override
    public void runOpMode() {

        //                      | CM |
        final double wheel_speen = 3 * Math.PI;

        // region GET DRIVETRAIN MOTORS
        DcMotor frontRight = hardwareMap.dcMotor.get("frontRight");
        DcMotor frontLeft  = hardwareMap.dcMotor.get("frontLeft" );
        DcMotor backRight  = hardwareMap.dcMotor.get("backRight" );
        DcMotor backLeft   = hardwareMap.dcMotor.get("backLeft"  );
        // endregion

        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // reversing the right motors in order to have intuition for their movement direction
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight .setDirection(DcMotorSimple.Direction.REVERSE);


        // region WAIT FOR START
        waitForStart();
        if (isStopRequested()) {return;}
        resetRuntime();
        // endregion


        while (opModeIsActive()) {
            frontRight.setPower(gamepad1.a ? 1: 0);
            frontLeft.setPower (gamepad1.a ? 1: 0);
            backRight.setPower (gamepad1.a ? 1: 0);
            backLeft.setPower  (gamepad1.a ? 1: 0);


        }
    }
}
