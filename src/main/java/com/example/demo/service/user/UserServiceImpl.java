package com.example.demo.service.user;

import com.example.demo.dao.country.CountryDao;
import com.example.demo.dao.document.DocumentDao;
import com.example.demo.dao.office.OfficeDao;
import com.example.demo.dao.user.UserDao;
import com.example.demo.model.Country;
import com.example.demo.model.Document;
import com.example.demo.model.Office;
import com.example.demo.model.User;
import com.example.demo.model.mapper.MapperFacade;
import com.example.demo.service.user.UserService;
import com.example.demo.view.user.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserDao dao;
    private final OfficeDao ofcD;
    private final DocumentDao docD;
    private final CountryDao countryD;
    private final MapperFacade mapperFacade;

    @Autowired
    public UserServiceImpl(UserDao dao, OfficeDao ofcD, DocumentDao docD, CountryDao countryD, MapperFacade mapperFacade) {
        this.dao = dao;
        this.ofcD=ofcD;
        this.docD=docD;
        this.countryD=countryD;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void add(UserView view) {
        User user = new User(view.firstName, view.lastName,view.middleName,view.position,ofcD.loadById(Long.valueOf(view.officeId)),view.phone,docD.loadById(view.docId),countryD.loadById(Long.valueOf(view.citizenshipId)),view.isIdentified);
        dao.save(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserView> users() {
        List<User> all = dao.all();
        return all.stream()
                .map(mapUser())
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public UserView getUserById(Long id) {
        User userById=dao.loadById(id);
        return mapUser().apply(userById);
    }

    private Function<User,UserView> mapUser(){
        return u -> {
            UserView view= new UserView();
            view.id=String.valueOf(u.getId());
            view.firstName=u.getFirstName();
            view.lastName=u.getLastName();
            view.middleName=u.getMiddleName();
            view.docId=u.getDocument().getId();
            view.officeId=u.getOffice().getId();
            view.position=u.getPosition();
            view.phone=u.getPhone();
            view.citizenshipId=u.getCountry().getId();
            view.isIdentified=u.getIsIdentified();
            return view;
        };

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(UserView view,Long id) {
        Document doc=docD.loadById(Long.valueOf(view.docId));
        Office office=ofcD.loadById(Long.valueOf(view.officeId));
        Country country=countryD.loadById(Long.valueOf(view.citizenshipId));
        User user = new User(view.firstName, view.lastName,view.middleName,view.position,office,view.phone,doc,country,view.isIdentified);
        dao.update(user,id);
    }
}
