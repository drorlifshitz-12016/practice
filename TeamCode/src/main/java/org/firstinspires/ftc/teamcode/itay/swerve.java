package org.firstinspires.ftc.teamcode.itay;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.itay.workingSwerve.dependencies.Encoder;

@TeleOp
public class swerve extends LinearOpMode {

    public double getLength(double x,double y){
        return Math.sqrt(x*x + y*y);
    }


    @Override
    public void runOpMode() throws InterruptedException {

        final Encoder encoder_FR;
        final Encoder encoder_FL;
        final Encoder encoder_BR;
        final Encoder encoder_BL;

        //new Encoder(encoder_BR,0.18);

        CRServo ServoFR  = hardwareMap.crservo.get("frontRight");
        CRServo ServoFL  = hardwareMap.crservo.get("frontLeft");
        CRServo ServoBR  = hardwareMap.crservo.get("backRight");
        CRServo ServoBL  = hardwareMap.crservo.get("backLeft");

        DcMotor motorFR = hardwareMap.dcMotor.get("frontRight");
        DcMotor motorFL = hardwareMap.dcMotor.get("frontLeft");
        DcMotor motorBR = hardwareMap.dcMotor.get("backRight");
        DcMotor motorBL = hardwareMap.dcMotor.get("backLeft");

        waitForStart();
        if (isStopRequested()) {return;}
        resetRuntime();

        while (opModeIsActive()) {

            //ServoBL.setPower(gamepad1.left_stick_y);
            //ServoBR.setPower(gamepad1.left_stick_y);
            //ServoFL.setPower(gamepad1.left_stick_y);
            //ServoFR.setPower(gamepad1.left_stick_y);

            motorFL.setPower(getLength(gamepad1.left_stick_x,gamepad1.left_stick_y));
            motorFR.setPower(getLength(gamepad1.left_stick_x,gamepad1.left_stick_y));
            motorBL.setPower(getLength(gamepad1.left_stick_x,gamepad1.left_stick_y));
            motorBR.setPower(getLength(gamepad1.left_stick_x,gamepad1.left_stick_y));

        }
    }
}
