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
public class elevator extends LinearOpMode {

    public static int where_are_now(int curentPos){
        if (curentPos < 310)                       {return 0;}
        if (curentPos < 4560 & curentPos > 4080)   {return 1;}
        if (curentPos < 11500 & curentPos > 11100) {return 2;}
        if (curentPos > 17250)                     {return 3;}
        else {return 4;}
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

    final double goingUpPower  = 1;
    final double holdingPower  = 0.23;
    final double goingDownPower= -0.2;
    final double GoingUpSlowlyPower = 0.65;

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

        int curentPosition = 0;
        int your_target = 0;
        boolean enterToLoop = false;
        boolean a = false;
        boolean x = false;
        boolean y = false;

        waitForStart();
        if (isStopRequested()) {return;}
        resetRuntime();


        while (opModeIsActive()){

            curentPosition = motorLeft.getCurrentPosition();

            if (gamepad1.b){your_target = 0;}
            if (gamepad1.a){your_target = lowPosition   ;}
            if (gamepad1.x){your_target = middlePosition;}
            if (gamepad1.y){your_target = highPosition  ;}

            if(gamepad1.a || gamepad1.x || gamepad1.y || enterToLoop){
                enterToLoop = true;
                if (your_target > where_are_now(your_target)){
                    elevatorMotors(goingUpPower);
                    if (tick_past(calculateBrakingRange(your_target,motorLeft.getCurrentPosition()),motorLeft.getCurrentPosition())){
                        elevatorMotors(GoingUpSlowlyPower);
                       if (tick_past(your_target,motorLeft.getCurrentPosition())){
                            elevatorMotors(holdingPower);
                            enterToLoop = false;
                        }
                    }
                }
                if (your_target < where_are_now(motorLeft.getCurrentPosition())){
                    elevatorMotors(goingDownPower);
                    if (tick_past(calculateBrakingRange(your_target,curentPosition),curentPosition)){
                        elevatorMotors(0.1);
                        if (tick_past(your_target,curentPosition)){
                            elevatorMotors(holdingPower);
                        }
                    }

                }
            }




            //if ((gamepad1.a || a) && where_are_you_now == 0){
            //    a = true;
            //    elevatorMotors(goingUpPower);
            //    if(tick_past(calculateBrakingRange(lowPosition,motorLeft.getCurrentPosition()),motorLeft.getCurrentPosition())){
            //        elevatorMotors(GoingUpSlowlyPower);
            //        if (tick_past(lowPosition,motorLeft.getCurrentPosition())){
            //            elevatorMotors(holdingPower);
            //            where_are_you_now = 1;
            //            a = false;
            //        }
            //    }
            //}
//
            //if ((gamepad1.x || x) && where_are_you_now < 2){
            //    x = true;
            //    elevatorMotors(goingUpPower);
            //    if(tick_past(calculateBrakingRange(middlePosition,motorLeft.getCurrentPosition()),motorLeft.getCurrentPosition())){
            //        elevatorMotors(GoingUpSlowlyPower);
            //        if (tick_past(middlePosition,motorLeft.getCurrentPosition())){
            //            elevatorMotors(holdingPower);
            //            where_are_you_now = 1;
            //            x = false;
            //        }
            //    }
            //}


            if (gamepad1.b){
                elevatorMotors(goingDownPower);
            }

        }
    }
}

