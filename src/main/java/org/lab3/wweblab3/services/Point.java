package org.lab3.wweblab3.services;

import jakarta.ejb.Remove;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.lab3.wweblab3.utilbeans.RegMBeans;

import java.io.Serializable;

@Named("p_bean")
@ApplicationScoped
public class Point implements Serializable, PointMBean
{
    public Point(){

    }
    @Inject
    public Point(PointCollection collection, RegMBeans reg){
        this.collection = collection;
        this.reg = reg;
        reg.registerBean(this);
    }
    RegMBeans reg;
    private PointCollection collection;

    public PointCollection getPoints() {
        return collection;
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
    @Override
    public void addPoint()
    {
        collection.addPoint(x,y,r);
    }
    @Override
    public void clearPoints()
    {
        collection.clear();
    }
    @Remove
    public void dispose(){
        System.out.println("Some point bean was removed");
        reg.unregisterBean(this);
    }
}
