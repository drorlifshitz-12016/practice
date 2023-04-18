package org.firstinspires.ftc.teamcode.yitzhak;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class testing extends LinearOpMode {
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
            frontRight.setPower(y-x-t);
            frontLeft .setPower(y+x+t);
            backRight .setPower(y+x-t);
            backLeft  .setPower(y-x+t);
            
        }

    }
}
