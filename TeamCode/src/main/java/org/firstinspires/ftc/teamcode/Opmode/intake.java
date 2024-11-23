package org.firstinspires.ftc.teamcode.Opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp (name="intake concept test" ,group="TeleOp")

public class intake extends OpMode {

    // Declare the CRServo
    private CRServo crServoR;
    private CRServo crServoL;

    @Override
    public void init() {
        // Initialize the CRServo hardware
        crServoR = hardwareMap.get(CRServo.class, "CRservoR");
        crServoL = hardwareMap.get(CRServo.class , "CRservoL");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void loop() {
        // Control the CR servo using triggers
        double leftTrigger = gamepad1.left_trigger;  // Value from 0 to 1
        double rightTrigger = gamepad1.right_trigger; // Value from 0 to 1

        //using the trigger command to control both rotation states for left servo
        if (rightTrigger > 0) {
            crServoL.setPower(rightTrigger); // Spin right at speed proportional to trigger press
        } else if (leftTrigger > 0) {
            crServoL.setPower(-leftTrigger); // Spin left at speed proportional to trigger press
        } else {
            crServoL.setPower(0.0); // Stop
        }

        //using the trigger command to control both rotation states for right servo
        if (rightTrigger > 0) {
            crServoR.setPower(rightTrigger); // Spin right at speed proportional to trigger press
        } else if (leftTrigger > 0) {
            crServoR.setPower(-leftTrigger); // Spin left at speed proportional to trigger press
        } else {
            crServoR.setPower(0.0); // Stop
        }



        // Telemetry to monitor state
        telemetry.addData("Left Trigger", leftTrigger);
        telemetry.addData("Right Trigger", rightTrigger);
        telemetry.addData("Servo Power", crServoL.getPower());
        telemetry.addData("Servo Power", crServoR.getPower());
        telemetry.update();
    }
}

