package ru.yurima.realset;

import java.util.Set;

public class RealSet {
    private Set<SubSet> subSets;

    public RealSet(SubSet...subSets) {
        this.subSets = Set.of(subSets);
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
            return (low < high) ?  new SubSet(low, high) : null;
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
    }


}
