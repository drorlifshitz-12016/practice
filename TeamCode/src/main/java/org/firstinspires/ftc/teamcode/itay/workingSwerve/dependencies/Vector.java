package org.firstinspires.ftc.teamcode.itay.workingSwerve.dependencies;

public class Vector{
    public double x, y;
    public Vector(double x, double y){
        this.x = x;
        this.y = y;
    }
    public Vector(){}

    public double getLength(){
        return Math.sqrt(x*x + y*y);
    }
    public double getAngle(){
        return (Math.atan2(x, y) / 2 / Math.PI + 1) % 1;
    }
    public void set(double x, double y){
        this.x = x;
        this.y = y;
    }
    public void add(double x, double y){
        this.x += x;
        this.y += y;
    }
    public void setByAngle(double a, double l){
        x = Math.sin(a * Math.PI * 2) * l;
        y = Math.cos(a * Math.PI * 2) * l;
    }
    public void normalize(double length){
        x /= length;
        y /= length;
    }
    public void addAngle(double angle){
        setByAngle((getAngle() + angle) % 1, getLength());
    }
}