package org.firstinspires.ftc.teamcode.itay;

import static org.firstinspires.ftc.teamcode.itay.ExtendArm.extendArm;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp
public class Test extends LinearOpMode {

    public static void calculatePower(int wantedPos, int currentPos, int slowly){
        elevatorMotors(0.85);
        if (tick_past(slowly,currentPos)){
            t.addData("brakingrange", slowly);
            t.update();
            elevatorMotors(0.5);
        if (tick_past(wantedPos,currentPos)){
            elevatorMotors(0.07);
            }
        }
    }

    public boolean time_past(double first_time,double time_to_past){
        return first_time + time_to_past < getRuntime();
    }

    public static int calculateBrakingRange(int wantedPos, int currentPos) {
        return (wantedPos - currentPos) * 3 / 5;
    }
    public static boolean tick_past(int wantedPos, int currentPosition){
        return currentPosition > wantedPos;
    }
    public static void elevatorMotors(double motorPower) {
        motorMiddle.setPower(motorPower);
        motorRight.setPower(motorPower);
        motorLeft.setPower(motorPower);
    }

    static DcMotor motorRight;
    static DcMotor motorLeft;
    static DcMotor motorMiddle;

    final static int middlePosition = 11300;
    final static int lowPosition    = 4300 ;
    final static int highPosition   = 17500;
    static Telemetry t;
    @Override
    public void runOpMode() {
        t = telemetry;
        // region INITIALIZE THE MOTORS
        motorRight = (DcMotorEx) hardwareMap.dcMotor.get("elevatorRight");
        motorLeft = (DcMotorEx) hardwareMap.dcMotor.get("elevatorLeft");
        motorMiddle = (DcMotorEx) hardwareMap.dcMotor.get("elevatorMiddle");

        motorRight.setDirection(DcMotorSimple.Direction.REVERSE);

        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // endregion

        int slowly;
        boolean b = false;
        boolean c = false;
        boolean d = false;
        int whereAareYouNow = 0;

        // region WAIT FOR START
        waitForStart();
        if (isStopRequested()) {
            return;
        }
        resetRuntime();
        // endregion


        while (opModeIsActive()) {
            if (gamepad1.a) {
                slowly = 2580;
                calculatePower(lowPosition,motorLeft.getCurrentPosition(),slowly);
            }

        }
    }
}
