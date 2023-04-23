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

        double[] highets = {0.7,0.055,0.06,0.09,0.13,0.17};

        grabberRight.setPosition(highets[0]);
        grabberLeft .setPosition(highets[0]);

        waitForStart();
        if(isStopRequested()){
            return;
        }

        resetRuntime();
        while (opModeIsActive()){

            if(gamepad1.left_trigger > 0.08) {
                grabberRight.setPosition(highets[1]);
                grabberLeft .setPosition(highets[1]);

            }else if(grabberRight.getPosition() == highets[0]) {

                if (gamepad1.dpad_up) {
                    grabberRight.setPosition(highets[5]);
                    grabberLeft.setPosition(highets[5]);

                } else if (gamepad1.dpad_right) {
                    grabberRight.setPosition(highets[4]);
                    grabberLeft.setPosition(highets[4]);

                } else if (gamepad1.dpad_down) {
                    grabberRight.setPosition(highets[3]);
                    grabberLeft.setPosition(highets[3]);

                } else if (gamepad1.dpad_left) {
                    grabberRight.setPosition(highets[2]);
                    grabberLeft.setPosition(highets[2]);

                } else if (grabberLeft.getPosition() != highets[0] && (gamepad1.dpad_up || gamepad1.dpad_right || gamepad1.dpad_down || gamepad1.dpad_left)) {
                    grabberLeft.setPosition(highets[0]);
                    grabberRight.setPosition(highets[0]);
                }
                if (gamepad1.dpad_up && grabberLeft.getPosition() == highets[5]) {
                    grabberLeft.setPosition(highets[0]);
                    grabberRight.setPosition(highets[0]);
                }
            }
        }
    }
}

