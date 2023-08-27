package org.firstinspires.ftc.teamcode.itay;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.broadcom.BroadcomColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.swerve.dependencies.Vector;

@TeleOp
public class fieldOriented extends LinearOpMode {
    @Override
    public void runOpMode() {


        // region GET DRIVETRAIN MOTORS
        DcMotor frontRight = hardwareMap.dcMotor.get("frontRight");
        DcMotor frontLeft  = hardwareMap.dcMotor.get("frontLeft" );
        DcMotor backRight  = hardwareMap.dcMotor.get("backRight" );
        DcMotor backLeft   = hardwareMap.dcMotor.get("backLeft"  );
        // endregion
        BNO055IMU imu = hardwareMap.get(BNO055IMU.class, "imu");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);

        // region SET MOTOR DIRECTION
        // reversing the right motors in order to have intuition for their movement direction
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight .setDirection(DcMotorSimple.Direction.REVERSE);
        // endregion

        Vector v1 = new Vector(0, 0);
        Vector v2 = new Vector(0, 0);

        double beta;
        double alpha;

        waitForStart();
        if (isStopRequested()) { return; }
        resetRuntime();


        while (opModeIsActive()){



            //v1.setByCardinal(gamepad1.left_stick_x, -gamepad1.left_stick_y);

            //alpha = v1.angle();
            beta = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle;

            //v2.setByAngular(v1.length(), alpha + beta);

                // region MOTOR POWER CALCULATION
                // calculating the motor powers based on the three basic movements (straight, lateral and turn)

                //                      [        straight       ] [         lateral       ] [         turn         ]
                double frontRightPower =          -v2.y           +          v2.x           + gamepad1.right_stick_x;
                double frontLeftPower  =          -v2.y           -          v2.x           - gamepad1.right_stick_x;
                double backRightPower  =          -v2.y           -          v2.x           + gamepad1.right_stick_x;
                double backLeftPower   =          -v2.y           +          v2.x           - gamepad1.right_stick_x;
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

      }
   }
}
