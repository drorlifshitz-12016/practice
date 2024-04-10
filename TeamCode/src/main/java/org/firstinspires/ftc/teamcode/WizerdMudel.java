package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class WizerdMudel {

    CRServo servo;
    DcMotorEx motor;

    public WizerdMudel(CRServo servo,DcMotorEx motor){
        this.servo = servo;
        this.motor = motor;

    }


}
