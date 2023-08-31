package org.firstinspires.ftc.teamcode.swerve.dependencies;

import com.qualcomm.robotcore.hardware.AnalogInput;

public class encoder {
    private final AnalogInput sensor;
    private final double offset;

    public encoder(AnalogInput sensor, double offset){
        this.sensor = sensor;
        this.offset = offset;
    }
    public double getAngle(){
        double voltage = sensor.getVoltage() / sensor.getMaxVoltage();
        if (voltage > 1) { voltage = 0; }
        return (voltage + offset) % 1;
    }
}
