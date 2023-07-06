package org.firstinspires.ftc.teamcode.itay;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class ExtendArm extends LinearOpMode {

    // region POSITION CONSTANTS
    // the positions of the servo for both the in and out arm positions
    static final double IN_POSITION = 0.55;
    static final double OUT_POSITION = 0.04;
    // endregion

    // region SERVO VARIABLES
    static Servo armRight;
    static Servo armLeft;
    // endregion

    @Override

    public void runOpMode() throws InterruptedException {
        // region SERVO INITIALIZATION
        // get servos from the hardware map
        armRight = hardwareMap.servo.get("armRight");
        armLeft  = hardwareMap.servo.get("armLeft" );

        // set the right servo to be reverse
        armRight.setDirection(Servo.Direction.REVERSE);

        // set the servos to be in
        extendArm(0);
        // endregion

        // region AWAIT START
        waitForStart();
        if (isStopRequested()) return;
        resetRuntime();
        // endregion

        while (opModeIsActive()) {
            extendArm(gamepad1.right_trigger);
        }
    }

    static void extendArm(double position){
        armLeft .setPosition((OUT_POSITION - IN_POSITION) * position + IN_POSITION);
        armRight.setPosition((OUT_POSITION - IN_POSITION) * position + IN_POSITION);
    }

}

