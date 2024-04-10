package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Wizerd extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        WizerdMudel frontRightMudel = new WizerdMudel(hardwareMap.crservo.get("frServo"), (DcMotorEx) hardwareMap.dcMotor.get("frMotor"));
        WizerdMudel frontLeftMudel = new WizerdMudel(hardwareMap.crservo.get("flServo"), (DcMotorEx) hardwareMap.dcMotor.get("flMotor"));
        WizerdMudel backRightMudel = new WizerdMudel(hardwareMap.crservo.get("brServo"), (DcMotorEx) hardwareMap.dcMotor.get("brMotor"));
        WizerdMudel backLeftMudel = new WizerdMudel(hardwareMap.crservo.get("blServo"), (DcMotorEx) hardwareMap.dcMotor.get("blMotor"));

        waitForStart();
        if (isStopRequested()){return;}
        resetRuntime();


        while (opModeIsActive()){

            frontRightMudel.servo.setPower(gamepad1.left_trigger);
            telemetry.addData("Meow",frontRightMudel.servo.getPower());
            telemetry.addData("incoder",frontRightMudel.motor.getCurrentPosition());

        }
    }
}
