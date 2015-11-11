/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.statistic;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class NumericStatisticPoint extends AbstractStatisticPoint {

    double value;

    public NumericStatisticPoint(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toOutput() {
        return NORMAL_FORMAT.format(value);
    }

}
