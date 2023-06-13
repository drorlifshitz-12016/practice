package org.firstinspires.ftc.teamcode.ido;
import com.qualcomm.robotcore.hardware.Servo;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class exetendArm extends LinearOpMode {



    static Servo grabberRight;
    static Servo grabberLeft;
    static Servo grabber;
    static Servo armRight;
    static Servo armLeft;

    static final double IN_POSITION = 0.55;
    static final double OUT_POSITION = 0.04;

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
        if(isStopRequested()){return;}
        resetRuntime();
        resetServos();
        while (opModeIsActive()){

            armLeft.setPosition(gamepad1.left_trigger * (OUT_POSITION - IN_POSITION) + IN_POSITION);
            armRight.setPosition(gamepad1.left_trigger * (OUT_POSITION - IN_POSITION) + IN_POSITION);
           if (gamepad1.left_trigger > 0 ) {

                grabberRight.setPosition(0.85);
                grabberLeft.setPosition(0.85);
            } else {

               grabberRight.setPosition(0.055);
               grabberLeft.setPosition(0.055);
           }

            if (gamepad1.right_trigger > 0) {
                grabber.setPosition(grabPosition);

            } else {
                grabber.setPosition(openPosition);
            }

        }


    }


}
