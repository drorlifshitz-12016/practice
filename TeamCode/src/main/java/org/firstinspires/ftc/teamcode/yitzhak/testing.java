package org.firstinspires.ftc.teamcode.yitzhak;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class testing extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor frontRight = hardwareMap.dcMotor.get("frontRight");
        DcMotor frontLeft  = hardwareMap.dcMotor.get("frontLeft" );
        DcMotor backRight  = hardwareMap.dcMotor.get("backRight" );
        DcMotor backLeft   = hardwareMap.dcMotor.get("backLeft"  );

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft .setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        if(isStopRequested()){
            return;
        }
        resetRuntime();

        while (opModeIsActive()){
            frontRight.setPower(gamepad1.left_stick_y);
            frontLeft .setPower(gamepad1.left_stick_y);
            backRight .setPower(gamepad1.left_stick_y);
            backLeft  .setPower(gamepad1.left_stick_y);
        }

    }
}
