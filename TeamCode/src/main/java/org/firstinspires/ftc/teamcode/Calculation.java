package org.firstinspires.ftc.teamcode;

public class Calculation {

    double x;
    double y;

    public Calculation(double x, double y){
        this.x = x;
        this.y = y;
    }
    public double getLength(){return Math.sqrt(x*x + y*y);}
    public double getAngle(){
        return (Math.atan2(x, y) / 2 / Math.PI + 1) % 1;
    } // in radians
    public void set(double x, double y){
        this.x = x;
        this.y = y;
    }
    public void add(double x, double y){
        this.x += x;
        this.y += y;
    }

    public void normalize(double length){
        x /= length;
        y /= length;
    }


}