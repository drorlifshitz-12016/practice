package org.firstinspires.ftc.teamcode.ido;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp
public class sideways extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor frontRight = hardwareMap.dcMotor.get("frontRight");
        DcMotor frontLeft  = hardwareMap.dcMotor.get("frontLeft" );
        DcMotor backRight  = hardwareMap.dcMotor.get("backRight" );
        DcMotor backLeft   = hardwareMap.dcMotor.get("backLeft"  );

           backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
           backRight .setDirection(DcMotorSimple.Direction.REVERSE);


        waitForStart();
        if(isStopRequested()){


            return;
        }
        resetRuntime();

        while (opModeIsActive()){
            frontRight.setPower(gamepad1.left_stick_x);
            frontLeft .setPower(gamepad1.left_stick_x);
            backRight .setPower(- gamepad1.left_stick_x);
            backLeft  .setPower(gamepad1.left_stick_x);
        }

        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);

    }
}
