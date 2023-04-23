package org.firstinspires.ftc.teamcode.ido;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class fullMovement extends LinearOpMode{




    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor frontRight = hardwareMap.dcMotor.get("frontRight");
        DcMotor frontLeft  = hardwareMap.dcMotor.get("frontLeft" );
        DcMotor backRight  = hardwareMap.dcMotor.get("backRight" );
        DcMotor backLeft   = hardwareMap.dcMotor.get("backLeft"  );

        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight .setDirection(DcMotorSimple.Direction.REVERSE);

        double y = gamepad1.left_stick_y;
        double x = gamepad2.left_stick_x;
        double t = gamepad1.right_stick_x;

        waitForStart();
        if(isStopRequested()){


            return;
        }
        resetRuntime();

        while (opModeIsActive()){
            frontRight.setPower(y+x+t);
            frontLeft .setPower(y-x-t);
            backRight .setPower(y-x+t);
            backLeft  .setPower(y+x-t);
        }

        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);

    }
}
