package org.firstinspires.ftc.teamcode.ido;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class elevator extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        double getCurrentPosition;
        DcMotor motorRight = (DcMotorEx) hardwareMap.dcMotor.get("elevatorRight");
        DcMotor motorLeft = (DcMotorEx) hardwareMap.dcMotor.get("elevatorLeft");
        DcMotor motorMiddle = (DcMotorEx) hardwareMap.dcMotor.get("elevatorMiddle");

        motorRight.setDirection(DcMotorSimple.Direction.REVERSE);

        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();
        telemetry.addData("tick:", motorLeft.getCurrentPosition());
        while (opModeIsActive()){
            motorLeft.setPower(gamepad1.left_trigger);
            motorRight.setPower(gamepad1.left_trigger);
            motorMiddle.setPower(gamepad1.left_trigger);
        if (gamepad1.a);
        motorLeft.getCurrentPosition();


            telemetry.update();


        }
    }
}