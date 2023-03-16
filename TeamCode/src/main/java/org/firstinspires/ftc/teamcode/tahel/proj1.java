package org.firstinspires.ftc.teamcode.tahel;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class proj1 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor frontRight = hardwareMap.dcMotor.get("frontRight");
        DcMotor frontLeft = hardwareMap.dcMotor.get("frontRight");
        DcMotor backRight = hardwareMap.dcMotor.get("frontRight");
        DcMotor backLeft = hardwareMap.dcMotor.get("frontRight");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        if(isStopRequested()){
            return;
        }
        resetRuntime();
        
        while (opModeIsActive()){
            double p2 = (gamepad1.right_stick_x-gamepad1.right_stick_y) / Math.sqrt(2);
            double p1 = (-gamepad1.right_stick_x-gamepad1.right_stick_y) / Math.sqrt(2);
            double bigger = Math.max(Math.abs(p1), Math.abs(p2));
            if(bigger>1){
                p1 /= bigger;
                p2 /= bigger;
            }
            frontRight.setPower(p2);
            frontLeft.setPower(p1);
            backRight.setPower(p1);
            backLeft.setPower(p2);
        }
    }
}
