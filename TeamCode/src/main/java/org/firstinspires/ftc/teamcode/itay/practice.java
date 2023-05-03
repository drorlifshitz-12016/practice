package org.firstinspires.ftc.teamcode.itay;

import static org.firstinspires.ftc.teamcode.itay.ExtendArm.extendArm;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp
public class practice extends LinearOpMode {

    // region POSITION CONSTANTS
    // the positions of the servo for both the in and out arm positions\

    private static void setPosition(double heights) {
        grabberRight.setPosition(heights);
        grabberLeft. setPosition(heights);
    }
    static Servo grabberRight;
    static Servo grabberLeft;

    static Servo grabber;

    @Override
    public void runOpMode(){

        DistanceSensor grabberDistanceToConeSensor = hardwareMap.get(DistanceSensor.class , "grabberDistanceToConeSensor");

        grabberRight = hardwareMap.servo.get("grabberRight");
        grabberLeft  = hardwareMap.servo.get("grabberLeft" );

        grabber = hardwareMap.servo.get("grabber");

        grabberRight.setDirection(Servo.Direction.REVERSE);

        final double[] heights = {0.7, 0.055, 0.06, 0.09, 0.13, 0.17};

        final double grabPosition = 0.40;
        final double releasePosition = 0.18;

        boolean dpad;
        boolean lastest_dpad = false;
        boolean dpad_click;
        boolean is_in = true;
        boolean trigger;
        boolean lastest_trigger = false;

        setPosition(heights[0]);

        // region WAIT FOR START
        waitForStart();
        if(isStopRequested()){return;}
        resetRuntime();
        // endregion

        while (opModeIsActive()) {

            dpad = lastest_dpad;
            lastest_dpad = gamepad1.dpad_up || gamepad1.dpad_right || gamepad1.dpad_down || gamepad1.dpad_left;
            dpad_click = lastest_dpad && !dpad;

            trigger = lastest_trigger;
            lastest_trigger = (gamepad1.left_trigger > 0.05);

            if (dpad_click) {
                is_in = !is_in;
            }

            if (lastest_trigger) {
                setPosition(heights[1]);
            } else if (trigger) {
                setPosition(heights[0]);
            } else if (dpad_click) {
                if      (gamepad1.dpad_up   ) {setPosition(heights[5]);}
                else if (gamepad1.dpad_right) {setPosition(heights[4]);}
                else if (gamepad1.dpad_down ) {setPosition(heights[3]);}
                else if (gamepad1.dpad_left ) {setPosition(heights[2]);}
            }else if (!is_in){
                setPosition(heights[0]);
            }

            if((grabberDistanceToConeSensor.getDistance(DistanceUnit.MM) < 120)){
                grabber.setPosition(grabPosition);
                setPosition(heights[0]);
            }else{
                grabber.setPosition(releasePosition);
            }


        }
    }
}








// package org.firstinspires.ftc.teamcode.itay;
//
//         import static org.firstinspires.ftc.teamcode.itay.ExtendArm.extendArm;
//
//         import com.qualcomm.hardware.bosch.BNO055IMU;
//         import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//         import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//         import com.qualcomm.robotcore.hardware.DcMotor;
//         import com.qualcomm.robotcore.hardware.DcMotorSimple;
//         import com.qualcomm.robotcore.hardware.DistanceSensor;
//         import com.qualcomm.robotcore.hardware.Servo;
//
//         import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
//         import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
//         import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
//         import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
//
// @TeleOp
// public class practice extends LinearOpMode {
//
//     // region POSITION CONSTANTS
//     // the positions of the servo for both the in and out arm positions\
//
//     private static void setPosition(double heights) {
//         grabberRight.setPosition(heights);
//         grabberLeft. setPosition(heights);
//     }
//     static Servo grabberRight;
//     static Servo grabberLeft;
//
//     static Servo grabber;
//
//
//     @Override
//     public void runOpMode(){
//
//         grabberRight = hardwareMap.servo.get("grabberRight");
//         grabberLeft  = hardwareMap.servo.get("grabberLeft" );
//
//         grabberRight.setDirection(Servo.Direction.REVERSE);
//
//         final double[] heights = {0.7, 0.055, 0.06, 0.09, 0.13, 0.17};
//
//         boolean dpad;
//         boolean lastest_dpad = false;
//         boolean dpad_click;
//         boolean is_in = true;
//         boolean trigger;
//         boolean lastest_trigger = false;
//
//         setPosition(heights[0]);
//
//         // region WAIT FOR START
//         waitForStart();
//         if(isStopRequested()){return;}
//         resetRuntime();
//         // endregion
//
//         while (opModeIsActive()) {
//
//             // region give power for opening intake
//             extendArm(gamepad1.left_trigger);
//             // endregion
//
//             dpad = lastest_dpad;
//             lastest_dpad = gamepad1.dpad_up || gamepad1.dpad_right || gamepad1.dpad_down || gamepad1.dpad_left;
//             dpad_click = lastest_dpad && !dpad;
//
//             trigger = lastest_trigger;
//             lastest_trigger = (gamepad1.left_trigger > 0.05);
//
//             if (dpad_click) { is_in = !is_in; }
//
//             if (lastest_trigger) {
//                 setPosition(heights[1]);
//             } else if(trigger) {
//                 setPosition(heights[0]);
//             } else if (dpad_click){
//                 if      (gamepad1.dpad_up   ) { setPosition(heights[5]); }
//                 else if (gamepad1.dpad_right) { setPosition(heights[4]); }
//                 else if (gamepad1.dpad_down ) { setPosition(heights[3]); }
//                 else if (gamepad1.dpad_left ) { setPosition(heights[2]); }
//             } else if (!is_in) {
//                 setPosition(heights[0]);
//             }
//
//
//
//
//
//         }
//     }
// }
//
//