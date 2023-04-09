package org.firstinspires.ftc.teamcode.itay;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class ExtendArm extends LinearOpMode {

    static final double IN_POSITION = 0.55;
    static final double OUT_POSITION = 0.04;

    static Servo armRight;
    static Servo armLeft;

    @Override
    public void runOpMode() throws InterruptedException {
        armRight = hardwareMap.servo.get("armRight");
        armLeft  = hardwareMap.servo.get("armLeft" );

        armRight.setDirection(Servo.Direction.REVERSE);

        extendArm(0);

        waitForStart();
        if (isStopRequested()) {
            return;
        }

        resetRuntime();

        while (opModeIsActive()) {

            extendArm(gamepad1.right_trigger);

        }
    }

    private static void extendArm(double position){
        armLeft .setPosition((OUT_POSITION - IN_POSITION) * position + IN_POSITION);
        armRight.setPosition((OUT_POSITION - IN_POSITION) * position + IN_POSITION);
    }

}

