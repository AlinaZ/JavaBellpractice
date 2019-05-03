package com.example.demo.dao.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Country;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class CountryDaoImpl implements CountryDao {

    private final EntityManager em;

    @Autowired
    public CountryDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Country> all() {
        TypedQuery<Country> query = em.createQuery("SELECT c FROM Country c", Country.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Country loadById(Long id) {
        return em.find(Country.class, id);
    }


    @Override
    public Country loadByCode(String code) {
        List<Country> all=all();
        Country countryCoded=null;
        for(Country c:all){
            if(c.getCode().equals(code)){
                countryCoded=c;
            }
        }
        return countryCoded;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Country country) {
        em.persist(country);
    }
}
