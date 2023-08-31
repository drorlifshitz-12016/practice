package org.firstinspires.ftc.teamcode.itay;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class swerve extends LinearOpMode {

    CRServo Servo_FR = hardwareMap.crservo.get("frontRightServo");
    CRServo Servo_FL = hardwareMap.crservo.get("frontLeftServo");
    CRServo Servo_BL = hardwareMap.crservo.get("backRightServo");
    CRServo Servo_BR = hardwareMap.crservo.get("backLeftServo");

    DcMotor frontRight = hardwareMap.dcMotor.get("frontRight");
    DcMotor frontLeft = hardwareMap.dcMotor.get("frontLeft");
    DcMotor backRight = hardwareMap.dcMotor.get("backRight");
    DcMotor backLeft = hardwareMap.dcMotor.get("backLeft");

    double speed;

    @Override
    public void runOpMode() throws InterruptedException {

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        if (isStopRequested()) {
            return;
        }
        resetRuntime();

        while (opModeIsActive()) {

            speed = gamepad1.left_trigger - gamepad1.right_trigger;
            frontRight.setPower(speed);
            frontLeft.setPower(speed);
            backLeft.setPower(speed);
            backRight.setPower(speed);

            // rotate the servos
            Servo_BL.setPower(0.6);
            Servo_BR.setPower(0.6);
            Servo_FL.setPower(0.6);
            Servo_FR.setPower(0.6);

            // wait for a short time before looping again
            sleep(50);
        }
    }
}
