package com.example.demo.dao.organization;

import com.example.demo.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    private final EntityManager em;

    @Autowired
    public OrganizationDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> all() {
        TypedQuery<Organization> query = em.createQuery("SELECT org FROM Organization org", Organization.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization loadById(Long id) {
        return em.find(Organization.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Organization organization) {
        em.persist(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Organization organization, Long id)  {
        Organization originalOrg=em.find(Organization.class,id);
        originalOrg.setName(organization.getName());
        originalOrg.setFull_name(organization.getFullName());
        originalOrg.setInn(organization.getInn());
        originalOrg.setKpp(organization.getKpp());
        originalOrg.setAddress(organization.getAddress());
        originalOrg.setPhone(organization.getPhone());
        originalOrg.setIsActive(organization.getIs_active());
        em.flush();
    }

}