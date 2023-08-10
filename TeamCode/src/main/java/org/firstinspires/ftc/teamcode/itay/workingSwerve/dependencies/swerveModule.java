package org.firstinspires.ftc.teamcode.itay.workingSwerve.dependencies;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class swerveModule {
    // region DEVICES
    private final Encoder encoder;
    private final CRServo servo;
    private final DcMotor motor;
    // endregion

    // region VARIABLES
    private double currentAngle;
    private double wantedAngle = 0;

    private final double defaultAngle;

    // endregion
    public swerveModule(HardwareMap hm, String name, double angleOffset, double defaultAngle) {
        this(new Encoder(hm.get(AnalogInput.class, name), angleOffset),
                hm.get(CRServo.class, name),
                hm.get(DcMotor.class, name),
                defaultAngle);
    }

    public swerveModule(Encoder encoder, CRServo servo, DcMotor motor, double defaultAngle) {
        this.encoder = encoder;
        this.servo = servo;
        this.motor = motor;
        this.defaultAngle = defaultAngle;
    }

    // region GETTERS AND SETTERS
    public double getCurrentAngle() {
        return currentAngle;
    }

    public void setWantedAngle(double angle) {
        wantedAngle = angle;
    }

    public void setPower(double power) {
        motor.setPower((Math.abs(wantedAngle - currentAngle) > 0.25 ? -1 : 1) * power);
    }

    public void setVector(Vector v) {
        setPower(v.getLength());
        setWantedAngle(v.getAngle());
    }

    public void idle() {
        setPower(0);
        setWantedAngle(defaultAngle);
    }
    // endregion

    public void update() {
        // update the current angle
        currentAngle = encoder.getAngle();

        // update the power of the servo
        servo.setPower(calcPower(calcAngleDifference(wantedAngle, currentAngle)));
    }




    // region MOVEMENT DEFINING FUNCTIONS
    private double calcAngleDifference(double targetAngle, double currentAngle) {
        return (targetAngle - currentAngle + 1.25) % 0.5 - 0.25;
    }

    private static double calcPower(double angleDifference) {
        if (angleDifference > 0) {
            return -moveTypeDefinition(angleDifference);
        } else {
            return moveTypeDefinition(-angleDifference);
        }
    }

    // region CONSTANTS
    private final static double minPower = 0.12;
    private final static double maxPower = 1;

    private final static double acceptableError = 0.02;
    private final static double slowingDistance = 0.1;
    private final static double deceleratingDist = 0.13;

    // endregion
    private static double moveTypeDefinition(double x) {
        if (x < acceptableError) {
            return 0;
        } else if (x < slowingDistance) {
            return minPower;
        } else if (x < deceleratingDist) {
            return decelerationTypeDefinition(x);
        } else {
            return maxPower;
        }
    }

    private static double decelerationTypeDefinition(double x) {
        // linear deceleration
        return (x - slowingDistance) * (maxPower - minPower) / (deceleratingDist - slowingDistance) + minPower;
    }
    // endregion
}
