package com.example.demo.dao.office;

import com.example.demo.dao.organization.OrganizationDaoImpl;
import com.example.demo.model.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class OfficeDaoImpl implements OfficeDao {

    private final EntityManager em;
    private OrganizationDaoImpl orgDao;

    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> all() {
        TypedQuery<Office> query = em.createQuery("SELECT o FROM Office o", Office.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office loadById(Long id) {
        return em.find(Office.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Office office) {
        em.persist(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Office office, Long id)  {
        Office originalOffice=em.find(Office.class,id);
        originalOffice.setOrganization(office.getOrganization());
        originalOffice.setName(office.getName());
        originalOffice.setAddress(office.getAddress());
        originalOffice.setPhone(office.getPhone());
        originalOffice.setIs_active(office.getIs_active());
        em.flush();
    }
}
