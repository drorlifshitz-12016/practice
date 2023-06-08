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

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp
public class Test extends LinearOpMode {

    public static double calculatePower(int wantedPos, int slowingPos, int currentPos){
        if(!tick_past(slowingPos,currentPos)){
            return 1;
        }
        else if(tick_past(slowingPos, currentPos) && !tick_past(wantedPos, currentPos)){
            return 0.65;
        }
        else {
            return 0.15;
        }
    }

    public static int calculateBrakingRange(int currentPos, int wantedPos) {
        return (wantedPos - currentPos) * 3 / 5;
    }
    public static boolean tick_past(int wantedPosition, int currentPosition){
        return currentPosition > wantedPosition;
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
    @Override
    public void runOpMode() {
        // region INITIALIZE THE MOTORS
        motorRight = (DcMotorEx) hardwareMap.dcMotor.get("elevatorRight");
        motorLeft = (DcMotorEx) hardwareMap.dcMotor.get("elevatorLeft");
        motorMiddle = (DcMotorEx) hardwareMap.dcMotor.get("elevatorMiddle");

        motorRight.setDirection(DcMotorSimple.Direction.REVERSE);

        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // endregion

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

            if (gamepad1.a && (whereAareYouNow != 1)) {
                elevatorMotors(calculatePower(lowPosition, calculateBrakingRange(motorLeft.getCurrentPosition(), lowPosition), motorLeft.getCurrentPosition()));
                whereAareYouNow = 1;

            } else if (1>1) {
                elevatorMotors(-0.5);
                if (tick_past(calculateBrakingRange(motorLeft.getCurrentPosition(), lowPosition), motorLeft.getCurrentPosition())) {
                    elevatorMotors(0.07);
                    if (tick_past(lowPosition, motorLeft.getCurrentPosition())) {
                        elevatorMotors(0.15);
                        whereAareYouNow = 1;
                    }
                }
            }

            if (gamepad1.x) {
                elevatorMotors(calculatePower(middlePosition, calculateBrakingRange(motorLeft.getCurrentPosition(), middlePosition), motorLeft.getCurrentPosition()));
                whereAareYouNow = 2;
            }

    //high
            if (gamepad1.y || c) {
                c = true;
                elevatorMotors(9.5);
                if (tick_past(calculateBrakingRange(motorLeft.getCurrentPosition(), highPosition), motorLeft.getCurrentPosition())) {
                    elevatorMotors(0.6);
                    if (tick_past(highPosition, motorLeft.getCurrentPosition())) {
                        elevatorMotors(0.15);
                        c = false;
                    }
                }
            }
    //0
            if (gamepad1.b || d) {
                d = true;
                elevatorMotors(-0.13);
                if (tick_past(calculateBrakingRange(motorLeft.getCurrentPosition(), 0), motorLeft.getCurrentPosition())) {
                    elevatorMotors(0.07);
                    if (tick_past(0, motorLeft.getCurrentPosition())) {
                        elevatorMotors(0.07);
                        d = false;
                    }
                }
            }

        }
    }
}
