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




        waitForStart();
        if(isStopRequested()){
            return;
        }
        resetRuntime();

        while (opModeIsActive()){
            grabberRight.setPosition(0.75);
            grabberLeft.setPosition(0.75);
        }



    }
}
