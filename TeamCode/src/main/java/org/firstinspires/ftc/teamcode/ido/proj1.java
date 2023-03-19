package org.firstinspires.ftc.teamcode.ido;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class proj1 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor frontLeft = hardwareMap.dcMotor.get("frontLeft");
        DcMotor fronRight = hardwareMap.dcMotor.get("frontRight");
        DcMotor backLeft = hardwareMap.dcMotor.get("backLeft");
        DcMotor backRight = hardwareMap.dcMotor.get("backRight");

        waitForStart();
        if(isStopRequested()){
            return;
        }
        resetRuntime();

        while (opModeIsActive()){
            if (gamepad1.left_stick_y>0){
                fronRight.setPower(-1);
                frontLeft.setPower(1);
                backRight.setPower(-1);
                backLeft.setPower(1);
            }
            if (gamepad1.left_stick_y==0) {
                fronRight.setPower(0);
                frontLeft.setPower(0);
                backRight.setPower(0);
                backLeft.setPower(0);
            }
        }
    }
}