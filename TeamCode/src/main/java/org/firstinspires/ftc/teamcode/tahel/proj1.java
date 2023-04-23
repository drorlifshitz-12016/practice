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
        // region GET DRIVETRAIN MOTORS
        DcMotor frontRight = hardwareMap.dcMotor.get("frontRight");
        DcMotor frontLeft = hardwareMap.dcMotor.get("frontLeft");
        DcMotor backRight = hardwareMap.dcMotor.get("backRight");
        DcMotor backLeft = hardwareMap.dcMotor.get("backLeft");
        // endregion

        // region SET MOTOR DIRECTION
        // reversing the right motors in order to have intuition for their movement direction
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        // endregion

        // region WAIT FOR START
        waitForStart();
        if(isStopRequested()){
            return;
        }
        resetRuntime();
        // endregion

        while (opModeIsActive()){
            // region MOTOR POWER CALCULATION
            // calculating the motor powers based on the three basic movements (straight, lateral and turn)
            //            [      straight       ] [       lateral       ] [         turn         ]
            double p1 = - gamepad1.right_stick_x - gamepad1.right_stick_y + gamepad1.left_stick_x;
            double p2 =   gamepad1.right_stick_x - gamepad1.right_stick_y - gamepad1.left_stick_x;
            double p3 =   gamepad1.right_stick_x - gamepad1.right_stick_y + gamepad1.left_stick_x;
            double p4 = - gamepad1.right_stick_x - gamepad1.right_stick_y - gamepad1.left_stick_x;
            // endregion
            double x1 = gamepad1.left_stick_x;
            double y1 = gamepad1.left_stick_y;
            // region NORMALIZE MOTOR POWER

            // finds the highest absolute value of the non normalized motor powers
            double bigger = MAX(Math.abs(p1), Math.abs(p2),Math.abs(p3), Math.abs(p4));
            // if the motors aren't capable of the power requirement
            if(bigger>1){
                p1 /= bigger;
                p2 /= bigger;
                p3 /= bigger;
                p4 /= bigger;
            }
            // endregion

            // region SET MOTOR POWER
            frontRight.setPower(p1);
            frontLeft.setPower(p2);
            backRight.setPower(p3);
            backLeft.setPower(p4);
            // endregion
        }
    }
}
