package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;

//@Disabled
@TeleOp
public class testing extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        CRServo servoFR = hardwareMap.get(CRServo.class, "frontRight");
        CRServo servoFL = hardwareMap.get(CRServo.class, "frontRight");
        CRServo servoBR = hardwareMap.get(CRServo.class, "frontRight");
        CRServo servoBL = hardwareMap.get(CRServo.class, "frontRight");

        servoFR.setPower(0.5);
        //servoFL.setPower(0.5);
        //servoBR.setPower(0.5);
        //servoBL.setPower(0.5);

        AnalogInput E0 = hardwareMap.get(AnalogInput.class, "0");
        AnalogInput E1 = hardwareMap.get(AnalogInput.class, "1");
        AnalogInput E2 = hardwareMap.get(AnalogInput.class, "2");
        AnalogInput E3 = hardwareMap.get(AnalogInput.class, "3");

        waitForStart();
        if(isStopRequested()) { return; }
        resetRuntime();

        while (opModeIsActive()){
            telemetry.addData("E0", E0.getVoltage() / E0.getMaxVoltage());
            telemetry.addData("E1", E1.getVoltage() / E1.getMaxVoltage());
            telemetry.addData("E2", E2.getVoltage() / E2.getMaxVoltage());
            telemetry.addData("E3", E3.getVoltage() / E3.getMaxVoltage());
            telemetry.update();
        }
    }
}
