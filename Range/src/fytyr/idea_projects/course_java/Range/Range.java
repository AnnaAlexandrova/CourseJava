package fytyr.idea_projects.course_java.Range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double x) {
        return (from <= x && x <= to);
    }

    public boolean isIntersect(Range range) {
        return (range.from < to && from < range.to);
    }

    public Range getIntersection(Range range) {
        if (!isIntersect(range)) {
            return null;
        }
        double inFrom = Math.max(from, range.from);
        double inTo = Math.min(to, range.to);

        return new Range(inFrom, inTo);
    }

    public Range[] getUnion(Range range) {
        if (range.from <= to && from <= range.to) {
            double unionFrom = Math.min(from, range.from);
            double unionTo = Math.max(to, range.to);

            Range[] result = {new Range(unionFrom, unionTo)};

            return result;
        }

        Range[] result = new Range[2];

        Range range1 = new Range(from, to);
        Range range2 = new Range(range.from, range.to);

        if (from < range.from) {
            result[0] = range1;
            result[1] = range2;
        } else {
            result[0] = range2;
            result[1] = range1;
        }
        return result;
    }

    public Range[] getDifference(Range range) {
        if (!isIntersect(range)) {
            Range[] result = {new Range(from, to)};
            return result;
        }

        double differFrom = from;
        double differTo = to;

        if (from < range.from) {
            if (to > range.to) {
                Range[] result2 = {new Range(from, range.from), new Range(range.to, to)};

                return result2;
            } else {
                differTo = range.from;
            }
        } else if (from >= range.from && range.to < to) {
            differFrom = range.to;
        } else {
            return new Range[0];
        }
        Range[] result = {new Range(differFrom, differTo)};

        return result;
    }
}
