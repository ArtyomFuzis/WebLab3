package org.lab3.wweblab3.services;

import jakarta.ejb.Remove;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.lab3.wweblab3.entities.Point;
import org.lab3.wweblab3.repos.PointDAO;
import org.lab3.wweblab3.utilbeans.RegMBeans;

import java.io.Serializable;
import java.util.ArrayList;
@Named
@ApplicationScoped
public class PointCollection implements Serializable, PointCollectionMBean
{
    ArrayList<Point> points;
    public PointCollection(){

    }
    @Inject
    public PointCollection(Counter counter, RegMBeans reg)
    {
        this.counter = counter;
        points = new ArrayList<>();
        points.addAll(PointDAO.getInstance().getAll());
        this.reg = reg;
        reg.registerBean(this);
    }
    RegMBeans reg;
    private Counter counter;
    @Override
    public void addPoint(double x,double y, double r)
    {
        Point p = new Point(x,y,r);
        PointDAO.getInstance().save(p);
        points.add(p);
        counter.updateCounter(p.getRes());
    }
    @Override
    public String toString() {
        return getJson();
    }
    public String getJson()
    {
        return Point.toJson(points);
    }
    @Override
    public void clear()
    {
        PointDAO.getInstance().clear();
        points.clear();
        counter.clearCounter();
    }
    @Remove
    public void dispose(){
        System.out.println("Some pointCollection bean was removed");
        reg.unregisterBean(this);
    }
}
