package org.firstinspires.ftc.teamcode.itay;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp
public class grabber extends LinearOpMode {
    static Servo grabber;

    @Override
    public void runOpMode() throws InterruptedException {

        DistanceSensor grabberDistanceToConeSensor = hardwareMap.get(DistanceSensor.class , "grabberDistanceToConeSensor");

        grabber = hardwareMap.servo.get("grabber");

         final double grabPosition = 0.40;
         final double releasePosition = 0.18;

        grabber.setPosition(releasePosition);

        waitForStart();
        if (isStopRequested()) {return;}
        resetRuntime();

        grabber.setPosition(releasePosition);

        while (opModeIsActive()){




        }
    }
}
