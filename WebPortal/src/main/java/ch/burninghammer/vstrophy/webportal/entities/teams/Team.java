/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.entities.teams;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

    @ElementCollection
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

    @Column(name = "team_uniform_pic")
    private byte[] uniformPicture;

    @Column(name = "team_logo")
    private byte[] logo;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TeamOfficial> getOfficials() {
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
        return uniformPicture;
    }

    public void setUniformPicture(byte[] uniformPicture) {
        this.uniformPicture = uniformPicture;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

}
