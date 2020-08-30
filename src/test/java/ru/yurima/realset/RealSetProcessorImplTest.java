package ru.yurima.realset;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.yurima.realset.RealSet.SubSet;

class RealSetProcessorImplTest {

    @Test
    public void testGetIntersection() {

        RealSetProcessor processor = new RealSetProcessorImpl();
        List<RealSet> realSetList = new ArrayList<>();
        realSetList.add(new RealSet(new SubSet(Double.NEGATIVE_INFINITY, 0),
                new SubSet(50, Double.POSITIVE_INFINITY)));
        realSetList.add(new RealSet(new SubSet(-100, 100)));
        realSetList.add(new RealSet(new SubSet(-10, 200)));

        RealSet intersection = processor.getIntersection(realSetList);

        assertEquals(2, intersection.getSubSets().size());
        assertTrue(intersection.getSubSets().contains(new SubSet(-10, 0)));
        assertTrue(intersection.getSubSets().contains(new SubSet(50, 100)));
    }

    @Test
    public void testGetClosestNumber() {
        RealSetProcessor processor = new RealSetProcessorImpl();
        List<RealSet> realSetList = new ArrayList<>();
        realSetList.add(new RealSet(new SubSet(-10, 10), new SubSet(30, 100)));

        assertEquals(0, processor.getClosestNumber(0, realSetList));
        assertEquals(-10, processor.getClosestNumber(-10, realSetList));
        assertEquals(-10, processor.getClosestNumber(-30, realSetList));
        assertEquals(-10, processor.getClosestNumber(Double.NEGATIVE_INFINITY, realSetList));
        assertEquals(30, processor.getClosestNumber(25, realSetList));
        assertEquals(70, processor.getClosestNumber(70, realSetList));
        assertEquals(100, processor.getClosestNumber(131, realSetList));
        assertEquals(100, processor.getClosestNumber(Double.POSITIVE_INFINITY, realSetList));
    }

}