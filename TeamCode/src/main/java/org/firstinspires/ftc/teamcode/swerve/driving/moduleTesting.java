package org.firstinspires.ftc.teamcode.swerve.driving;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import java.util.Base64;

@TeleOp
public class moduleTesting extends LinearOpMode {

    /* fl
    final static double minPower = 0.12;
    final static double maxPower = 1  ;

    final static double acceptableError  = 0.02;
    final static double slowingDistance  = 0.1;
    final static double deceleratingDist = 0.13;
     */

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

        // region MOTORS
        DcMotor fr = hardwareMap.dcMotor.get("frontRight");
        DcMotor fl = hardwareMap.dcMotor.get("frontLeft");
        DcMotor br = hardwareMap.dcMotor.get("backRight");
        DcMotor bl = hardwareMap.dcMotor.get("backLeft");
        // endregion

        VoltageSensor batteryVoltage = hardwareMap.voltageSensor.get("Control Hub");


        waitForStart();
        if(isStopRequested()) { return; }
        resetRuntime();

        telemetry.setAutoClear(false);

        while (opModeIsActive()){
            servoFR.setPower(calcPower(calcDist(0.00, encoderFR.getVoltage() / encoderFR.getMaxVoltage() - 0.03 * batteryVoltage.getVoltage())));
            servoFL.setPower(calcPower(calcDist(0.21, encoderFL.getVoltage() / encoderFL.getMaxVoltage() - 0.03 * batteryVoltage.getVoltage())));
            servoBR.setPower(calcPower(calcDist(0.39, encoderBR.getVoltage() / encoderBR.getMaxVoltage() - 0.03 * batteryVoltage.getVoltage())));
            servoBL.setPower(calcPower(calcDist(0.35, encoderBL.getVoltage() / encoderBL.getMaxVoltage() - 0.03 * batteryVoltage.getVoltage())));

            fr.setPower(gamepad1.left_trigger);
            fl.setPower(gamepad1.left_trigger);
            br.setPower(gamepad1.left_trigger);
            bl.setPower(gamepad1.left_trigger);
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
