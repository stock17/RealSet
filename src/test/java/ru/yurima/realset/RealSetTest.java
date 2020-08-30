package ru.yurima.realset;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RealSetTest {

    @Test
    public void testCreateRealSet() {
        RealSet realSet = new RealSet(new RealSet.SubSet(Double.NEGATIVE_INFINITY, 10),
                new RealSet.SubSet(20, Double.POSITIVE_INFINITY));
        assertEquals(2, realSet.getSubSets().size());
    }


    /*
    *
    * 1)   (-inf)----------------- (-10)        (10)------------------(100)         (200)-----------------(+inf)
    *                   |              |        |             |       |  |          |                          |
    * 2)                (-50)------------------------------(50)       (100)-------------------------------(+inf)
    *                   |              |        |             |       |  |          |                          |
    * intersection       **************          *************         **            **************************
    *
    */

    @Test
    public void testIntersectRealSet() {
        RealSet realSet1 = new RealSet(new RealSet.SubSet(Double.NEGATIVE_INFINITY, -10),
                new RealSet.SubSet(10, 100), new RealSet.SubSet(200, Double.POSITIVE_INFINITY));

        RealSet realSet2 = new RealSet(new RealSet.SubSet(-50, 50),
                new RealSet.SubSet(100, Double.POSITIVE_INFINITY));

        RealSet intersection = realSet1.intersect(realSet2);

        assertEquals(4, intersection.getSubSets().size());
        assertTrue(intersection.getSubSets().contains(new RealSet.SubSet(-50, -10)));
        assertTrue(intersection.getSubSets().contains(new RealSet.SubSet(10, 50)));
        assertTrue(intersection.getSubSets().contains(new RealSet.SubSet(100, 100)));
        assertTrue(intersection.getSubSets().contains(new RealSet.SubSet(200, Double.POSITIVE_INFINITY)));
    }

}