package org.firstinspires.ftc.teamcode.itay;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp
public class puffer extends LinearOpMode {

    static Servo pufferServo;
     static Servo rightServo ;
     static Servo leftServo  ;

    double inPosition  = 0.16;
    double midPosition = 0.5 ;
    double outPosition = 0.95;

    double autonomousOutPosition = 0.88;
    double grabPosition    = 0.3;
    double releasePosition = 0.2;

    @Override
    public void runOpMode() throws InterruptedException {

        pufferServo = hardwareMap.servo.get("puffer");
        rightServo  = hardwareMap.servo.get("pufferRight");
        leftServo   = hardwareMap.servo.get("pufferLeft" );

        waitForStart();
        if (isStopRequested()) { return; }
        resetRuntime();


        while (opModeIsActive()) {

        if (gamepad1.a){
            pufferServo.setPosition(grabPosition);
        }
        if (gamepad1.b){
            pufferServo.setPosition(releasePosition);
        }

        }
    }
}