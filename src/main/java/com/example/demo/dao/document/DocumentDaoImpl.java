package com.example.demo.dao.document;

import com.example.demo.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class DocumentDaoImpl implements DocumentDao {

    private final EntityManager em;

    @Autowired
    public DocumentDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Document> all() {
        TypedQuery<Document> query = em.createQuery("SELECT d FROM Document d", Document.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Document loadById(Long id) {
        return em.find(Document.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Document document) {
        em.persist(document);
    }
}
