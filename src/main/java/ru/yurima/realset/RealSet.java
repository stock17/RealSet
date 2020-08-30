package ru.yurima.realset;

import java.util.*;

public class RealSet {
    private Set<SubSet> subSets;

    public RealSet(SubSet...subSets) {
        this.subSets = Set.of(subSets);
    }

    public RealSet (Set<SubSet> subSets) {
        this.subSets = subSets;
    }

    public Set<SubSet> getSubSets() {
        return Set.copyOf(subSets);
    }

    public RealSet intersect (RealSet other) {
        if (this.subSets.isEmpty() || other.subSets.isEmpty())
            return new RealSet(Collections.emptySet());

        Set<SubSet> intersectedSets = new HashSet<>();
        for (SubSet thisSet : this.subSets) {
            for (SubSet otherSet : other.subSets) {
                SubSet intersection = thisSet.intersect(otherSet);
                if (intersection != null) {
                    intersectedSets.add(intersection);
                }
            }
        }
        return new RealSet(intersectedSets);
    }


    public static class SubSet {

        private double low;
        private double high;

        public SubSet(double low, double high) {
            if (low > high)
                throw new IllegalArgumentException("Low should be less than high");
            this.low = low;
            this.high = high;
        }

        public SubSet intersect(SubSet other) {
            double low = Math.max(this.low, other.low);
            double high  = Math.min(this.high,   other.high);
            return (low <= high) ?  new SubSet(low, high) : null;
        }

        public boolean contains(double d) {
            return (d >= low && d <= high);
        }

        public double getLow() {
            return low;
        }

        public double getHigh() {
            return high;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SubSet subSet = (SubSet) o;
            return Double.compare(subSet.low, low) == 0 &&
                    Double.compare(subSet.high, high) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(low, high);
        }
    }


}
