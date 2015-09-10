/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.golem;

import java.io.IOException;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Default
@LocalBean
@Stateless
public class VSTrophyGolem implements Golem {

    private static String VSTROPHY_ADDRESS = "http://vstrophy.league.fantasy.nfl.com";
    private static String NFL_LOGIN_ADDRESS = "https://id2.s.nfl.com/fans/login?s=fantasy&returnTo=http%3A%2F%2Fvstrophy.league.fantasy.nfl.com%2F";
    private static String USERNAME = "vstrophy";
    private static String PASSWORD = "g04l3m080815";

    @Override
    public void getData() throws IOException {
        Connection.Response res = Jsoup.connect(NFL_LOGIN_ADDRESS)
                .data("username", USERNAME, "password", PASSWORD)
                .method(Method.POST)
                .execute();
        Map<String, String> cookies = res.cookies();
        Document doc = Jsoup.connect(VSTROPHY_ADDRESS).
                cookies(cookies)
                .get();
    }

}
