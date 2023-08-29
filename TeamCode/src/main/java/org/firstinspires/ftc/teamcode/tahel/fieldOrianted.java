package org.firstinspires.ftc.teamcode.tahel;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.checkerframework.framework.qual.TargetLocations;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

@TeleOp
public class fieldOrianted extends LinearOpMode {
    public BNO055IMU imu;

    @Override
    public void runOpMode() throws InterruptedException {
        imu = hardwareMap.get(BNO055IMU.class, "imu");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);

        waitForStart();
        if (isStopRequested()) {
            return;
        }
        resetRuntime();

        while (opModeIsActive()) {
            double angle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle;

            double x1 = gamepad1.left_stick_x;
            double y1 = gamepad1.left_stick_y;
            double x2, y2;
            double[] x2_y2 = get_x2_y2(angle, x1, y1);
            x2 = x2_y2[0];
            y2 = x2_y2[1];
        }
    }
    public double[] get_x2_y2(double angle , double x1, double y1){
        double len = Math.sqrt(Math.pow(x1, 2)+Math.pow(y1, 2));
        double alfa = Math.atan2(y1, x1);
        double x2 = len * Math.cos(alfa + angle);
        double y2 = len * Math.sin(alfa + angle);
        return new double[]{x2, y2};
    }
}

//  public BNO055IMU imu;

//      imu = hardwareMap.get(BNO055IMU.class, "imu");

//      BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
//      parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
//      imu.initialize(parameters);

//      BNO055IMUUtil.remapZAxis(imu, AxisDirection.NEG_X);


//blic static double getRobotAngle() {
//      // get the angle from the imu
//      double angle = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle;

//      // normalize the angle
//      if (angle < 0) {
//          angle += Math.PI * 2;
//      }
//      angle = Math.PI * 2 - angle;

//      return angle;
//  }

