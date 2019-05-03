package com.example.demo.dao.user;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager em;

    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> all() {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User loadById(Long id) {
        return em.find(User.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(User user) {
        em.persist(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(User user, Long id)  {
        User originalUser=em.find(User.class,id);
        originalUser.setFirstName(user.getFirstName());
        if(user.getLastName()!=null){originalUser.setLastName(user.getLastName());}
        if(user.getMiddleName()!=null){originalUser.setMiddleName(user.getMiddleName());}
        originalUser.setPosition(user.getPosition());
        if(user.getOffice()!=null){originalUser.setOffice(user.getOffice());}
        if(user.getCountry()!=null){originalUser.setCountry(user.getCountry());}
        originalUser.setDocument(user.getDocument());
        if(user.getPhone()!=null){originalUser.setPhone(user.getPhone());}
        originalUser.setIsIdentified(user.getIsIdentified());
        em.flush();
    }
}
