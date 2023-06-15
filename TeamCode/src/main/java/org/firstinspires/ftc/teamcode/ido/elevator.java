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

        DcMotor motorRight = (DcMotorEx) hardwareMap.dcMotor.get("motorRight");
        DcMotor motorLeft = (DcMotorEx) hardwareMap.dcMotor.get("motorLeft");
        DcMotor motorMiddle = (DcMotorEx) hardwareMap.dcMotor.get("motorMiddle");

        waitForStart();
        if (isStopRequested()) {return;}
        resetRuntime();


        while (opModeIsActive()){
            motorLeft.setPower(gamepad1.left_stick_y);
            motorRight.setPower(gamepad1.left_stick_y);
            motorMiddle.setPower(gamepad1.left_stick_y);



        }
    }
}