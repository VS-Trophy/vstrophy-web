/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.vstrophy.golem;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Kobashi
 */
@Singleton
@Startup
@LocalBean
public class GolemService {

    @EJB
    private VSTrophyGolem golem;

    @PostConstruct
    private void init() {
    }

    @Schedule(second = "*/10", minute = "*", hour = "*", persistent = false)
    private void run() {
        try {
            golem.getData();
        } catch (IOException ex) {
            Logger.getLogger(GolemService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
