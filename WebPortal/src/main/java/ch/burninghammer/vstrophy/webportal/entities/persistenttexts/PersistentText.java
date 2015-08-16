/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.entities.persistenttexts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Entity
@Table(name = "persistenttexts")
public class PersistentText {

    @Id
    @Column(name = "persistenttext_id")
    private int id;

    @Column(name = "persistenttext_content")
    @Lob
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
