package org.firstinspires.ftc.teamcode.itay;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class Test extends LinearOpMode {

    Servo grabberRight;
    Servo grabberLeft;
    final double[] heights = {0.7, 0.055, 0.06, 0.09, 0.13, 0.17};
    @Override
    public void runOpMode() throws InterruptedException {
        // region initialize servos
        grabberRight = hardwareMap.servo.get("grabberRight");
        grabberLeft  = hardwareMap.servo.get("grabberLeft");

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

        waitForStart();
        if (isStopRequested()) { return; }
        resetRuntime();

        while (opModeIsActive()) {

            // region update the trigger variables
            previous_trigger = trigger_left;
            trigger_left = gamepad1.left_trigger > 0.8;
            is_trigger_released = !trigger_left && previous_trigger;
            // endregion

            // region update the dpad variables
            previous_dpad = dpad;
            dpad = gamepad1.dpad_up || gamepad1.dpad_down || gamepad1.dpad_right || gamepad1.dpad_left;
            dpad_click = !previous_dpad && dpad;
            // endregion

            if (dpad_click) { is_in = !is_in; }

            if (gamepad1.left_trigger > 0.08) {
                grabbers(heights[1]);
                is_in = false;
            } else if (is_trigger_released) {
                grabbers(heights[0]);
                is_in = true;
            } else {
                if (dpad_click) {
                    if (is_in) {
                        if      (gamepad1.dpad_up   ) { grabbers(heights[5]); }
                        else if (gamepad1.dpad_right) { grabbers(heights[4]); }
                        else if (gamepad1.dpad_down ) { grabbers(heights[3]); }
                        else if (gamepad1.dpad_left ) { grabbers(heights[2]); }
                    } else {
                        grabbers(heights[0]);
                    }
                }
            }
        }
    }

    private void grabbers(double height){
        grabberLeft. setPosition(height);
        grabberRight.setPosition(height);
    }
}





