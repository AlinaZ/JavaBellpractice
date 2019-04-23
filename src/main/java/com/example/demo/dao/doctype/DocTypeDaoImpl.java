package com.example.demo.dao.doctype;

import com.example.demo.dao.doctype.DocTypeDao;
import com.example.demo.model.DocType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class DocTypeDaoImpl implements DocTypeDao {

    private final EntityManager em;

    @Autowired
    public DocTypeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DocType> all() {
        TypedQuery<DocType> query = em.createQuery("SELECT dct FROM DocType dct", DocType.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DocType loadById(Long id) {
        return em.find(DocType.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(DocType doctype) {
        em.persist(doctype);
    }
}
