package org.firstinspires.ftc.teamcode.itay.workingSwerve;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.itay.workingSwerve.dependencies.*;

@TeleOp
public class drive extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        swerveDrive.initialize(hardwareMap);

        waitForStart();
        if(isStopRequested()) { return; }
        resetRuntime();

        swerveDrive.start(gamepad1);

        while (opModeIsActive()){}

        swerveDrive.stop();
    }
}

