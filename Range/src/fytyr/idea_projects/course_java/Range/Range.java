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
        return ((from <= x) && (x <= to));
    }

    public boolean isIntersect(Range range) {
        return (((from > range.from) && (from <= range.to)) ||
                ((from <= range.from) && (range.from <= to)));
    }

    public Range getIntersection(Range range) {
        double inFrom = from;
        double inTo = to;

        if ((from >= range.to) || (range.from >= to)) {
            return null;
        }

        inFrom = (range.from > from) ? range.from : inFrom;

        if (from < range.from) {
            inTo = (range.to > to) ? inTo : range.to;
        } else {
            inTo = (range.to < to) ? range.to : inTo;
        }

        return new Range(inFrom, inTo);
    }

    public Range[] getMerger(Range range) {
        double mergeFrom = from;
        double mergeTo = to;

        Range merger = new Range(mergeFrom, mergeTo);

        if (isIntersect(range)) {
            Range[] result = {merger};

            mergeFrom = (from > range.from) ? range.from : mergeFrom;
            mergeTo = (to > range.to) ? mergeTo : range.to;

            merger.setFrom(mergeFrom);
            merger.setTo(mergeTo);

            return result;
        }

        Range[] result = new Range[2];

        if (from < range.from) {
            result[0] = merger;
            result[1] = range;
        } else {
            result[0] = range;
            result[1] = merger;
        }
        return result;
    }

    public Range[] getDifference(Range range) {
        double differFrom = from;
        double differTo = to;

        Range differ = new Range(differFrom, differTo);
        Range[] result = {differ};

        if ((from >= range.to) || (range.from >= to)) {
            return result;
        }

        if (from < range.from) {
            if (to > range.to) {
                Range[] result2 = new Range[2];
                result2[0] = new Range(from, range.from);
                result2[1] = new Range(range.to, to);

                return result2;
            } else {
                differ.setTo(range.from);
            }
        } else if ((from > range.from) && (range.to < to)) {
            differ.setFrom(range.to);
        } else {
            return new Range[0];
        }
        return result;
    }
}
