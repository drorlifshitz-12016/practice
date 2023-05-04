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

    @Override
    public void runOpMode() throws InterruptedException {

        isOutSensor = hardwareMap.get(DigitalChannel.class, "grabberIsOutSensor");

        boolean grabb_is_in;

        waitForStart();
          if (isStopRequested()) { return; }
          resetRuntime();



        while (opModeIsActive()) {

            grabb_is_in = !isOutSensor.getState();
            telemetry.addData("are you in",grabb_is_in);
            telemetry.update();
        }
    }
}

