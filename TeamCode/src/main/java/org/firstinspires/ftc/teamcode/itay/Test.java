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

    public static double calculateBrakingRange(double high_now,double DST_of_ticks) {
        return (((DST_of_ticks - high_now) / 5) * 3);
    }

    public boolean tick_past(double calculateBrakingRange, int currentPosition){
        return currentPosition > calculateBrakingRange;
    }

    public static void elevatorMotors(double motorPower) {
        motorMiddle.setPower(motorPower);
        motorRight.setPower(motorPower);
        motorLeft.setPower(motorPower);
    }

    static DcMotor motorRight;
    static DcMotor motorLeft;
    static DcMotor motorMiddle;

    @Override
    public void runOpMode() {

        motorRight  = (DcMotorEx) hardwareMap.dcMotor.get("elevatorRight");
        motorLeft   = (DcMotorEx) hardwareMap.dcMotor.get("elevatorLeft");
        motorMiddle = (DcMotorEx) hardwareMap.dcMotor.get("elevatorMiddle");

        motorRight.setDirection(DcMotorSimple.Direction.REVERSE);

        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        final int middlePosition = 11300;
        final int lowPosition    = 4300 ;
        final int highPosition   = 17500;

        boolean a = false;
        boolean a_back = false;
        boolean b = false;
        boolean c = false;

        // region WAIT FOR START
        waitForStart();
        if (isStopRequested()) {return;}
        resetRuntime();
        // endregion


        while (opModeIsActive()) {



            if (gamepad1.a || a) {
                a = true;
                if (motorLeft.getCurrentPosition() < (lowPosition - 50)) {
                    elevatorMotors(0.95);
                    if (tick_past(calculateBrakingRange(motorLeft.getCurrentPosition(), lowPosition), motorLeft.getCurrentPosition())) {
                        elevatorMotors(0.6);
                        if (tick_past(lowPosition, motorLeft.getCurrentPosition())) {
                            elevatorMotors(0.15);
                        }
                    }
                }
                if (motorLeft.getCurrentPosition() > (lowPosition + 150)) {
                    elevatorMotors(-0.5);
                    if (tick_past(calculateBrakingRange(motorLeft.getCurrentPosition(), lowPosition), motorLeft.getCurrentPosition())) {
                        elevatorMotors(0.07);
                        if (tick_past(lowPosition, motorLeft.getCurrentPosition())) {
                            elevatorMotors(0.15);
                        }
                    }
                }
            }
            if (gamepad1.x || b){
                b = true;
                elevatorMotors(9.5);
                if(tick_past(calculateBrakingRange(motorLeft.getCurrentPosition(),middlePosition),motorLeft.getCurrentPosition())){
                    elevatorMotors(0.6);
                    if(tick_past(middlePosition,motorLeft.getCurrentPosition())){
                        elevatorMotors(0.15);
                        b = false;
                    }
                }
            }


            if (gamepad1.y || c){
                c = true;
                elevatorMotors(9.5);
                if(tick_past(calculateBrakingRange(motorLeft.getCurrentPosition(),highPosition),motorLeft.getCurrentPosition())){
                    elevatorMotors(0.6);
                    if(tick_past(highPosition,motorLeft.getCurrentPosition())){
                        elevatorMotors(0.15);
                        c = false;
                    }
                }
            }


        }
    }
}
