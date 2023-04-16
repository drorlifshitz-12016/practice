package org.firstinspires.ftc.teamcode.itay;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class practice extends LinearOpMode {

    static Servo grabberRight;
    static Servo grabberLeft ;

    @Override
    public void runOpMode() throws InterruptedException {

        grabberRight = hardwareMap.servo.get("grabberRight");
        grabberLeft  = hardwareMap.servo.get("grabberLeft" );

        grabberRight.setDirection(Servo.Direction.REVERSE);

        double inPosition = 0.7;
        double highLevel1 = 0.055;
        double highLevel2 = 0.06;
        double highLevel3 = 0.09;
        double highLevel4 = 0.13;
        double highLevel5 = 0.17;

        grabberLeft.setPosition(inPosition);
        grabberRight.setPosition(inPosition);

        waitForStart();
        if(isStopRequested()){
            return;
        }


        resetRuntime();
        while (opModeIsActive()){

            if(gamepad1.left_trigger > 0.08) {
                grabberRight.setPosition(highLevel1);
                grabberLeft.setPosition(highLevel1);

            }else if(gamepad1.dpad_up) {
                grabberRight.setPosition(highLevel5);
                grabberLeft.setPosition(highLevel5);

            }else if(gamepad1.dpad_right) {
                grabberRight.setPosition(highLevel4);
                grabberLeft.setPosition(highLevel4);

            }else if(gamepad1.dpad_down) {
                grabberRight.setPosition(highLevel3);
                grabberLeft.setPosition(highLevel3);

            }else if(gamepad1.dpad_left){
                grabberRight.setPosition(highLevel2);
                grabberLeft.setPosition(highLevel2);

            }
        }
    }
}

