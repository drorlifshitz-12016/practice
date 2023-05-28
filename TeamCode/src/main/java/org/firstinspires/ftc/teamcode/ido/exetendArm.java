package org.firstinspires.ftc.teamcode.ido;
import com.qualcomm.robotcore.hardware.Servo;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class exetendArm extends LinearOpMode {



    @Override
    public void runOpMode() throws InterruptedException {

        Servo grabberRight = hardwareMap.servo.get("grabberRight");
        Servo grabberLeft  = hardwareMap.servo.get("grabberLeft" );
        grabberLeft.setDirection(Servo.Direction.REVERSE);
        Servo grabber = hardwareMap.servo.get("grabber");
        Servo armRight = hardwareMap.servo.get("armRight");
        Servo armLeft = hardwareMap.servo.get("armLeft" );


        armRight.setDirection(Servo.Direction.REVERSE);

        exetendArm





        grabber.setPosition(0);
        waitForStart();
        grabberRight.setPosition(0);
        grabberRight.setPosition(0);
        grabberLeft.setPosition(0);
        if(isStopRequested()){
            return;
        }
        resetRuntime();

        while (opModeIsActive()){
            grabberRight.setPosition(0);
            grabberRight.setPosition(0);
            grabberLeft.setPosition(0);
            grabber.setPosition(0);


            while (gamepad1.left_trigger) {
                grabberRight.setPosition(0.85);
                grabberLeft.setPosition(0.85);
                armLeft.setPosition(gamepad1.left_trigger * (-0.51) + 0.55);
                armRight.setPosition(gamepad1.left_trigger * (-0.51) + 0.55);
            }

            while (gamepad1.right_trigger > 0) {
                grabber.setPosition(0.6);
            }


        }


    }
}
