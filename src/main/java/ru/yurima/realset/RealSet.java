package ru.yurima.realset;

public class RealSet {
    private final double low;
    private final double high;
    private final boolean empty;

    private RealSet(double low, double high, boolean empty) {
        this.low = low;
        this.high = high;
        this.empty = empty;
    }

    public static RealSet newInstance(double low, double high) {
        if (low < high)
            return new RealSet(low, high, false);
        else
            throw new IllegalArgumentException("Start should be less than or equals end");
    }

    public static RealSet emptySet(){
        return new RealSet(0, 0, true);
    }

    public double getLow() {
        if (!empty)
            return low;
        else
            throw new IllegalStateException("The set is empty");
    }

    public double getHigh() {
        if (!empty)
            return high;
        else
            throw new IllegalStateException("The set is empty");
    }

    public boolean isEmpty() {
        return empty;
    }

    public RealSet intersect(RealSet other) {
        if (this.isEmpty() || other.isEmpty()) return emptySet();

        double low = Math.max(this.low, other.low);
        double high  = Math.min(this.high,   other.high);
        return (low < high) ?  newInstance(low, high) : emptySet();
    }

    public boolean contains(double d) {
        return (d >= low && d <= high);
    }


}
