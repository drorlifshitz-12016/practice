package org.firstinspires.ftc.teamcode.ido;
import com.qualcomm.robotcore.hardware.Servo;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class exetendArm extends LinearOpMode {



    static Servo grabberRight;
    static Servo grabberLeft;
    static Servo grabber;
    static Servo armRight;
    static Servo armLeft;
    static final double IN_POSITION = 0.55;
    static final double OUT_POSITION = 0.04;
    static double[] heights = {0.7, 0.055, 0.06, 0.09, 0.13, 0.17};
    static double grabPosition = 0.40;
    final double openPosition = 0.18;
    public static void resetServos(){

        grabber.setPosition(grabPosition);
        grabberLeft.setPosition(0.055);
        grabberRight.setPosition(0.055);
        armLeft.setPosition(IN_POSITION);
        armRight.setPosition(IN_POSITION);


        
    }





    @Override
    public void runOpMode() throws InterruptedException {
        grabberRight = hardwareMap.servo.get("grabberRight");
        grabberLeft  = hardwareMap.servo.get("grabberLeft" );
        grabber      = hardwareMap.servo.get("grabber"     );
        armRight     = hardwareMap.servo.get("armRight"    );
        armLeft      = hardwareMap.servo.get("armLeft"     );
        grabberLeft.setDirection(Servo.Direction.REVERSE);
        armRight.setDirection(Servo.Direction.REVERSE);



        waitForStart();
        if(isStopRequested()){
            return;
        }
        resetRuntime();

        while (opModeIsActive()){
            resetServos();

            while (gamepad1.left_trigger > 0 ) {
                grabberRight.setPosition(OUT_POSITION);
                grabberLeft.setPosition(OUT_POSITION);
                armLeft.setPosition(gamepad1.left_trigger * 0.4);
                armRight.setPosition(gamepad1.left_trigger * 0.4);

            }

            while (gamepad1.right_trigger > 0) {
                grabber.setPosition(openPosition);
            }


        }


    }


}
