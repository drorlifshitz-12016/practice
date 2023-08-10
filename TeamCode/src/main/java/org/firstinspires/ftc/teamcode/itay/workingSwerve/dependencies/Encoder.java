package org.firstinspires.ftc.teamcode.itay.workingSwerve.dependencies;

import com.qualcomm.robotcore.hardware.AnalogInput;

public class Encoder {
    private final AnalogInput sensor;
    private final double offset;

    public Encoder(AnalogInput sensor, double offset){
        this.sensor = sensor;
        this.offset = offset;
    }
    public double getAngle(){
        double voltage = sensor.getVoltage() / sensor.getMaxVoltage();
        if (voltage > 1) { voltage = 0; }
        return (voltage + offset) % 1;
    }
}
