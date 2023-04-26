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
    static Servo grabberLeft;

    @Override
    public void runOpMode() throws InterruptedException {
        grabberRight = hardwareMap.servo.get("grabberRight");
        grabberLeft = hardwareMap.servo.get("grabberLeft");

        grabberRight.setDirection(Servo.Direction.REVERSE);

        double[] highets = {0.7, 0.055, 0.06, 0.09, 0.13, 0.17};

        grabberRight.setPosition(highets[0]);
        grabberLeft.setPosition(highets[0]);

        boolean last_dpad;
        boolean dpad = false;
        boolean dpad_click;
        boolean second_click = false;
        boolean is_high_0 = true;
        boolean triger_left = false;
        boolean last_triger_left;
        int dpad_num = 0;
        boolean out = false;

        waitForStart();
        if (isStopRequested()) {
            return;
        }

        resetRuntime();
        while (opModeIsActive()) {
            last_triger_left = triger_left;
            triger_left = (gamepad1.left_trigger > 0.1);

            last_dpad = dpad;
            dpad = gamepad1.dpad_up || gamepad1.dpad_right || gamepad1.dpad_down || gamepad1.dpad_left;
            dpad_click = dpad && !last_dpad;
            if(dpad_click){
                dpad_num = dpad_num++;
            }
            if(dpad_num % 2 != 0){
                out = true;
            }



            if (triger_left) {
                grabberRight.setPosition(highets[1]);
                grabberLeft.setPosition(highets[1]);
            }
            else if (last_triger_left && !triger_left) {
                grabberRight.setPosition(highets[0]);
                grabberLeft. setPosition(highets[0]);
            }

            if (dpad_click && is_high_0) {
                if (gamepad1.dpad_up) {
                    grabberRight.setPosition(highets[5]);
                    grabberLeft.setPosition(highets[5]);
                }
                else if (gamepad1.dpad_right) {
                    grabberRight.setPosition(highets[4]);
                    grabberLeft.setPosition(highets[4]);
                }
                else if (gamepad1.dpad_down) {
                    grabberRight.setPosition(highets[3]);
                    grabberLeft.setPosition(highets[3]);
                }
                else if (gamepad1.dpad_left) {
                    grabberRight.setPosition(highets[2]);
                    grabberLeft.setPosition(highets[2]);
                }
            }

            if (dpad_click && out) {
                grabberLeft.setPosition(highets[0]);
                grabberRight.setPosition(highets[0]);
            }
        }
    }
}
