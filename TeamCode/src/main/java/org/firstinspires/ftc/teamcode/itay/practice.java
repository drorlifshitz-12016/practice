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
        grabberLeft  = hardwareMap.servo.get("grabberLeft" );

        grabberRight.setDirection(Servo.Direction.REVERSE);

        double[] highets = {0.7, 0.055, 0.06, 0.09, 0.13, 0.17};

        grabberRight.setPosition(highets[0]);
        grabberLeft .setPosition(highets[0]);

        boolean dpad;
        boolean lastest_dpad = false;
        boolean dpad_click;
        int dpad_num = 0;
        boolean is_in = true;
        boolean trigger;
        boolean lastest_trigger;
        boolean press_trigger;


        waitForStart();
        if (isStopRequested()) {
            return;
        }


        resetRuntime();
        while (opModeIsActive()) {


        dpad = lastest_dpad;
        lastest_dpad = gamepad1.dpad_up || gamepad1.dpad_right || gamepad1.dpad_down || gamepad1.dpad_left;
        dpad_click = lastest_dpad && !dpad;
        trigger = lastest_trigger;
        lastest_trigger = (gamepad1.left_trigger > 0);


        if (dpad_click){++dpad_num;}
        if (dpad_num % 2 == 0){is_in = true;}
        else {is_in = false;}

        if (gamepad1.left_trigger)




        }
    }
}
