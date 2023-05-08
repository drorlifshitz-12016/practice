package org.firstinspires.ftc.teamcode.swerve.driving;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp
public class moduleTesting extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        // p = power

        // 12   : ORR (overall reduction ratio)
        // 6000 : MMS (motor max speed)

        // 55.556 = 111.111 / 2 : servoRPM
        // 111.111 : initial servo rpm
        // 2       : SPR (servo pulley ratio)

        // WWS (wanted wheel speed) : inputSpeed * WMS
        // 495 : WMS (wheel max speed)
        // inputSpeed : 0 -> 1

        // WWS = (MMS * p - servoRPM) / ORR
        // WWS * ORR = MMS * p - servoRPM
        // WWS * ORR + servoRPM = MMS * p
        // (WWS * ORR + servoRPM) / MMS = p

        // p = (inputSpeed * WMS * ORR + servoRPM) / MMS

        waitForStart();
        if(isStopRequested()) { return; }
        resetRuntime();

    }
    // region CONSTANTS
    final static int motorReductionRatio = 12;
    final static int maxMotorSpeed = 6000;
    final static double initialServoSpeed = 111.111;
    final static double servoReductionRatio = 2;
    final static double servoRPM = initialServoSpeed / servoReductionRatio;

    final static double maxWheelSpeed = (maxMotorSpeed - servoRPM) / motorReductionRatio;
    // endregion
    static double calculateMotorSpeed(double power){
        return (power * maxWheelSpeed * motorReductionRatio + servoRPM) / maxMotorSpeed;
    }
}
