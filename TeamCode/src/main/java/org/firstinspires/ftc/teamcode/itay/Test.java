package org.firstinspires.ftc.teamcode.itay;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class Test extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Servo armRight = hardwareMap.servo.get("armRight");
        Servo armLeft = hardwareMap.servo.get("armLeft");

        waitForStart();
        if (isStopRequested()) {
            return;
        }
        resetRuntime();

        while (opModeIsActive()) {

            telemetry.addData("servo position", armRight.getPosition());
            telemetry.update();

            armLeft.setPosition(gamepad1.right_trigger);
            armRight.setPosition(gamepad1.right_trigger);

        }
    }


}
