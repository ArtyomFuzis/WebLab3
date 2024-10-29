package org.lab3.wweblab3;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
@Named("p_bean")
@ApplicationScoped
public class PointBean implements Serializable
{
    private static final long serialVersionUID = 1L;
    //private final ArrayList<Point> collection= new ArrayList<>();

    /*public ArrayList<Point> getCollection() {
        return collection;
    }*/
    private PointCollection points = new PointCollection();

    public PointCollection getPoints() {
        return points;
    }

    private double r;
    private double x;
    private double y;

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void addPoint()
    {
        points.addPoint(x,y,r);
    }
    public void clearPoints()
    {
        points.clear();
    }

}
