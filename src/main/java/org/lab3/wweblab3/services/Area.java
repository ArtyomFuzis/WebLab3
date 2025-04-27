package org.lab3.wweblab3.services;

import jakarta.ejb.Remove;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.lab3.wweblab3.utilbeans.RegMBeans;

import java.io.Serializable;

@Named("a")
@SessionScoped
public class Area implements Serializable, AreaMBean
{
    public Area(){

    }
    @Inject
    public Area(Point pb, RegMBeans reg){
        this.pb = pb;
        this.reg = reg;
        reg.registerBean(this);
    }
    Point pb;
    RegMBeans reg;
    @Override
    public double getArea(){
        double r = pb.getR();
        double squareArea = r*r;
        double triangleArea = r*r/4;
        double semiCircleArea = Math.PI*r*r/4;
        return squareArea+triangleArea+semiCircleArea;
    }
    @Remove
    public void dispose(){
        System.out.println("Some area bean was removed");
        reg.unregisterBean(this);
    }
}
