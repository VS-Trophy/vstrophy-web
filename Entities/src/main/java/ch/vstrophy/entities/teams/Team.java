/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.entities.teams;

import ch.vstrophy.entities.divisions.Division;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Entity
@Table(name = "teams")
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private int id;

    @Column(name = "team_name", nullable = false)
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "team_official",
            joinColumns = @JoinColumn(name = "team_ID")
    )
    private List<TeamOfficial> officials;

    @Column(name = "team_founded_in")
    @Temporal(TemporalType.DATE)
    private Date foundedIn;

    @Column(name = "team_joined_in")
    @Temporal(TemporalType.DATE)
    private Date joinedIn;

    @Column(name = "team_colors")
    private String colors;

    @Column(name = "team_city")
    private String city;

    @Column(name = "team_stadium")
    private String stadium;

    @Column(name = "team_fans")
    private String fans;

    @Column(name = "team_uniform_pic", length = 102400)
    @Lob
    private byte[] uniformPicture;

    @Column(name = "team_logo", length = 102400)
    @Lob
    private byte[] logo;

    @ManyToOne
    private Division division;

    @Column(name = "team_nfl_id", unique = true, nullable = false)
    private int nflId;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public List<TeamOfficial> getOfficials() {
        if (officials == null) {
            return new ArrayList<>();
        }
        return officials;
    }

    public void setOfficials(List<TeamOfficial> officials) {
        this.officials = officials;
    }

    public Date getFoundedIn() {
        return foundedIn;
    }

    public void setFoundedIn(Date foundedIn) {
        this.foundedIn = foundedIn;
    }

    public Date getJoinedIn() {
        return joinedIn;
    }

    public void setJoinedIn(Date joinedIn) {
        this.joinedIn = joinedIn;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getFans() {
        return fans;
    }

    public void setFans(String fans) {
        this.fans = fans;
    }

    public byte[] getUniformPicture() {
        if (uniformPicture == null) {
            uniformPicture = new byte[0];
        }
        return uniformPicture;
    }

    public void setUniformPicture(byte[] uniformPicture) {
        this.uniformPicture = uniformPicture;
    }

    public byte[] getLogo() {
        if (logo == null) {
            logo = new byte[0];
        }
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public int getNflId() {
        return nflId;
    }

    public void setNflId(int nflId) {
        this.nflId = nflId;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.nflId;
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
        final Team other = (Team) obj;
        if (this.nflId != other.nflId) {
            return false;
        }
        return true;
    }

}
