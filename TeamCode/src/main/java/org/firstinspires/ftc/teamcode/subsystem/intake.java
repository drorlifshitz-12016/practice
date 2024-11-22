package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp (name="intake concept test" ,group="TeleOp")

public class intake extends OpMode {

    // Declare the CRServo
    private CRServo crServo;

    @Override
    public void init() {
        // Initialize the CRServo hardware
        crServo = hardwareMap.get(CRServo.class, "CRServo");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void loop() {
        // Control the CR servo using triggers
        double leftTrigger = gamepad1.left_trigger;  // Value from 0 to 1
        double rightTrigger = gamepad1.right_trigger; // Value from 0 to 1

        //using the trigger command to control both rotation states
        if (rightTrigger > 0) {
            crServo.setPower(rightTrigger); // Spin right at speed proportional to trigger press
        } else if (leftTrigger > 0) {
            crServo.setPower(-leftTrigger); // Spin left at speed proportional to trigger press
        } else {
            crServo.setPower(0.0); // Stop
        }

        // Telemetry to monitor state
        telemetry.addData("Left Trigger", leftTrigger);
        telemetry.addData("Right Trigger", rightTrigger);
        telemetry.addData("Servo Power", crServo.getPower());
        telemetry.update();
    }
}

