/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.statistic;

import java.text.DecimalFormat;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public abstract class AbstractStatisticPoint implements StatisticPoint {

    protected static final DecimalFormat NORMAL_FORMAT = new DecimalFormat("#.###");
    protected static final DecimalFormat US_FORMAT = new DecimalFormat(".###");
    protected String name;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}
