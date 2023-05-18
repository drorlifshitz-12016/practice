package org.firstinspires.ftc.teamcode.itay;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp
public class ManualDrive extends LinearOpMode {

    public boolean time_past(double first_time,double time_to_past){
        return first_time + time_to_past < getRuntime();
    }

    public void scoreCone() {
        double firstTime_puffer = getRuntime();
        pufferServo.setPosition(inflatedPosition);
        pufferRight.setPosition(P_outPosition);
        pufferLeft.setPosition(P_outPosition);
        time_past(firstTime_puffer,1);
        pufferServo.setPosition(releasePosition);
    }


    // endregion

    // region SERVO VARIABLES
    static Servo armRight;
    static Servo armLeft;
    // endregion

    static DigitalChannel isOutSensor;

    static Servo pufferServo;
    static Servo pufferRight;
    static Servo pufferLeft ;


    // (extend arm)
    static final double IN_POSITION = 0.55;
    static final double OUT_POSITION = 0.04;

    // collection highest
    final double[] heights = {0.7, 0.055, 0.06, 0.09, 0.13, 0.17};

    //grabber position
    final double grabPosition = 0.40;
    final double openPosition = 0.18;

    static double inflatedPosition    = 0.3;
    static double releasePosition = 0.2;
    static double P_inPosition  = 0.16;
    static double P_midPosition = 0.5 ;
    static double P_outPosition = 0.95;

    @Override
    public void runOpMode() {

        grabbers.grabberRight = hardwareMap.servo.get("grabberRight");
        grabbers.grabberLeft = hardwareMap.servo.get("grabberLeft");
        grabbers.grabber = hardwareMap.servo.get("grabber");

        isOutSensor = hardwareMap.get(DigitalChannel.class, "grabberIsOutSensor");
        DistanceSensor grabberDistanceToConeSensor = hardwareMap.get(DistanceSensor.class, "grabberDistanceToConeSensor");

        // region SERVO INITIALIZATION
        // get servos from the hardware map
        armRight = hardwareMap.servo.get("armRight");
        armLeft = hardwareMap.servo.get("armLeft");

        // region GET DRIVETRAIN MOTORS
        DcMotor frontRight = hardwareMap.dcMotor.get("frontRight");
        DcMotor frontLeft = hardwareMap.dcMotor.get("frontLeft");
        DcMotor backRight = hardwareMap.dcMotor.get("backRight");
        DcMotor backLeft = hardwareMap.dcMotor.get("backLeft");
        // endregion

        pufferRight = hardwareMap.servo.get("pufferRight");
        pufferLeft = hardwareMap.servo.get("pufferLeft");
        pufferServo = hardwareMap.servo.get("puffer");

        // region INITIALIZE THE IMU
        BNO055IMU imu = hardwareMap.get(BNO055IMU.class, "imu");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);
        // endregion

        // region SET MOTOR DIRECTION
        // reversing the right motors in order to have intuition for their movement direction
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        // endregion

        // set the right servo to be reverse
        armRight.setDirection(Servo.Direction.REVERSE);

        grabbers.grabberRight.setDirection(Servo.Direction.REVERSE);

        pufferRight.setDirection(Servo.Direction.REVERSE);

        // region CREATE VARIABLES FOR DRIVE TRAIN
        double x1;
        double y1;
        double x2;
        double y2;
        double L;
        double beta;
        double alpha;
        //endregion


        /// check before the test
        double puff_is_in = 0.14;


        boolean dpad;
        boolean lastest_dpad = false;
        boolean dpad_click;
        boolean is_in = true;
        boolean trigger;
        boolean lastest_trigger = false;


        double first_time_dpad = 0;
        double first_time_grab = 0;
        boolean firstTime = true;

        // region...
        // set the servos to be in
        ExtendArm.extendArm(0);
        // endregion

        grabbers.setPosition(heights[0]);

        grabbers.grabber.setPosition(openPosition);

        pufferRight.setPosition(puff_is_in);
        pufferLeft.setPosition(puff_is_in);
        // endregion

        // region WAIT FOR START
        waitForStart();
        if (isStopRequested()) {
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
            double frontRightPower = -y2 + x2 + gamepad1.right_stick_x;
            double frontLeftPower = -y2 - x2 - gamepad1.right_stick_x;
            double backRightPower = -y2 - x2 + gamepad1.right_stick_x;
            double backLeftPower = -y2 + x2 - gamepad1.right_stick_x;
            // endregion

            // region NORMALIZE MOTOR POWER

            // finds the highest absolute value of the non normalized motor powers
            double highestAbsoluteNum = Math.max(Math.max(Math.abs(frontRightPower), Math.abs(frontLeftPower)), Math.max(Math.abs(backRightPower), Math.abs(backLeftPower)));

            // if the motors aren't capable of the power requirement
            if (highestAbsoluteNum > 1) {
                // normalize the motor powers to be between -1 to 1
                frontRightPower /= highestAbsoluteNum;
                frontLeftPower /= highestAbsoluteNum;
                backRightPower /= highestAbsoluteNum;
                backLeftPower /= highestAbsoluteNum;
            }
            // endregion

            // region SET MOTOR POWER
            frontRight.setPower(frontRightPower);
            frontLeft.setPower(frontLeftPower);
            backRight.setPower(backRightPower);
            backLeft.setPower(backLeftPower);
            // endregion

            // region give power for opening intake
            ExtendArm.extendArm(gamepad1.left_trigger);
            // endregion

            dpad = lastest_dpad;
            lastest_dpad = gamepad1.dpad_up || gamepad1.dpad_right || gamepad1.dpad_down || gamepad1.dpad_left;
            dpad_click = lastest_dpad && !dpad;

            trigger = lastest_trigger;
            lastest_trigger = (gamepad1.left_trigger > 0.05);

            if (dpad_click) {
                is_in = !is_in;
            }

            if (lastest_trigger) {
                grabbers.setPosition(heights[1]);
            } else if (trigger) {
                grabbers.setPosition(heights[0]);

            } else if (dpad_click) {
                first_time_dpad = getRuntime();
                if (gamepad1.dpad_up) {grabbers.setPosition(heights[5]);grabbers.grabber.setPosition(openPosition);}
                else if (gamepad1.dpad_right) {grabbers.setPosition(heights[4]);grabbers.grabber.setPosition(openPosition);}
                else if (gamepad1.dpad_down ) {grabbers.setPosition(heights[3]);grabbers.grabber.setPosition(openPosition);}
                else if (gamepad1.dpad_left ) {grabbers.setPosition(heights[2]);grabbers.grabber.setPosition(openPosition);}
                else if (!is_in) {grabbers.setPosition(heights[0]);}
            }

            if (time_past(first_time_dpad, 0.5) && !is_in) {
                first_time_dpad = getRuntime();
                if (grabberDistanceToConeSensor.getDistance(DistanceUnit.CM) < 9) {
                    if (firstTime) {
                        grabbers.grabber.setPosition(grabPosition);
                        first_time_grab = getRuntime();
                        firstTime = false;
                    }
                    if (time_past(first_time_grab, 1.5)) {
                        grabbers.setPosition(heights[0]);
                        is_in = true;
                        firstTime = true;
                        first_time_dpad = 0;
                        if (gamepad1.a) {
                            grabbers.grabber.setPosition(openPosition);
                            scoreCone();
                        }
                    }
                }
            }


        }
    }
}

