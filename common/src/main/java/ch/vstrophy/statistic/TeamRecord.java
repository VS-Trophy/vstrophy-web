/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.statistic;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class TeamRecord {

    private int wins;
    private int losses;
    private int draws;

    private static final char DELIMITER = '-';

    public TeamRecord() {
        wins = 0;
        losses = 0;
        draws = 0;
    }

    public TeamRecord(int wins, int losses, int draws) {
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public void addWin() {
        ++this.wins;
    }

    public void addLoss() {
        ++this.losses;
    }

    public void addDraw() {
        ++this.draws;
    }

    public String toOutput() {
        StringBuilder builder = new StringBuilder();
        builder.append(wins).append(DELIMITER).append(losses);
        if (draws > 0) {
            builder.append(DELIMITER).append(draws);
        }
        return builder.toString();
    }

}
