package org.firstinspires.ftc.teamcode.swerve.dependencies;

public class Vector {
    public double x, y;

    public Vector(double X, double Y){
        x = X;
        y = Y;
    }

    public void add(Vector v){
        x += v.x;
        y += v.y;
    }
    public void sub(Vector v){
        x -= v.x;
        y -= v.y;
    }
    public void div(Vector v){
        x /= v.x;
        y /= v.y;
    }
    public void mult(Vector v){
        x *= v.x;
        y *= v.y;
    }

    public double length(){
        return x*x + y*y;
    }
    public double angle(){
        return Math.atan2(y, x);
    }

    public void setByAngular(double l, double a){
        x = l * Math.cos(a);
        y = l * Math.sin(a);
    }
    public void setByCardinal(double X, double Y){
        x = X;
        y = Y;
    }
}
