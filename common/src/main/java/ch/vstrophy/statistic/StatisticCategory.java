/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.statistic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class StatisticCategory {

    private String name = "";
    private final List<StatisticPoint> statisticPoints = new ArrayList<>();

    public StatisticCategory(String name) {
        this.name = name;
    }

    public StatisticCategory() {
    }

    public void addStatisticPoint(final StatisticPoint point) {
        statisticPoints.add(point);
    }

    public List<StatisticPoint> getStatisticPoints() {
        return Collections.unmodifiableList(statisticPoints);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
