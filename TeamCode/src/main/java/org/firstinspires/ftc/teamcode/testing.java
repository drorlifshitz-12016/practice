package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.VoltageSensor;

//@Disabled
@TeleOp
public class testing extends LinearOpMode {
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

        while (opModeIsActive()){
            telemetry.addData("encoderFR", encoderFR.getVoltage() / encoderFR.getMaxVoltage() - 0.03 * batteryVoltage.getVoltage());
            telemetry.addData("encoderBL", encoderFL.getVoltage() / encoderFL.getMaxVoltage() - 0.03 * batteryVoltage.getVoltage());
            telemetry.addData("encoderBR", encoderBR.getVoltage() / encoderBR.getMaxVoltage() - 0.03 * batteryVoltage.getVoltage());
            telemetry.addData("encoderFL", encoderBL.getVoltage() / encoderBL.getMaxVoltage() - 0.03 * batteryVoltage.getVoltage());
            telemetry.update();
        }
    }
}
