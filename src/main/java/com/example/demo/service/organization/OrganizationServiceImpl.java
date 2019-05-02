package com.example.demo.service.organization;

import com.example.demo.dao.organization.OrganizationDao;
import com.example.demo.exceptionhandler.CustomException;
import com.example.demo.model.Organization;
import com.example.demo.view.SuccessView;
import com.example.demo.view.organization.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationDao dao;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao dao) {
        this.dao = dao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public SuccessView add(OrganizationView view) {
        Organization organization = new Organization();
        notNullFieldsCheck(view);
        if (view.address == null) {
            throw new CustomException("Не задан адрес организации");
        } else {
            organization.setName(view.name);
            organization.setFullName(view.fullName);
            organization.setInn(view.inn);
            organization.setKpp(view.kpp);
            organization.setAddress(view.address);
            if (view.phone != null) {
                organization.setPhone(view.phone);
            } else {
                organization.setPhone("Не задан");
            }
            if (view.isActive!=null) {
                organization.setIsActive(view.isActive);
            } else {
                organization.setIsActive(false);
            }
            dao.save(organization);
        }
        return new SuccessView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrgsListOutView> orgsFilter(OrgsListInView view) {
        List<Organization> all = dao.all();
        List<Organization> result = new ArrayList<>();
        if (view.name == null) {
            throw new CustomException("Не задано имя организации");
        } else {
            for (Organization org : all) {
                boolean compare;
                boolean compareName = org.getName().contains(view.name);
                compare = compareName;
                if (view.inn != null) {
                    boolean compareInn = org.getInn().equals(view.inn);
                    compare = compare && compareInn;
                }
                if (view.isActive!=null) {
                    boolean compareIsActive = (org.getIs_active() == view.isActive);
                    compare = compare && compareIsActive;
                }
                if (compare) {
                    result.add(org);
                }
            }
        }
        return result.stream()
                .map(mapOrgsList())
                .collect(Collectors.toList());
    }

    private Function<Organization, OrgsListOutView> mapOrgsList() {
        return o -> {
            OrgsListOutView view = new OrgsListOutView();
            view.id = o.getId();
            view.name = o.getName();
            view.isActive = o.getIs_active();
            return view;
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OrganizationView getOrgById(Long id) {
        Organization orgById = dao.loadById(id);
        if (orgById != null) {
            return mapOrganization().apply(orgById);
        } else {
            throw new CustomException("Нет организации с Id=" + id);
        }
    }

    private Function<Organization, OrganizationView> mapOrganization() {
        return o -> {
            OrganizationView view = new OrganizationView();
            view.id = o.getId();
            view.name = o.getName();
            view.fullName = o.getFullName();
            view.inn = o.getInn();
            view.kpp = o.getKpp();
            view.address = o.getAddress();
            view.phone = o.getPhone();
            view.isActive = o.getIs_active();
            return view;
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public SuccessView update(OrganizationView view) {
        if (view.id == null) throw new CustomException("Невозможно обновить организацию: не задан ID");
        if (dao.loadById(view.id) == null) {
            throw new CustomException("Организации, которую вы пытаетесь обновить, нет в базе, id=" + view.id);
        }
        notNullFieldsCheck(view);
        if (view.address == null) {
            throw new CustomException("Не задан адрес организации");
        } else {
            Organization organization = new Organization();
            organization.setName(view.name);
            organization.setFullName(view.fullName);
            organization.setInn(view.inn);
            organization.setKpp(view.kpp);
            organization.setAddress(view.address);
            if (view.phone != null) {
                organization.setPhone(view.phone);
            }
            if (view.isActive!=null) {
                organization.setIsActive(view.isActive);
            }
            dao.update(organization, view.id);
        }
        return new SuccessView();
    }

    private void notNullFieldsCheck(OrganizationView view){
        if (view.name == null) {
            throw new CustomException("Не задано название организации");
        }
        if (view.fullName == null) {
            throw new CustomException("Не задано полное название организации");
        }
        if (view.inn == null) {
            throw new CustomException("Не задан inn организации");
        }
        if (view.kpp == null) {
            throw new CustomException("Не задан kpp организации");
        }
    }
}
