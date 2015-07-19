/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.entities.teams;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Embeddable
public class TeamOfficial {

    private String name;
    private String function;

    @Column(name = "team_official_name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "team_official_function", nullable = false)
    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

}
