/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.vstrophy.entities.news;

import javax.enterprise.context.Dependent;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Dependent
public class NewsItemQueries {
    public CriteriaQuery<NewsItem> getAllNewsItems(CriteriaBuilder cb){
        CriteriaQuery<NewsItem> q = cb.createQuery(NewsItem.class);
        Root<NewsItem> root = q.from(NewsItem.class);
        q.select(root).orderBy(cb.desc(root.get(NewsItem_.publicationDate)));
        return q;
    }
    public CriteriaQuery<NewsItem> getNewsItemById(CriteriaBuilder cb, int id){
        CriteriaQuery<NewsItem> q = cb.createQuery(NewsItem.class);
        Root<NewsItem> root = q.from(NewsItem.class);
        q.select(root).where(cb.equal(root.get(NewsItem_.id), id));
        return q;
    }
}
