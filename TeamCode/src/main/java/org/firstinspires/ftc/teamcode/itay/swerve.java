package org.firstinspires.ftc.teamcode.itay;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class swerve extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();
        if(isStopRequested()){return;}
        resetRuntime();

        while (opModeIsActive()){
            
        }
    }
}
