package com.crewmaker.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="cycleperiod")
public class CyclePeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cyclePeriodID")
    private int cyclePeriodId;

    @Column(name="cycleType")
    private String cycleType;

    @Column(name="cycleLength")
    private int cycleLength;

    @OneToMany(mappedBy="cyclePeriod", cascade= {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<Event> cyclePeriodEvents;

    public CyclePeriod(String cycleType, int cycleLength) {
        this.cycleType = cycleType;
        this.cycleLength = cycleLength;
    }

    public CyclePeriod() {}

    public int getCyclePeriodId() {
        return cyclePeriodId;
    }

    public void setCyclePeriodId(int cyclePeriodId) {
        this.cyclePeriodId = cyclePeriodId;
    }

    public String getCycleType() {
        return cycleType;
    }

    public void setCycleType(String cycleType) {
        this.cycleType = cycleType;
    }

    public int getCycleLength() {
        return cycleLength;
    }

    public void setCycleLength(int cycleLength) {
        this.cycleLength = cycleLength;
    }

    public Set<Event> getCyclePeriodEvents() {
        return cyclePeriodEvents;
    }

    public void setCyclePeriodEvents(Set<Event> cyclePeriodEvents) {
        this.cyclePeriodEvents = cyclePeriodEvents;
    }

    @Override
    public String toString() {
        return "CyclePeriod{" +
                "cyclePeriodId=" + cyclePeriodId +
                ", cycleType='" + cycleType + '\'' +
                ", cycleLength=" + cycleLength +
                '}';
    }
}
