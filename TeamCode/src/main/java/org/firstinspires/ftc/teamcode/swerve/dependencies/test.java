package org.firstinspires.ftc.teamcode.swerve.dependencies;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

public class test extends LinearOpMode {

    public void runOpMode(){

        CRServo frontRight = hardwareMap.crservo.get("frontRight");
        CRServo frontLeft  = hardwareMap.crservo.get("frontLeft" );
        CRServo backRight  = hardwareMap.crservo.get("backRight" );
        CRServo backLeft   = hardwareMap.crservo.get("backLeft"  );

        DcMotor motorfr =
        DcMotor motorfl =
        DcMotor motorfr =
        DcMotor motorfr =

        waitForStart();
        if(isStopRequested()){return;}
        resetRuntime();

        while (opModeIsActive()){




        }
    }
}
