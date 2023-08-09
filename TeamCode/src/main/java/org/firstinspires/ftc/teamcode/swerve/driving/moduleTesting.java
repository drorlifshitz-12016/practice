package org.firstinspires.ftc.teamcode.swerve.driving;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import java.util.Base64;

@TeleOp
public class moduleTesting extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // region MODULES
        swerveModule moduleFR = new swerveModule(hardwareMap, "frontRight", 0.793);
        swerveModule moduleFL = new swerveModule(hardwareMap, "frontLeft" , 0.2  );
        swerveModule moduleBR = new swerveModule(hardwareMap, "backRight" , 0.395);
        swerveModule moduleBL = new swerveModule(hardwareMap, "backLeft"  , 0.365);
        // endregion

        waitForStart();
        if(isStopRequested()) { return; }
        resetRuntime();

        while (opModeIsActive()){
            // region SET POWER
            double power = Math.pow(Math.pow(gamepad1.right_stick_y, 2) + Math.pow(gamepad1.right_stick_x, 2), 0.5);

            moduleFR.setPower(power);
            moduleFL.setPower(power);
            moduleBR.setPower(power);
            moduleBL.setPower(power);
            // endregion

            // region SET ANGLE
            double angle = Math.atan2(gamepad1.right_stick_x, gamepad1.right_stick_y) / 2 / Math.PI;

            if (power > 0.1) {
                moduleFR.setWantedAngle((Math.atan2(gamepad1.right_stick_x, gamepad1.right_stick_y) / 2 / Math.PI + 1) % 1);
                moduleFL.setWantedAngle((Math.atan2(gamepad1.right_stick_x, gamepad1.right_stick_y) / 2 / Math.PI + 1) % 1);
                moduleBR.setWantedAngle((Math.atan2(gamepad1.right_stick_x, gamepad1.right_stick_y) / 2 / Math.PI + 1) % 1);
                moduleBL.setWantedAngle((Math.atan2(gamepad1.right_stick_x, gamepad1.right_stick_y) / 2 / Math.PI + 1) % 1);
            }
            // endregion

            // region UPDATE
            moduleFR.update();
            moduleFL.update();
            moduleBR.update();
            moduleBL.update();
            // endregion

            // region TELEMETRY
            telemetry.addData("FR wanted angle", moduleFR.wantedAngle);
            telemetry.addData("FL wanted angle", moduleFL.wantedAngle);
            telemetry.addData("BR wanted angle", moduleBR.wantedAngle);
            telemetry.addData("BL wanted angle", moduleBL.wantedAngle);
            telemetry.addData("FR current angle", moduleFR.currentAngle);
            telemetry.addData("FL current angle", moduleFL.currentAngle);
            telemetry.addData("BR current angle", moduleBR.currentAngle);
            telemetry.addData("BL current angle", moduleBL.currentAngle);
            telemetry.update();
            // endregion
        }
    }
}

class swerveModule{
    // region DEVICES
    private final AnalogInput encoder;
    private final CRServo servo;
    private final DcMotor motor;
    // endregion

    // region VARIABLES
    public double currentAngle;
    public double wantedAngle = 0;
    private final double angleOffset;
    // endregion
    public swerveModule(HardwareMap hm, String name, double angleOffset){
        this(hm.get(AnalogInput.class, name),
            hm.get(CRServo.class     , name),
            hm.get(DcMotor.class     , name),
            angleOffset);
    }
    public swerveModule(AnalogInput encoder, CRServo servo, DcMotor motor, double angleOffset){
        this.encoder = encoder;
        this.servo = servo;
        this.motor = motor;
        this.angleOffset = angleOffset;
    }

    // region GETTERS AND SETTERS
    public double getCurrentAngle(){
        return currentAngle;
    }
    public void setWantedAngle(double angle){
        wantedAngle = angle;
    }
    public double getWantedAngle(){
        return wantedAngle;
    }
    public void setPower(double power){
        motor.setPower((Math.abs(wantedAngle - currentAngle) > 0.25 ? -1: 1) * power);
    }
    // endregion

    public void update(){
        // update the current angle
        currentAngle = (encoder.getVoltage() / encoder.getMaxVoltage() - angleOffset + 1) % 1;

        // update the power of the servo
        servo.setPower(calcPower(calcAngleDifference(wantedAngle, currentAngle)));
    }

    // region MOVEMENT DEFINING FUNCTIONS
    private double calcAngleDifference(double targetAngle, double currentAngle){
        return (targetAngle - currentAngle + 1.25) % 0.5 - 0.25;
    }
    private static double calcPower(double angleDifference){
        if (angleDifference > 0) { return -moveTypeDefinition( angleDifference); }
        else                     { return  moveTypeDefinition(-angleDifference); }
    }

    // region CONSTANTS
    private final static double minPower = 0.12;
    private final static double maxPower = 1  ;

    private final static double acceptableError  = 0.02;
    private final static double slowingDistance  = 0.1;
    private final static double deceleratingDist = 0.13;
    // endregion
    private static double moveTypeDefinition(double x){
        if      (x < acceptableError ) { return 0; }
        else if (x < slowingDistance ) { return minPower; }
        else if (x < deceleratingDist) { return decelerationTypeDefinition(x); }
        else                           { return maxPower; }
    }
    private static double decelerationTypeDefinition(double x){
        // linear deceleration
        return (x - slowingDistance) * (maxPower - minPower) / (deceleratingDist - slowingDistance) + minPower;
    }
    // endregion
}
