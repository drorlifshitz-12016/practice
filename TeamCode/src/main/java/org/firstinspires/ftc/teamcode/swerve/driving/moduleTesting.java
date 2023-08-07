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

    final static double minPower = 0.12;
    final static double maxPower = 1  ;

    final static double acceptableError  = 0.02;
    final static double slowingDistance  = 0.1;
    final static double deceleratingDist = 0.13;
    @Override
    public void runOpMode() throws InterruptedException {
        // region SERVOS
        CRServo servoFR = hardwareMap.get(CRServo.class, "frontRight");
        CRServo servoFL = hardwareMap.get(CRServo.class, "frontLeft");
        CRServo servoBR = hardwareMap.get(CRServo.class, "backRight");
        CRServo servoBL = hardwareMap.get(CRServo.class, "backLeft");
        // endregion

        // region ENCODERS
        AnalogInput encoderFR = hardwareMap.get(AnalogInput.class, "frontRight");
        AnalogInput encoderBR = hardwareMap.get(AnalogInput.class, "backRight");
        AnalogInput encoderBL = hardwareMap.get(AnalogInput.class, "backLeft");
        AnalogInput encoderFL = hardwareMap.get(AnalogInput.class, "frontLeft");
        // endregion

        //servoFR.setPower(0.5);
        //servoFL.setPower(0.5);
        //servoBR.setPower(0.5);
        //servoBL.setPower(0.5);

        waitForStart();
        if(isStopRequested()) { return; }
        resetRuntime();

        while (opModeIsActive()){
            servoFL.setPower(calcPower(calcDist(gamepad1.left_trigger, encoderFL.getVoltage() / encoderFL.getMaxVoltage())));

            telemetry.addData("encoderFR", encoderFR.getVoltage() / encoderFR.getMaxVoltage());
            telemetry.addData("encoderBR", encoderBR.getVoltage() / encoderBR.getMaxVoltage());
            telemetry.addData("encoderBL", encoderBL.getVoltage() / encoderBL.getMaxVoltage());
            telemetry.addData("encoderFL", encoderFL.getVoltage() / encoderFL.getMaxVoltage());
            telemetry.update();
        }
    }
    public static double calcDist(double targetPos, double currentPos){
        return (targetPos - currentPos + 1.5) % 1 - 0.5;
    }
    public static double calcPower(double dist){
        if (dist > 0) { return -moveTypeDefinition( dist); }
        else          { return  moveTypeDefinition(-dist); }
    }
    public static double moveTypeDefinition(double x){
        if      (x < acceptableError ) { return 0; }
        else if (x < slowingDistance ) { return minPower; }
        else if (x < deceleratingDist) { return decelerationTypeDefinition(x); }
        else                           { return maxPower; }
    }
    public static double decelerationTypeDefinition(double x){
        // linear deceleration
        return (x - slowingDistance) * (maxPower - minPower) / (deceleratingDist - slowingDistance) + minPower;
    }

}
