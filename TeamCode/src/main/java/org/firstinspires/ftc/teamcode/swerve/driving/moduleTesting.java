package org.firstinspires.ftc.teamcode.swerve.driving;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Base64;

@TeleOp
public class moduleTesting extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor motor = hardwareMap.dcMotor.get("motor");
        CRServo servo = hardwareMap.get(CRServo.class, "servo");


        waitForStart();
        if(isStopRequested()) { return; }
        resetRuntime();

        while (opModeIsActive()){
            servo.setPower(1);
        }
    }
}
