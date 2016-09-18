/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.entities.weeks;

import ch.vstrophy.entities.match.Match;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Entity
@Table(name = "weeks")
public class Week {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "season")
    private int season;
    @Column(name = "number")
    private int number;

    @OneToMany(mappedBy = "week", cascade = CascadeType.PERSIST)
    private List<Match> matches;

    public Week() {
        super();
    }

    public Week(int season, int number) {
        this.season = season;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public void addMatch(Match match) {
        if (matches == null) {
            matches = new ArrayList<>();
        }
        match.setWeek(this);
        matches.add(match);

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.season;
        hash = 31 * hash + this.number;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Week other = (Week) obj;
        if (this.season != other.season) {
            return false;
        }
        if (this.number != other.number) {
            return false;
        }
        return true;
    }

}
