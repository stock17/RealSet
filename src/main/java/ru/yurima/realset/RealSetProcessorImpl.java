package ru.yurima.realset;

import java.util.*;

public class RealSetProcessorImpl implements RealSetProcessor {

    @Override
    public double getClosestNumber(double x, List<RealSet> realSetList) {

        RealSet intersection = getIntersection(realSetList);
        if (intersection.contains(x)) return x;

        Set<RealSet.SubSet> subSets = intersection.getSubSets();

        double[] bounds = new double[subSets.size() * 2];
        int counter = 0;
        for (RealSet.SubSet subSet : subSets) {
            bounds[counter++] = subSet.getLow();
            bounds[counter++] = subSet.getHigh();
        }

        // Special cases for Infinity as an argument
        if (x == Double.POSITIVE_INFINITY)
            return getBiggestNumber(bounds);
        if (x == Double.NEGATIVE_INFINITY)
            return getSmallestNumber(bounds);


        double closestNumber = bounds[0];
        double minDistance = Math.abs(x - bounds[0]);

        for (int i = 1; i < bounds.length; i++) {
            double distance = Math.abs(x - bounds[i]);
            if (distance < minDistance) {
                minDistance = distance;
                closestNumber = bounds[i];
            }
        }
        return closestNumber;
    }

    private double getSmallestNumber(double[] array) {
        double smallestNumber = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < smallestNumber)
                smallestNumber = array[i];
        }
        return smallestNumber;
    }

    private double getBiggestNumber(double[] array) {
        double biggestNumber = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > biggestNumber)
                biggestNumber = array[i];
        }
        return biggestNumber;
    }

    @Override
    public RealSet getIntersection(List<RealSet> realSetList) {
        if (realSetList.size() < 1)
            return new RealSet(Collections.emptySet());

        if (realSetList.size() == 1)
            return realSetList.get(0);

        RealSet intersection = realSetList.get(0);

        for (int i = 1; i < realSetList.size(); i++) {
            intersection = intersection.intersect(realSetList.get(i));
        }

        return intersection;
    }
}
