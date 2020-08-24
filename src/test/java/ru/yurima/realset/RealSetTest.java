package ru.yurima.realset;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RealSetTest {
    
    @Test
    public void testCreateAndGet() {
        RealSet realSet = RealSet.newInstance(-10, +10);
        assertEquals(-10, realSet.getLow());
        assertEquals(10, realSet.getHigh());

        RealSet infinitySet = RealSet.newInstance(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        assertEquals(Double.NEGATIVE_INFINITY, infinitySet.getLow());
        assertEquals(Double.POSITIVE_INFINITY, infinitySet.getHigh());

        RealSet emptySet = RealSet.emptySet();
        assertThrows(IllegalStateException.class, emptySet::getLow);
        assertThrows(IllegalStateException.class, emptySet::getHigh);
    }

    @Test
    public void testIntersect() {
        RealSet realSet1 = RealSet.newInstance(-10, 20);
        RealSet realSet2 = RealSet.newInstance(-20, 10);
        RealSet intersection = realSet1.intersect(realSet2);

        assertEquals(-10, intersection.getLow());
        assertEquals(10, intersection.getHigh());

        intersection = intersection.intersect(RealSet.newInstance(40, 100));
        assertTrue( intersection.isEmpty());

        intersection = RealSet.newInstance(-100, 100);
        intersection = intersection.intersect(RealSet.emptySet());
        assertTrue( intersection.isEmpty());
    }

    @Test
    public void testContains() {
        RealSet realSet = RealSet.newInstance(-10, 10);
        assertTrue(realSet.contains(-10));
        assertTrue(realSet.contains(10));
        assertFalse(realSet.contains(-11));
        assertFalse(realSet.contains(11));

        RealSet infinitySet = RealSet.newInstance(0, Double.POSITIVE_INFINITY);
        assertFalse(infinitySet.contains(-1));
        assertFalse(infinitySet.contains(Double.NEGATIVE_INFINITY));
        assertTrue(infinitySet.contains(1));
        assertTrue(infinitySet.contains(Double.POSITIVE_INFINITY));
    }

}