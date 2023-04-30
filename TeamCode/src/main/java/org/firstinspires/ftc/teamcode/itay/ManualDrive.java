package org.firstinspires.ftc.teamcode.itay;

import static org.firstinspires.ftc.teamcode.itay.ExtendArm.extendArm;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

@TeleOp
public class ManualDrive extends LinearOpMode {

    // region POSITION CONSTANTS
    // the positions of the servo for both the in and out arm positions
    static final double IN_POSITION = 0.55;
    static final double OUT_POSITION = 0.04;
    // endregion

    // region SERVO VARIABLES
    static Servo armRight;
    static Servo armLeft;
    // endregion


    static void extendArm(double position){
        armLeft .setPosition((OUT_POSITION - IN_POSITION) * position + IN_POSITION);
        armRight.setPosition((OUT_POSITION - IN_POSITION) * position + IN_POSITION);
    }

    @Override
    public void runOpMode(){

        // region GET DRIVETRAIN MOTORS
        DcMotor frontRight = hardwareMap.dcMotor.get("frontRight");
        DcMotor frontLeft  = hardwareMap.dcMotor.get("frontLeft" );
        DcMotor backRight  = hardwareMap.dcMotor.get("backRight" );
        DcMotor backLeft   = hardwareMap.dcMotor.get("backLeft"  );
        // endregion

        // region INITIALIZE THE IMU
        BNO055IMU imu = hardwareMap.get(BNO055IMU.class, "imu");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);
        // endregion

        // region SET MOTOR DIRECTION
        // reversing the right motors in order to have intuition for their movement direction
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight .setDirection(DcMotorSimple.Direction.REVERSE);
        // endregion

        // region CREATE VARIABLES FOR DRIVE TRAIN
        double x1;
        double y1;
        double x2;
        double y2;
        double L;
        double beta;
        double alpha;
        //endregion

        // region SERVO INITIALIZATION
        // get servos from the hardware map
        armRight = hardwareMap.servo.get("armRight");
        armLeft  = hardwareMap.servo.get("armLeft" );

        // set the right servo to be reverse
        armRight.setDirection(Servo.Direction.REVERSE);

        // set the servos to be in
        extendArm(0);
        // endregion

        // region WAIT FOR START
        waitForStart();
        if(isStopRequested()){
            return;
        }
        resetRuntime();
        // endregion


        while (opModeIsActive()) {

            // region MOTOR POWER CALCULATION
            // calculating the motor powers based on the three basic movements (straight, lateral and turn)

            x1 = gamepad1.left_stick_x;
            y1 = -gamepad1.left_stick_y;

            alpha = Math.atan2(y1, x1);
            beta = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle;
            L = Math.sqrt(x1 * x1 + y1 * y1);

            x2 = L * Math.cos(alpha + beta);
            y2 = L * Math.sin(alpha + beta);

            // region MOTOR POWER CALCULATION
            // calculating the motor powers based on the three basic movements (straight, lateral and turn)

            //                      [      straight       ] [       lateral       ] [         turn         ]
            double frontRightPower =          -y2           +          x2           + gamepad1.right_stick_x;
            double frontLeftPower  =          -y2           -          x2           - gamepad1.right_stick_x;
            double backRightPower  =          -y2           -          x2           + gamepad1.right_stick_x;
            double backLeftPower   =          -y2           +          x2           - gamepad1.right_stick_x;
            // endregion

            // region NORMALIZE MOTOR POWER

            // finds the highest absolute value of the non normalized motor powers
            double highestAbsoluteNum = Math.max(Math.max(Math.abs(frontRightPower), Math.abs(frontLeftPower)), Math.max(Math.abs(backRightPower), Math.abs(backLeftPower)));

            // if the motors aren't capable of the power requirement
            if(highestAbsoluteNum > 1) {
                // normalize the motor powers to be between -1 to 1
                frontRightPower /= highestAbsoluteNum;
                frontLeftPower  /= highestAbsoluteNum;
                backRightPower  /= highestAbsoluteNum;
                backLeftPower   /= highestAbsoluteNum;
            }
            // endregion

            // region SET MOTOR POWER
            frontRight.setPower(frontRightPower);
            frontLeft .setPower(frontLeftPower );
            backRight .setPower(backRightPower );
            backLeft  .setPower(backLeftPower  );
            // endregion


            // region give power for opening intake
            extendArm(gamepad1.right_trigger);
            // endregion

        }
    }
}
