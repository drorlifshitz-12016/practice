package org.firstinspires.ftc.teamcode.tahel;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp
public class Drive_Train extends LinearOpMode {

        //A function that receives the angle from the imu sensor
        // and the x and y reached by the joystick and outputs the new x and y.
    public double[] get_x2_y2 (double angle, double x1, double y1){
        double len  = Math.sqrt(Math.pow(x1, 2) + Math.pow(y1, 2));
        double alfa = Math.atan2(y1, x1);
        double x2   = len * Math.cos(alfa + angle);
        double y2   = len * Math.sin(alfa + angle);
        return new double[]{x2, y2};
    }

        //A function that accepts four values and returns the highest value
    public double MAX(double n1, double n2, double n3, double n4){
        double bigger1 = Math.max(n1, n2);
        double bigger2 = Math.max(n3, n4);
        double max_num = Math.max(bigger1, bigger2);
        return max_num;
    }
    @Override

    public void runOpMode() throws InterruptedException {
        // region GET DRIVETRAIN MOTORS, SERVO and Setting Variables
        DcMotor frontRight = hardwareMap.dcMotor.get("frontRight");
        DcMotor frontLeft  = hardwareMap.dcMotor.get("frontLeft");
        DcMotor backRight  = hardwareMap.dcMotor.get("backRight");
        DcMotor backLeft   = hardwareMap.dcMotor.get("backLeft");
        Servo grabberRight = hardwareMap.servo.  get("grabberRight");
        Servo grabberLeft  = hardwareMap.servo.  get("grabberLeft");
        Servo armRight     = hardwareMap.servo.  get("armRight");
        Servo armLeft      = hardwareMap.servo.  get("armLeft");
        Servo grabber = hardwareMap.servo.get("grabber");
        double[] heights   = new double[]{0.055, 0.06, 0.09, 0.13, 0.17, 0.7};
        double[] grabberPosition = new double[]{0.36 , 0.18};
        double grab = grabberPosition[0];
        double release = grabberPosition[1];
        double  supposedToBe    = heights[5];
        boolean upWasPressed    = false;
        boolean downWasPressed  = false;
        boolean leftWasPressed  = false;
        boolean rightWasPressed = false;
        DistanceSensor ds = hardwareMap.get(DistanceSensor.class , "grabberDistanceToConeSensor");
        // endregion

        // region SET MOTOR DIRECTION
        // reversing the right motors in order to have intuition for their movement direction
        frontLeft.setDirection   (DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection    (DcMotorSimple.Direction.REVERSE);
        grabberRight.setDirection(Servo.Direction.REVERSE);
        armRight.setDirection    (Servo.Direction.REVERSE);
        // endregion

        //imu - A sensor that contains a GYRO sensor, which we use to detect the orientation
        //of the robot at any given moment, and using the information for pitch-based travel

        // define new value - imu type BNO055IMU
        BNO055IMU imu;

        // asks from the BNO055IMU.class to get the imu sensor
        imu = hardwareMap.get(BNO055IMU.class, "imu");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        // changes degrees to radians
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);

        //Keeps the arm closed
//        armRight.setPosition(1);
//        armLeft.setPosition(1);
//        grabberRight.setPosition(heights[5]);
//        grabberLeft.setPosition(heights[5]);
        grabber.setPosition(release);

        waitForStart();
        if (isStopRequested()) {
            return;
        }
        resetRuntime();




        while (opModeIsActive()) {

            if(ds.getDistance(DistanceUnit.MM) < 30){
                grabber.setPosition(grab);
            }

            //Opens and closes the arm by pressing the trigger
            telemetry.addData("position" , supposedToBe);
            telemetry.update();
            if (gamepad1.left_trigger > 0) {
                armRight.setPosition(0);
                armLeft.setPosition(0);
                grabberRight.setPosition(heights[0]);
                grabberLeft.setPosition(heights[0]);
            } else {
                if (grabberLeft.getPosition() == heights[0]) {
                    armRight.setPosition(1);
                    armLeft.setPosition(1);
                    grabberRight.setPosition(heights[5]);
                    grabberLeft.setPosition(heights[5]);
                }
            }
            //Opens and closes the arm by pressing the dpad_up
            // to the desired height that is defined in the list of heights
            //double[] heights = new double[]{0.055, 0.06, 0.09, 0.13, 0.17, 0.7};
            if (gamepad1.dpad_up && !upWasPressed) {
                upWasPressed = true;
                if (supposedToBe == heights[5] || supposedToBe == heights[3] || supposedToBe == heights[2] || supposedToBe == heights[1]) {
                    grabberRight.setPosition(heights[4]);
                    grabberLeft.setPosition(heights[4]);
                    supposedToBe = heights[4];
                } else if(supposedToBe == heights[4]){
                    grabberRight.setPosition(heights[5]);
                    grabberLeft.setPosition(heights[5]);
                    supposedToBe = heights[5];
                }
            }
            //Opens and closes the arm by pressing the dpad_right
            // to the desired height that is defined in the list of heights
            if (gamepad1.dpad_right && !rightWasPressed) {
                rightWasPressed  = true;
                if (supposedToBe == heights[5] || supposedToBe == heights[4] || supposedToBe == heights[2] || supposedToBe == heights[1]) {
                    grabberRight.setPosition(heights[3]);
                    grabberLeft.setPosition(heights[3]);
                    supposedToBe = heights[3];
                } else if(supposedToBe == heights[3]){
                    grabberRight.setPosition(heights[5]);
                    grabberLeft.setPosition(heights[5]);
                    supposedToBe = heights[5];
                }
            }
            //Opens and closes the arm by pressing the dpad_down
            // to the desired height that is defined in the list of heights
            if (gamepad1.dpad_down && !downWasPressed) {
                downWasPressed  = true;
                if (supposedToBe == heights[5] || supposedToBe == heights[4] || supposedToBe == heights[3]|| supposedToBe == heights[1]) {
                    grabberRight.setPosition(heights[2]);
                    grabberLeft.setPosition(heights[2]);
                    supposedToBe = heights[2];
                } else if(supposedToBe == heights[2]){
                    grabberRight.setPosition(heights[5]);
                    grabberLeft.setPosition(heights[5]);
                    supposedToBe = heights[5];
                }
            }

            //Opens and closes the arm by pressing the dpad_left
            // to the desired height that is defined in the list of heights
            if (gamepad1.dpad_left && !leftWasPressed) {
                leftWasPressed  = true;
                if (supposedToBe == heights[5] || supposedToBe == heights[4] || supposedToBe == heights[3] || supposedToBe == heights[2]) {
                    grabberRight.setPosition(heights[1]);
                    grabberLeft.setPosition(heights[1]);
                    supposedToBe = heights[1];
                } else if(supposedToBe == heights[1]){
                    grabberRight.setPosition(heights[5]);
                    grabberLeft.setPosition(heights[5]);
                    supposedToBe = heights[5];
                }
            }

            //If the dpad was pressed the boolean variable changes to false
            // so that the dpad closes on the next press
            if(!gamepad1.dpad_up){
                upWasPressed    = false;
            }
            if(!gamepad1.dpad_right){
                rightWasPressed = false;
            }
            if(!gamepad1.dpad_left){
                leftWasPressed  = false;
            }
            if(!gamepad1.dpad_down){
                downWasPressed  = false;
            }

            //obtain the current orientation of the robot.
            //AxesReference.INTRINSIC - specifies the coordinate system that the angles should be returned in.
            //AngleUnit.RADIANS specifies that the angles should be returned in radians.
            double angle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle;

            double x1 = gamepad1.left_stick_x;
            double y1 = gamepad1.left_stick_y;
            double x2, y2;
            double[] x2_y2 = get_x2_y2(angle, x1, y1);
            x2 = x2_y2[0];
            y2 = x2_y2[1];

            // region MOTOR POWER CALCULATION
            // calculating the motor powers based on the three basic movements (straight, lateral and turn)
            //            [      straight       ] [       lateral       ] [         turn          ]
            double p1 =             x2           -          y2           +  gamepad1.right_stick_x;
            double p2 = -           x2           -          y2           -  gamepad1.right_stick_x;
            double p3 =             x2           -          y2           -  gamepad1.right_stick_x;
            double p4 = -           x2           -          y2           +  gamepad1.right_stick_x;
            // endregion

            // region NORMALIZE MOTOR POWER

            // finds the highest absolute value of the non normalized motor powers
            double bigger = MAX(Math.abs(p1), Math.abs(p2), Math.abs(p3), Math.abs(p4));
            // if the motors aren't capable of the power requirement
            if (bigger > 1) {
                p1 /= bigger;
                p2 /= bigger;
                p3 /= bigger;
                p4 /= bigger;
            }
            // endregion


            // region SET MOTOR POWER
            telemetry.addData("p1", p1);
            telemetry.addData("p2", p2);
            telemetry.addData("p3", p3);
            telemetry.addData("p4", p4);
            telemetry.addData("sensor" ,ds);
            telemetry.update();

            frontRight.setPower(p2);
            frontLeft.setPower (p1);
            backRight.setPower (p3);
            backLeft.setPower  (p4);
            // endregion


        }
    }
}

