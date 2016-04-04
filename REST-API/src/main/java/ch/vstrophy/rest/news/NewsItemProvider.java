/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.vstrophy.rest.news;

import ch.vstrophy.entities.news.NewsItem;
import ch.vstrophy.entities.news.NewsItemEntityManager;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;


/**
 *
 * @author Fabi
*/
public class NewsItemProvider {
    
    @Inject
    private NewsItemEntityManager newsItemEntityManager;
    
    public List<NewsItem> getNewsItems(){
       return newsItemEntityManager.getAllNewsItems();
    }
    
}
