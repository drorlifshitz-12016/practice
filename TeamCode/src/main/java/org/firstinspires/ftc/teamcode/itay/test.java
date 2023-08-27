package org.firstinspires.ftc.teamcode.itay;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

public class test extends LinearOpMode {

    public void runOpMode(){

        CRServo ServoFR  = hardwareMap.crservo.get("frontRight");
        CRServo ServoFL  = hardwareMap.crservo.get("frontLeft");
        CRServo ServoBR  = hardwareMap.crservo.get("backRight");
        CRServo ServoBL  = hardwareMap.crservo.get("backLeft");

        DcMotor motorFR = hardwareMap.dcMotor.get("frontRight");
        DcMotor motorFL = hardwareMap.dcMotor.get("frontLeft");
        DcMotor motorBR = hardwareMap.dcMotor.get("backRight");
        DcMotor motorBL = hardwareMap.dcMotor.get("backLeft");

        waitForStart();
        if(isStopRequested()){return;}
        resetRuntime();

        while (opModeIsActive()){

            ServoBL.setPower(gamepad1.left_stick_y);
            ServoBR.setPower(gamepad1.left_stick_y);
            ServoFL.setPower(gamepad1.left_stick_y);
            ServoFR.setPower(gamepad1.left_stick_y);


        }
    }
}
