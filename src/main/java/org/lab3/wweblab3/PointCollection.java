package org.lab3.wweblab3;

import java.util.ArrayList;

public class PointCollection
{
    ArrayList<Point> points;
    public PointCollection()
    {
        points = new ArrayList<>();
    }
    public void addPoint(double x,double y, double r)
    {
        Point p = new Point(x,y,r);
        points.add(p);
    }
    @Override
    public String toString() {
        return getJson();
    }
    public String getJson()
    {
        return Point.toJson(points);
    }
    public void clear()
    {
        points.clear();
    }
}
