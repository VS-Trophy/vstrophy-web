/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.statistic;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public interface StatisticPoint {

    public void setName(String name);

    public String getName();

    public String toOutput();
}
