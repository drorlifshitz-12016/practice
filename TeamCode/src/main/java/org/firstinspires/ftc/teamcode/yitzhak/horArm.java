package org.firstinspires.ftc.teamcode.yitzhak;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;



@TeleOp
public class horArm extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        Servo armRight = hardwareMap.servo.get("armRight" );
        Servo armLeft  = hardwareMap.servo.get("armLeft"  );

        armRight.setDirection(Servo.Direction.REVERSE);
        double x;

        waitForStart();
        if(isStopRequested()){
            return;
        }
        while (opModeIsActive()) {

            x = gamepad1.left_trigger;

            armLeft.setPosition(x * (-0.51) + 0.55);
            armRight.setPosition(x * (-0.51) + 0.55);

        }
    }
}
