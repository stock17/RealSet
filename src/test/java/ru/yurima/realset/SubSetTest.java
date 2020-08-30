package ru.yurima.realset;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static ru.yurima.realset.RealSet.SubSet;

class SubSetTest {
    
    @Test
    public void testCreateAndGet() {

        SubSet subSet = new SubSet(-10, +10);
        assertEquals(-10, subSet.getLow());
        assertEquals(10, subSet.getHigh());

        SubSet infinitySet = new SubSet(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        assertEquals(Double.NEGATIVE_INFINITY, infinitySet.getLow());
        assertEquals(Double.POSITIVE_INFINITY, infinitySet.getHigh());
    }

    @Test
    public void testIntersect() {
        SubSet subSet1 = new SubSet(-10, 20);
        SubSet subSet2 = new SubSet(-20, 10);
        SubSet intersection = subSet1.intersect(subSet2);

        assertEquals(-10, intersection.getLow());
        assertEquals(10, intersection.getHigh());

        SubSet subSet3 = new SubSet(-10, 20);
        SubSet subSet4 = new SubSet(30, 50);
        intersection = subSet3.intersect(subSet4);

        assertNull(intersection);

        SubSet subSet5 = new SubSet(-10, 10);
        SubSet subSet6 = new SubSet(10, 20);
        intersection = subSet5.intersect(subSet6);

        assertNotNull(intersection);
        assertEquals(10, intersection.getLow());
        assertEquals(10, intersection.getHigh());

    }

    @Test
    public void testContains() {
        SubSet subSet = new SubSet(-10, 10);
        assertTrue(subSet.contains(-10));
        assertTrue(subSet.contains(10));
        assertFalse(subSet.contains(-11));
        assertFalse(subSet.contains(11));

        SubSet infinitySet = new SubSet(0, Double.POSITIVE_INFINITY);
        assertFalse(infinitySet.contains(-1));
        assertFalse(infinitySet.contains(Double.NEGATIVE_INFINITY));
        assertTrue(infinitySet.contains(1));
        assertTrue(infinitySet.contains(Double.POSITIVE_INFINITY));
    }

}