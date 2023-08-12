package org.firstinspires.ftc.teamcode.swerve.dependencies;

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
    private double wantedAngle;

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
        this.wantedAngle = defaultAngle;
    }

    private void setPower(double power) {
        /*
            if { Math.abs(wantedAngle - currentAngle) > 0.25 } is true that means that the wheel
            is backwards to the wanted direction and that we should reverse the power to achieve
            the wanted affect.
        */

        if (Math.abs(wantedAngle - currentAngle) > 0.25) motor.setPower(-power);
        else                                             motor.setPower( power);

    }
    public void setByVector(Vector v) {
        // set the power and direction of the module according to the vector

        setPower(v.getLength());
        wantedAngle = v.getAngle();
    }
    public void idle() {
        // stop movement and go to the default position

        setPower(0);
        wantedAngle = defaultAngle;
    }
    public void update() {
        // update the current angle
        currentAngle = encoder.getAngle();

        // update the power set to the servo
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
