package org.firstinspires.ftc.teamcode.tahel;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@TeleOp
public class proj1 extends LinearOpMode {

    public double MAX(double n1, double n2, double n3, double n4){
        double bigger1 = Math.max(n1, n2);
        double bigger2 = Math.max(n3, n4);
        double max_num = Math.max(bigger1, bigger2);
        return max_num;
    }
    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor frontRight = hardwareMap.dcMotor.get("frontRight");
        DcMotor frontLeft = hardwareMap.dcMotor.get("frontLeft");
        DcMotor backRight = hardwareMap.dcMotor.get("backRight");
        DcMotor backLeft = hardwareMap.dcMotor.get("backLeft");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        if(isStopRequested()){
            return;
        }
        resetRuntime();

        while (opModeIsActive()){
            double p1 = - gamepad1.right_stick_x - gamepad1.right_stick_y + gamepad1.left_stick_x;
            double p2 =   gamepad1.right_stick_x - gamepad1.right_stick_y - gamepad1.left_stick_x;
            double p3 =   gamepad1.right_stick_x - gamepad1.right_stick_y + gamepad1.left_stick_x;
            double p4 = - gamepad1.right_stick_x - gamepad1.right_stick_y - gamepad1.left_stick_x;
            double bigger = MAX(Math.abs(p1), Math.abs(p2),Math.abs(p3), Math.abs(p4));

            if(bigger>1){
                p1 /= bigger;
                p2 /= bigger;
                p3 /= bigger;
                p4 /= bigger;
            }
            frontRight.setPower(p1);
            frontLeft.setPower(p2);
            backRight.setPower(p3);
            backLeft.setPower(p4);
        }
    }
}
