package org.firstinspires.ftc.teamcode.itay;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp
public class Test extends LinearOpMode {

    static DigitalChannel isOutSensor;

    static  Servo grabber;

    @Override
    public void runOpMode() throws InterruptedException {

        isOutSensor = hardwareMap.get(DigitalChannel.class, "grabberIsOutSensor");

        DistanceSensor grabberDistanceToConeSensor = hardwareMap.get(DistanceSensor.class , "grabberDistanceToConeSensor");

        grabber = hardwareMap.servo.get("grabber");

        final double grabPosition = 0.40;
        final double releasePosition = 0.18;


        boolean grabb_is_in;

        waitForStart();
          if (isStopRequested()) { return; }
          resetRuntime();



        while (opModeIsActive()) {

        if (grabberDistanceToConeSensor.getDistance(DistanceUnit.CM) < 8){
            grabber.setPosition(grabPosition);
        }
        else if (grabberDistanceToConeSensor.getDistance(DistanceUnit.CM) > 9){
            grabber.setPosition(releasePosition);
        }



        }
    }
}

