package org.firstinspires.ftc.teamcode.itay;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class practice extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();
        if(isStopRequested()){
            return;
        }
        resetRuntime();
        DcMotor frontRight = hardwareMap.dcMotor.get("frontRight");
        DcMotor frontLeft = hardwareMap.dcMotor.get("frontLeft");
        DcMotor backRight = hardwareMap.dcMotor.get("backRight");
        DcMotor backLeft = hardwareMap.dcMotor.get("backLeft");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        while (opModeIsActive()){
                double Powermotor_FR = gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x;
                double Powermotor_FL = gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x;
                double Powermotor_BR = gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x;
                double Powermotor_BL = gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x;

                double highestNum_B = Math.max(Math.abs(Powermotor_BL),Math.abs(Powermotor_BR));
                double highestNum_F = Math.max(Math.abs(Powermotor_FR),Math.abs(Powermotor_FL));
                double highestNum = Math.max(highestNum_B,highestNum_F);
                if (highestNum > 1){
                    Powermotor_BL = Powermotor_BL / highestNum;
                    Powermotor_BR = Powermotor_BR / highestNum;
                    Powermotor_FL = Powermotor_FL / highestNum;
                    Powermotor_FR = Powermotor_FR / highestNum;
                }
            frontRight.setPower(Powermotor_FR);
            frontLeft.setPower(Powermotor_FL);
            backRight.setPower(Powermotor_BR);
            backLeft.setPower(Powermotor_BL);
        }
   }
}
