package org.lab3.wweblab3;

import java.io.Serializable;
import java.util.ArrayList;

public class PointBean implements Serializable
{
    private static final long serialVersionUID = -465645;
    private final ArrayList<Point> collection= new ArrayList<>();

    public ArrayList<Point> getCollection() {
        return collection;
    }
}
