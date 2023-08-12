package org.firstinspires.ftc.teamcode.swerve.dependencies;

import com.qualcomm.robotcore.hardware.AnalogInput;

public class Encoder {
    private final AnalogInput sensor;
    private final double offset;

    public Encoder(AnalogInput sensor, double offset){
        this.sensor = sensor;
        this.offset = offset;
    }
    public double getAngle(){
        // getting the pure reading and normalizing it
        double voltage = sensor.getVoltage() / sensor.getMaxVoltage();

        // fixing the nonsensical values (when the encoder nears 0 it's reading can go crazy)
        if (voltage > 1) { voltage = 0; }

        // accounting for the encoder offset and normalizing the resulting value between 0 and 1
        return (voltage + offset) % 1;
    }
}
