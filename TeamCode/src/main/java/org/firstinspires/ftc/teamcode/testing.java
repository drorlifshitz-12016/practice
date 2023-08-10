package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class testing extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();
        if (isStopRequested()) { return; }
        resetRuntime();

        while (opModeIsActive()){

        }
    }
}
