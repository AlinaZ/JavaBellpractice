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

    @Override
    public Organization loadByName(String name) {
        List<Organization> all=all();
        Organization orgNamed=null;
        for(Organization org:all){
            if(org.getName().equals(name)){
                orgNamed=org;
            }
        }
        return orgNamed;
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
    public void update(Organization organization, Long id) {
        Organization originalOrg = em.find(Organization.class, id);
        originalOrg.setName(organization.getName());
        originalOrg.setFullName(organization.getFullName());
        originalOrg.setInn(organization.getInn());
        originalOrg.setKpp(organization.getKpp());
        originalOrg.setAddress(organization.getAddress());
        if (organization.getPhone() != null) {
            originalOrg.setPhone(organization.getPhone());
        }
        if (organization.getIsActive()) {
            originalOrg.setIsActive(organization.getIsActive());
        }
        em.flush();
    }

}