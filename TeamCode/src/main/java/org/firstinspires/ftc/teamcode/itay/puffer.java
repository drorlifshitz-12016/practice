package org.firstinspires.ftc.teamcode.itay;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp
public class puffer extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        DistanceSensor grabberDistanceToConeSensor = hardwareMap.get(DistanceSensor.class , "grabberDistanceToConeSensor");
        Servo grabber = hardwareMap.servo.get("grabber");

        waitForStart();
        if (isStopRequested()) { return; }
        resetRuntime();


        while (opModeIsActive()) {

            telemetry.addData("distance", grabberDistanceToConeSensor.getDistance(DistanceUnit.MM));
            telemetry.update();


        }
    }
}