package org.firstinspires.ftc.teamcode.itay;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class practice extends LinearOpMode {
    static Servo grabberRight;
    static Servo grabberLeft;

    private static void setPosition(double heights) {
        grabberRight.setPosition(heights);
        grabberLeft. setPosition(heights);
    }
    @Override
    public void runOpMode() throws InterruptedException {
        // keeps the servos positions for ease of remembering
        final double[] heights = {0.7, 0.055, 0.06, 0.09, 0.13, 0.17};

        // region INITIALIZE THE SERVOS
        // getting the servos from the hardware map
        grabberRight = hardwareMap.servo.get("grabberRight");
        grabberLeft  = hardwareMap.servo.get("grabberLeft" );

        // make the servos go in the same direction
        grabberRight.setDirection(Servo.Direction.REVERSE);

        setPosition(heights[0]);
        // endregion

        boolean dpad;
        boolean lastest_dpad = false;
        boolean dpad_click;
        boolean is_in = true;
        boolean trigger;
        boolean lastest_trigger = false;

        waitForStart();
        if (isStopRequested()) { return; }
        resetRuntime();

        while (opModeIsActive()) {

            dpad = lastest_dpad;
            lastest_dpad = gamepad1.dpad_up || gamepad1.dpad_right || gamepad1.dpad_down || gamepad1.dpad_left;
            dpad_click = lastest_dpad && !dpad;

            trigger = lastest_trigger;
            lastest_trigger = (gamepad1.left_trigger > 0.05);

            if (dpad_click) { is_in = !is_in; }

            if (lastest_trigger) {
                setPosition(heights[1]);
            } else if(trigger) {
                setPosition(heights[0]);
            } else if (dpad_click){
                if      (gamepad1.dpad_up   ) { setPosition(heights[5]); }
                else if (gamepad1.dpad_right) { setPosition(heights[4]); }
                else if (gamepad1.dpad_down ) { setPosition(heights[3]); }
                else if (gamepad1.dpad_left ) { setPosition(heights[2]); }
            } else if (!is_in) {
                setPosition(heights[0]);
            }
        }
    }
}
