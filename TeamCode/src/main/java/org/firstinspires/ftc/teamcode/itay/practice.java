package org.firstinspires.ftc.teamcode.itay;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class practice extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();
        if(isStopRequested()){
            return;
        }
        resetRuntime();
        DcMotor frontRight = hardwareMap.dcMotor.get("frontRight");
        DcMotor frontLeft = hardwareMap.dcMotor.get("frontLeft");
        DcMotor backRight = hardwareMap.dcMotor.get("backRight");
        DcMotor backLeft = hardwareMap.dcMotor.get("backLeft");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        while (opModeIsActive()){
                frontRight.setPower(gamepad1.right_trigger - gamepad1.left_trigger);
                frontLeft.setPower(gamepad1.right_trigger -  gamepad1.left_trigger);
                backRight.setPower(gamepad1.right_trigger - gamepad1.left_trigger);
                backLeft.setPower(gamepad1.right_trigger - gamepad1.left_trigger);


                double Powermotor_FR = gamepad1.left_stick_y + gamepad1.left_stick_x
                double Powermotor_FL = gamepad1.left_stick_y - gamepad1.left_stick_x
                double Powermotor_BR = gamepad1.left_stick_y - gamepad1.left_stick_x
                double Powermotor_BL =gamepad1.left_stick_y + gamepad1.left_stick_x 
       }
   }
}
