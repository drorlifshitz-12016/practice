package org.firstinspires.ftc.teamcode.yitzhak;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class project1 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();
        if(isStopRequested()){
            return;
        }
        resetRuntime();
    }
}
