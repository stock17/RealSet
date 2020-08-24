package ru.yurima.realset;

public class RealSet {
    private final double start;
    private final double end;
    private final boolean empty;

    private RealSet(double start, double end, boolean empty) {
        this.start = start;
        this.end = end;
        this.empty = empty;
    }

    public static RealSet create(double start, double end) {
        if (end > start)
            return new RealSet(start, end, false);
        else
            throw new IllegalArgumentException("Start should be greater than or equals end");
    }

    public static RealSet getEmptySet(){
        return new RealSet(0, 0, true);
    }

    public double getStart() {
        if (!empty)
            return start;
        else
            throw new IllegalStateException("The set is empty");
    }

    public double getEnd() {
        if (!empty)
            return end;
        else
            throw new IllegalStateException("The set is empty");
    }

    public boolean isEmpty() {
        return empty;
    }


}
