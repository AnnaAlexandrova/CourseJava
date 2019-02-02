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
        boolean isInside = false;
        if ((from <= x) && (x <= to)) {
            isInside = true;
        }
        return isInside;
    }

    public boolean isIntersect(Range newRrange) {
        double newFrom = newRrange.getFrom();
        double newTo = newRrange.getTo();

        boolean isIntersect = false;

        if ((from > newFrom) && (from <= newTo)) {
            isIntersect = true;
        } else if ((from <= newFrom) && (newFrom <= to)) {
            isIntersect = true;
        }
        return isIntersect;
    }

    public Range getRangeIntersection(Range newRange) {
        double newFrom = newRange.getFrom();
        double newTo = newRange.getTo();
        double inFrom = from;
        double inTo = to;

        Range resultRange = new Range(inFrom, inTo);

        if (!isIntersect(newRange)) {
            return null;
        }

        inFrom = (newFrom > from) ? newFrom : inFrom;

        if (from < newFrom) {
            inTo = (newTo > to) ? inTo : newTo;
        } else {
            inTo = (newTo > to) ? newTo : inTo;
        }

        resultRange.setFrom(inFrom);
        resultRange.setTo(inTo);

        return resultRange;
    }

    public Range[] getRangeMerger(Range newRange) {
        double newFrom = newRange.getFrom();
        double newTo = newRange.getTo();
        double mergeFrom = from;
        double mergeTo = to;

        Range rangeMerger = new Range(mergeFrom, mergeTo);

        if (isIntersect(newRange)) {
            Range[] mergeRange = {rangeMerger};

            mergeFrom = (from > newFrom) ? newFrom : mergeFrom;
            mergeTo = (to > newTo) ? mergeTo : newTo;

            rangeMerger.setFrom(mergeFrom);
            rangeMerger.setTo(mergeTo);

            return mergeRange;
        }

        Range[] mergeRange = new Range[2];

        if (from < newFrom) {
            mergeRange[0] = rangeMerger;
            mergeRange[1] = newRange;
        } else {
            mergeRange[0] = newRange;
            mergeRange[1] = rangeMerger;
        }
        return mergeRange;
    }

    public Range[] getRangeDifference(Range newRange) {
        double newFrom = newRange.getFrom();
        double newTo = newRange.getTo();
        double differFrom = from;
        double differTo = to;

        Range rangeDiffer = new Range(differFrom, differTo);

        Range[] differRange = {rangeDiffer};
        Range[] differRange2 = new Range[2];
        Range[] differRange3 = new Range[0];

        if (!isIntersect(newRange)) {
            return differRange;
        }

        if (from < newFrom) {
            if (to > newTo) {
                differRange2[0] = new Range(from, newFrom);
                differRange2[1] = new Range(newTo, to);

                return differRange2;
            } else {
                rangeDiffer.setTo(newFrom);
            }
        } else if ((from > newFrom) && (newTo < to)) {
            rangeDiffer.setFrom(newTo);
        } else {
            System.out.println("Интервалы равны друг другу или уменьшаемый интервал полностью входит в вычитаемый");
            return differRange3;
        }
        return differRange;
    }
}
