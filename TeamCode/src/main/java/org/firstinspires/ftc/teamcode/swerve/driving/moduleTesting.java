package org.firstinspires.ftc.teamcode.swerve.driving;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Base64;

@TeleOp
public class moduleTesting extends LinearOpMode {
    static AnalogInput encoder;

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor motor = hardwareMap.dcMotor.get("motor");
        CRServo servo = hardwareMap.get(CRServo.class, "servo");
        encoder = hardwareMap.get(AnalogInput.class, "encoder");

        waitForStart();
        if(isStopRequested()) { return; }
        resetRuntime();

        while (opModeIsActive()){
            servo.setPower(calcPower2(getEncoderAngle(), gamepad1.left_stick_x ));
            telemetry.addData("angle", getEncoderAngle());
            telemetry.update();
        }
    }
    public static double getEncoderAngle(){
        return encoder.getVoltage() / encoder.getMaxVoltage();
    }

    public static double calcPower(double angle, double wantedAngle){
        if (Math.abs(angle - wantedAngle) < 0.003){
            return 0;
        } else if (angle > wantedAngle){
            return angle - wantedAngle + 0.05;
        } else {
            return angle - wantedAngle - 0.05;
        }

    }
    final static double stoppingDst = 0.08;
    public static double calcPower2(double angle, double wantedAngle){

        if (Math.abs(angle - wantedAngle) > stoppingDst){
            return (angle - wantedAngle) > 0 ? 1: -1;
        }

        return (Math.cos((angle - wantedAngle) / stoppingDst * Math.PI) / 2 - 0.5) * (angle - wantedAngle > 0 ? -1: 1) / 3;
    }
}
