package org.firstinspires.ftc.teamcode.itay;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp
public class Test extends LinearOpMode {

    Servo grabberRight;
    Servo grabberLeft;

    static Servo grabber;

    final double[] heights = {0.7, 0.055, 0.06, 0.09, 0.13, 0.17};
    @Override
    public void runOpMode() throws InterruptedException {
        // region initialize servos
        grabberRight = hardwareMap.servo.get("grabberRight");
        grabberLeft  = hardwareMap.servo.get("grabberLeft");

        grabber = hardwareMap.servo.get("grabber");

        DistanceSensor grabberDistanceToConeSensor = hardwareMap.get(DistanceSensor.class , "grabberDistanceToConeSensor");

        grabberRight.setDirection(Servo.Direction.REVERSE);

        grabbers(heights[0]);


        // endregion

        // region dpad state keeping variables
        boolean dpad = false;
        boolean previous_dpad;
        boolean dpad_click;
        // endregion

       // region trigger state keeping variables
       boolean trigger_left = false;
       boolean previous_trigger;
       boolean is_trigger_released;
       // endregion

       boolean is_in = true;

        final double grabPosition = 0.35;

         waitForStart();
          if (isStopRequested()) { return; }
          resetRuntime();
//
        while (opModeIsActive()) {

            if(gamepad1.a){
                grabbers(heights[3]);
            }


        }
    }
    private void grabbers(double height){
        grabberLeft. setPosition(height);
        grabberRight.setPosition(height);
    }
}

