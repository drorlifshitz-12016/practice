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

    final static double minPower = 0.2;
    final static double maxPower = 1  ;

    final static double acceptableError  = 0.05;
    final static double slowingDistance  = 0.2;
    final static double deceleratingDist = 0.4;
    @Override
    public void runOpMode() throws InterruptedException {
        // region ENCODERS
        AnalogInput encoderFR = hardwareMap.get(AnalogInput.class, "frontRight");
        AnalogInput encoderBR = hardwareMap.get(AnalogInput.class, "backRight");
        AnalogInput encoderBL = hardwareMap.get(AnalogInput.class, "backLeft");
        AnalogInput encoderFL = hardwareMap.get(AnalogInput.class, "frontLeft");
        // endregion
        DcMotor fr = hardwareMap.dcMotor.get("frontRight");
        DcMotor fl = hardwareMap.dcMotor.get("frontLeft");
        DcMotor br = hardwareMap.dcMotor.get("backRight");
        DcMotor bl = hardwareMap.dcMotor.get("backLeft");

        waitForStart();
        if(isStopRequested()) { return; }
        resetRuntime();
        VoltageSensor vs = hardwareMap.voltageSensor.get("Control Hub");

        while (opModeIsActive()){
            fr.setPower(gamepad1.left_trigger);
            fl.setPower(gamepad1.left_trigger);
            br.setPower(gamepad1.left_trigger);
            bl.setPower(gamepad1.left_trigger);


            telemetry.addData("encoderFR", encoderFR.getVoltage() / encoderFR.getMaxVoltage() - 0.03 * vs.getVoltage());
            telemetry.addData("encoderBL", encoderFL.getVoltage() / encoderFL.getMaxVoltage() - 0.03 * vs.getVoltage());
            telemetry.addData("encoderBR", encoderBR.getVoltage() / encoderBR.getMaxVoltage() - 0.03 * vs.getVoltage());
            telemetry.addData("encoderFL", encoderBL.getVoltage() / encoderBL.getMaxVoltage() - 0.03 * vs.getVoltage());
            telemetry.update();
        }
    }
}
