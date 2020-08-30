package ru.yurima.realset;

import java.util.List;
import java.util.Set;

public interface RealSetProcessor {
    double getClosestNumber(double d, List<RealSet> realSetList);
    RealSet getIntersection(List<RealSet> realSetList);

}
