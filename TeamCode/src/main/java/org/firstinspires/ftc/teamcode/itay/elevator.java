package org.firstinspires.ftc.teamcode.itay;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class elevator extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor motorRight = (DcMotorEx) hardwareMap.dcMotor.get("elevatorLeft");
        DcMotor motorMiddle = (DcMotorEx)hardwareMap.dcMotor.get("elevatorMiddle");
        DcMotor motorLeft = (DcMotorEx) hardwareMap.dcMotor.get("elevatorLeft");

        motorRight.setDirection(DcMotorSimple.Direction.REVERSE);

        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorMiddle.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        if (isStopRequested()) {return;}
        resetRuntime();


        while (opModeIsActive()){

            if(gamepad1.a) {
                motorRight.setTargetPosition(2000);
                motorLeft.setTargetPosition(2000);
                motorMiddle.setTargetPosition(2000);
                motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorMiddle.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorLeft.setPower(0.5);
                motorRight.setPower(0.5);
                motorMiddle.setPower(0.5);

            }
            if(gamepad1.b) {
                motorRight.setTargetPosition(0);
                motorLeft.setTargetPosition(0);
                motorMiddle.setTargetPosition(0);
                motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                motorMiddle.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                motorLeft.setPower(0);
                motorRight.setPower(0);
                motorMiddle.setPower(0);
            }

        }
    }
}