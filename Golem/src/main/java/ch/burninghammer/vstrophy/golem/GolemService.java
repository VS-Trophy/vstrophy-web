/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.burninghammer.vstrophy.golem;

import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Kobashi
 */
@Singleton
@LocalBean
public class GolemService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(GolemService.class);
    @EJB
    private VSTrophyGolem golem;

    @PostConstruct
    private void init() {
        LOGGER.info("Golem summoned");
    }

    @Schedule(hour = "*", minute = "33", second = "0", persistent = false)
    private void run() {
        try {
            golem.updateCurrentWeek();
        } catch (IOException ex) {
            LOGGER.error("Could not update current week!", ex);
        }
    }

}
