package com.example.demo.service.user;

import com.example.demo.dao.country.CountryDao;
import com.example.demo.dao.doctype.DocTypeDao;
import com.example.demo.dao.document.DocumentDao;
import com.example.demo.dao.office.OfficeDao;
import com.example.demo.dao.user.UserDao;
import com.example.demo.exceptionhandler.CustomException;
import com.example.demo.model.*;
import com.example.demo.view.SuccessView;
import com.example.demo.view.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private final CountryDao countryD;
    private final DocTypeDao dctD;
    private final DocumentDao docD;

    @Autowired
    public UserServiceImpl(UserDao dao, OfficeDao ofcD, CountryDao countryD, DocTypeDao dctD, DocumentDao docD) {
        this.dao = dao;
        this.ofcD = ofcD;
        this.countryD = countryD;
        this.dctD = dctD;
        this.docD = docD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public SuccessView add(UserSaveView view) {
        if (view.officeId == null) {
            throw new CustomException("Не задан идентификатор офиса, за которым закреплен сотрудник");
        }
        if (view.firstName == null) {
            throw new CustomException("Не задано имя сотрудника");
        }
        if (view.position == null) {
            throw new CustomException("Не задана должность сотрудника");
        } else {
            User user = new User();
            user.setFirstName(view.firstName);
            user.setPosition(view.position);
            user.setOffice(ofcD.loadById(view.officeId));
            if (view.lastName != null) {
                user.setLastName(view.lastName);
            }
            else {user.setLastName("-");}
            if (view.middleName != null) {
                user.setMiddleName(view.middleName);
            }
            else {user.setMiddleName("-");}
            if (view.phone != null) {
                user.setPhone(view.phone);
            }
            else {user.setPhone("-");}
            Document userdoc = new Document();

            if (view.docNumber != null) {
                userdoc.setNumber(view.docNumber);
            }
            else {userdoc.setNumber("-");}
            if (view.docDate != null) {
                userdoc.setDate(view.docDate);
            }
            else {userdoc.setDate(Date.valueOf("1800-12-12"));}
            if (view.docCode != null) {
                DocType dctByCode = dctD.loadByCode(view.docCode);
                if (dctByCode == null) {
                    throw new CustomException("Нет типа документа с таким кодом");
                } else {
                    userdoc.setDoctype(dctByCode);
                }
            }
            if (view.docName != null) {
                DocType dctByName = dctD.loadByName(view.docName);
                if (dctByName == null) {
                    throw new CustomException("Нет типа документа с таким названием");
                } else {
                    userdoc.setDoctype(dctByName);
                }
            }
            if (view.docCode != null && view.docName != null) {
                if (!(dctD.loadByCode(view.docCode).equals(dctD.loadByName(view.docName)))) {
                    throw new CustomException("Код и название типа документа не соответствуют");
                }
            }
            if (view.docCode == null && view.docName == null){
                throw new CustomException("Нельзя определить тип документа сотрудника");
            }
            user.setDocument(userdoc);
            userFieldsCheck(user,view);
            dao.save(user);
        }
        return new SuccessView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserListOutView> userFilter(UserListInView view) {
        List<User> all = dao.all();
        List<User> result = new ArrayList<>();
        if (view.officeId == null) {
            throw new CustomException("Не задан Id офиса, за которым закреплен сотрудник");
        } else {
            for (User user : all) {
                boolean compare;
                boolean compareOfficeId = user.getOffice().getId().equals(view.officeId);
                compare = compareOfficeId;
                if (view.firstName != null) {
                    boolean compareFirstName = user.getFirstName().equals(view.firstName);
                    compare = compare && compareFirstName;
                }
                if (view.lastName != null) {
                    boolean compareLasName = user.getLastName().equals(view.lastName);
                    compare = compare && compareLasName;
                }
                if (view.middleName != null) {
                    boolean compareMiddleName = user.getMiddleName().equals(view.middleName);
                    compare = compare && compareMiddleName;
                }
                if (view.position != null) {
                    boolean comparePosition = user.getPosition().equals(view.position);
                    compare = compare && comparePosition;
                }
                if (view.docCode != null) {
                    boolean compareDocCode = user.getDocument().getDoctype().getCode().equals(view.docCode);
                    compare = compare && compareDocCode;
                }
                if (view.citizenshipCode != null) {
                    boolean compareCitizenshipCode = user.getCountry().getCode().equals(view.citizenshipCode);
                    compare = compare && compareCitizenshipCode;
                }
                if (compare) {
                    result.add(user);
                }
            }
        }
        return result.stream()
                .map(mapUserList())
                .collect(Collectors.toList());
    }

    private Function<User, UserListOutView> mapUserList() {
        return u -> {
            UserListOutView view = new UserListOutView();
            view.id = u.getId();
            view.firstName = u.getFirstName();
            view.lastName = u.getLastName();
            view.middleName = u.getMiddleName();
            view.position = u.getPosition();
            return view;
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public UserView getUserById(Long id) {
        User userById = dao.loadById(id);
        if (userById != null) {
            return mapUser().apply(userById);
        } else {
            throw new CustomException("Нет сотрудника с Id=" + id);
        }
    }

    private Function<User, UserView> mapUser() {
        return u -> {
            UserView view = new UserView();
            view.id = u.getId();
            view.firstName = u.getFirstName();
            view.lastName = u.getLastName();
            view.middleName = u.getMiddleName();
            view.docName = u.getDocument().getDoctype().getName();
            view.docNumber = u.getDocument().getNumber();
            view.docDate = u.getDocument().getDate();
            view.officeId = u.getOffice().getId();
            view.position = u.getPosition();
            view.phone = u.getPhone();
            view.citizenshipCode = u.getCountry().getCode();
            view.isIdentified = u.getIsIdentified();
            return view;
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public SuccessView update(UserView view) {
        if (view.id == null) throw new CustomException("Невозможно обновить сотрудника: не задан ID");
        if (dao.loadById(view.id) == null) {
            throw new CustomException("Сотрудника, которого вы пытаетесь обновить, нет в базе, id=" + view.id);
        } else {
            if (view.firstName == null) {
                throw new CustomException("Не задано имя обновляемого сотруника");
            }
            if (view.position == null) {
                throw new CustomException("Не задана должность обновляемого сотруника");
            } else {
                User user = new User();
                user.setFirstName(view.firstName);
                user.setPosition(view.position);
                if (view.officeId != null) {
                    Office office = ofcD.loadById(view.officeId);
                    user.setOffice(office);
                }
                if (view.lastName != null) {
                    user.setLastName(view.lastName);
                }
                if (view.middleName != null) {
                    user.setMiddleName(view.middleName);
                }
                if (view.phone != null) {
                    user.setPhone(view.phone);
                }
                Document userDoc = new Document();
                if (view.docNumber != null) {
                    //если в базе есть такой документ (по номеру) - присваиваем его сотруднику
                    if (docD.loadByNumber(view.docNumber) != null) {
                        user.setDocument(docD.loadByNumber(view.docNumber));
                   }
                    //если задан номер, и такого документа нет, присваиваем номер и остальные допустимые параметры новому документу
                    else {
                        userDoc.setNumber(view.docNumber);
                        userDocDateNameCheck(user,userDoc,view);
                    }
                }
                //если номер не задан, присваиваем остальные допустимые параметры новому документу
               else {
                   userDoc.setNumber("не задан");
                   userDocDateNameCheck(user,userDoc,view);
                }
                userFieldsCheck(user,view);
                dao.update(user, view.id);
            }
        }
        return new SuccessView();
    }

    private void userDocDateNameCheck (User user, Document userDoc, UserView view){
        if (view.docDate != null) {
            userDoc.setDate(view.docDate);
        }
        if (view.docName != null) {
            if (dctD.loadByName(view.docName) == null) {
                throw new CustomException("В справочнике Виды документов нет такого документа");
            } else {
                userDoc.setDoctype(dctD.loadByName(view.docName));
            }
        }
        user.setDocument(userDoc);
    }

    private void userFieldsCheck(User user,UserView view){
        if (view.citizenshipCode != null) {
            if (countryD.loadByCode(view.citizenshipCode) == null) {
                throw new CustomException("В справочнике Страны нет такой страны");
            } else {
                user.setCountry(countryD.loadByCode(view.citizenshipCode));
            }
        }
        if (view.isIdentified!=null) {
            user.setIsIdentified(view.isIdentified);
        } else {
            user.setIsIdentified(false);
        }
    }

}
