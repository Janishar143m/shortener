package com.url.shortener.repository;

import com.url.shortener.model.Url;
import com.url.shortener.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public class UrlRepository {

    @Autowired
    @PersistenceContext
    EntityManager entityManager;

    public void createUrl(Url url)
    {
      entityManager.merge(url);
    }

    public Url getUrl(String urlKey)
    {
        return entityManager.find(Url.class,urlKey);
    }

    public User getUser(String email)
    {
        return entityManager.find(User.class,email);
    }
    @Transactional
    public void createUser(User user)
    {
        entityManager.merge(user);
    }

    public void deleteUrl(Url url)
    {
        entityManager.remove(url);
    }

    public List<Url> expiredUrl ()
    {
        CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();
        CriteriaQuery<Url> criteriaQuery= criteriaBuilder.createQuery(Url.class);
        Root<Url>urlRoot= criteriaQuery.from(Url.class);
        criteriaQuery.where(criteriaBuilder.lessThanOrEqualTo(urlRoot.get("expirationDate"),new Date()));
        TypedQuery<Url> typedQuery= entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

}
