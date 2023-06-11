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

    static double grabPosition = 0.40;
    static double openPosition = 0.18;
    static Servo pufferServo;
    static Servo rightServo ;
    static Servo leftServo  ;

    double P_inPosition  = 0.16;


    double inflatedPosition    = 0.3;
    double releasePosition = 0.2;




        public static void resetServos(){

        grabber.setPosition(openPosition);
        grabberLeft.setPosition(0.055);
        grabberRight.setPosition(0.055);

        rightServo.setPosition(0.16);
        leftServo.setPosition(0.16);

        
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

        pufferServo = hardwareMap.servo.get("puffer");
        rightServo  = hardwareMap.servo.get("pufferRight");
        leftServo   = hardwareMap.servo.get("pufferLeft" );

        rightServo.setDirection(Servo.Direction.REVERSE);


        waitForStart();
        if(isStopRequested()){
            return;
        }
        resetRuntime();

        while (opModeIsActive()){
            resetServos();

            while (gamepad1.left_trigger > 0 ) {
           
                grabberRight.setPosition(0.85);
                grabberLeft.setPosition(0.85);


            }

            while (gamepad1.right_trigger > 0) {
                grabber.setPosition(grabPosition);
            }

        }


    }


}
