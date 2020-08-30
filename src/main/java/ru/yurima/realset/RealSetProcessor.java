package ru.yurima.realset;

import java.util.Set;

public interface RealSetProcessor {
    double getNearestNumber(double d, Set<RealSet> set);
    RealSet getIntersection(Set<RealSet> set);

}
