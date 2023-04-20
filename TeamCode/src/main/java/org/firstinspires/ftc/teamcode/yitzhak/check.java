package org.firstinspires.ftc.teamcode.yitzhak;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class check extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor frontRight = hardwareMap.dcMotor.get("frontRight");
        DcMotor frontLeft  = hardwareMap.dcMotor.get("frontLeft" );
        DcMotor backRight  = hardwareMap.dcMotor.get("backRight" );
        DcMotor backLeft   = hardwareMap.dcMotor.get("backLeft"  );

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft .setDirection(DcMotorSimple.Direction.REVERSE);


        waitForStart();
        if(isStopRequested()){
            return;
        }
        resetRuntime();

        double t;
        double x;
        double y;
        while (opModeIsActive()){
            y = -gamepad1.left_stick_y;
            x =  gamepad1.left_stick_x;
            t =  gamepad1.right_stick_x;

            telemetry.addData("x", x);
            telemetry.update();
            double fR = (y-x-t);
            double fL = (y+x+t);
            double bR = (y+x-t);
            double bL = (y-x+t);

            double max1 = Math.max(Math.abs(fR) , Math.abs(fL));
            double max2 = Math.max(Math.abs(bR) , Math.abs(bL));
            double maxF = Math.max(max2 , max1);
            if(maxF>1) {
                fR /= maxF;
                fL /= maxF;
                bR /= maxF;
                bL /= maxF;
            }

            frontRight.setPower(fR);
            frontLeft .setPower(fL);
            backRight .setPower(bR);
            backLeft  .setPower(bL);

        }
    }
}
