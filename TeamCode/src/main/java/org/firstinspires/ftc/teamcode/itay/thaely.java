package org.firstinspires.ftc.teamcode.itay;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class thaely extends LinearOpMode {

    static DcMotor frontRight;
    static DcMotor frontLeft;
    static DcMotor backRight;
    static DcMotor backLeft;

    public static float Vector(float x, float y){
        return (float) Math.sqrt(x * x + y * y);
    }

    public static void motors(float power){
        frontRight.setPower(power);
        frontLeft.setPower(power);
        backRight.setPower(power);
        backLeft.setPower(power);
    }

    @Override
    public void runOpMode() throws InterruptedException {

        Servo ServoFL = hardwareMap.servo.get("ServoFL");
        Servo ServoFR = hardwareMap.servo.get("ServoFR");
        Servo ServoBL = hardwareMap.servo.get("ServoBL");
        Servo ServoBR = hardwareMap.servo.get("ServoBR");

        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontLeft  = hardwareMap.dcMotor.get("frontLeft" );
        backRight  = hardwareMap.dcMotor.get("backRight" );
        backLeft   = hardwareMap.dcMotor.get("backLeft"  );

        //DcMotor frontRight = hardwareMap.dcMotor.get("frontRight");
        //DcMotor frontLeft = hardwareMap.dcMotor.get("frontLeft");
        //DcMotor backRight = hardwareMap.dcMotor.get("backRight");
        //DcMotor backLeft = hardwareMap.dcMotor.get("backLeft");
//
        //frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        //backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        if(isStopRequested()){return;}
        resetRuntime();

        while (opModeIsActive()){
;;
            motors(Vector(gamepad1.left_stick_x,gamepad1.left_stick_y));

            //double p2 = (gamepad1.right_stick_x-gamepad1.right_stick_y) / Math.sqrt(2);
            //double p1 = (-gamepad1.right_stick_x-gamepad1.right_stick_y) / Math.sqrt(2);
//
            //double bigger = Math.max(Math.abs(p1), Math.abs(p2));
            //if(bigger>1){
            //    p1 /= bigger;
            //    p2 /= bigger;
//
            //}
            //frontRight.setPower(p2);
            //frontLeft.setPower(p1);
            //backRight.setPower(p1);
            //backLeft.setPower(p2);




        }
    }
}
