package org.lab3.wweblab3;

import org.junit.Test;
import org.lab3.wweblab3.entities.Point;

import static org.junit.jupiter.api.Assertions.*;
public class PointTest {

    @Test
    public void calculateRes() {
        Point testPoint = new Point(1.0,1.0,1.0);
        testPoint.calculateRes();
        assertEquals(false,testPoint.getRes());
        Point testPoint2 = new Point(1.0,10.0,1.0);
        testPoint2.calculateRes();
        assertEquals(false,testPoint2.getRes());
        Point testPoint3 = new Point(-1.0,-1.0,10.0);
        testPoint3.calculateRes();
        assertEquals(true,testPoint3.getRes());
    }
}