package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class testing extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

    DcMotor frontright = hardwareMap.dcMotor.get("frontRight");
    DcMotor frontleft = hardwareMap.dcMotor.get("frontleft");
    DcMotor backright = hardwareMap.dcMotor.get("backright");
    DcMotor backleft = hardwareMap.dcMotor.get("backlrft");


        waitForStart();
    if (isStopRequested()){ return; }
    resetRuntime();

    while (opModeIsActive()){
        frontleft.setPower(-1);
        frontright.setPower(1);
        backleft.setPower(-1);
        backright.setPower(1);
        
    }



    }
}
