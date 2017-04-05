package edu.nyu.cs9053.homework8;

import java.time.LocalDateTime;

/**
 * User: Lei Su
 * Date: 4/2/17
 * Time: 11:40 AM
 */
class Job {
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final double profit;

    public Job(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
        this.profit = 0.0d;
    }  

    public Job(LocalDateTime start, LocalDateTime end, double profit) {
        this.start = start;
        this.end = end;
        this.profit = profit;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public double getProfit() {
        return profit;
    }

}