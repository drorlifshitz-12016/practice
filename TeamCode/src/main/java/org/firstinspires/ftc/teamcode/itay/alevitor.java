package org.firstinspires.ftc.teamcode.itay;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class alevitor extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor frontRight = hardwareMap.dcMotor.get("frontRight");
        DcMotor frontLeft  = hardwareMap.dcMotor.get("frontLeft" );
        DcMotor backRight  = hardwareMap.dcMotor.get("backRight" );
        DcMotor backLeft   = hardwareMap.dcMotor.get("backLeft"  );

        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight .setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        if (isStopRequested()) {return;}
        resetRuntime();


        while (opModeIsActive()){

        frontRight.setPower(gamepad1.left_trigger);
        frontLeft.setPower(gamepad1.left_trigger);
        backLeft.setPower(gamepad1.left_trigger);
        backRight.setPower(gamepad1.left_trigger);

        frontRight.setPower(-gamepad1.right_trigger);
        frontLeft.setPower(-gamepad1.right_trigger);
        backLeft.setPower(-gamepad1.right_trigger);
        backRight.setPower(-gamepad1.right_trigger);
        }
    }
}