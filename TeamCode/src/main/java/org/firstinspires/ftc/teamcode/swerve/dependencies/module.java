package org.firstinspires.ftc.teamcode.swerve.dependencies;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class module {
    DcMotor motor;
    Servo servo;

    public static double cuculatePower(double sticLeft_x,double sticLeft_y,double sticRight_x,double sticRight_y){
       return (Math.sqrt(sticLeft_x * sticLeft_x + sticLeft_y * sticLeft_y)) + Math.sqrt(sticRight_x * sticRight_x + sticRight_y * sticRight_y);
    }


}
