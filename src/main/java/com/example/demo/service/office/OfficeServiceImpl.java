package com.example.demo.service.office;

import com.example.demo.dao.office.OfficeDao;
import com.example.demo.dao.organization.OrganizationDao;
import com.example.demo.exceptionhandler.OfficeException;
import com.example.demo.model.Office;
import com.example.demo.view.SuccessView;
import com.example.demo.view.office.*;
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
public class OfficeServiceImpl implements OfficeService {
    private final OfficeDao dao;
    private final OrganizationDao orgD;

    @Autowired
    public OfficeServiceImpl(OfficeDao dao, OrganizationDao orgD) {
        this.dao = dao;
        this.orgD = orgD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public SuccessView add(OfficeSaveView view) {
        Office office = new Office();
        if (view.orgId == null) {
            throw new OfficeException("Не задан идентификатор организации, которой принадлежит офис");
        } else {
            office.setOrganization(orgD.loadById(view.orgId));
            if (view.name != null) {
                office.setName(view.name);
            } else {
                office.setName("-");
            }
            if (view.address != null) {
                office.setAddress(view.address);
            } else {
                office.setAddress("-");
            }
            if (view.phone != null) {
                office.setPhone(view.phone);
            } else {
                office.setPhone("-");
            }
            if (view.isActive) {
                office.setIs_active(view.isActive);
            } else {
                office.setIs_active(true);
            }
        }
        dao.save(office);
        return new SuccessView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OfficeListOutView> officeFilter(OfficeListInView view) {
        List<Office> all = dao.all();
        List<Office> result = new ArrayList<>();
        if (view.orgId == null) {
            throw new OfficeException("Не задано Id организации,котрой принадлежат офисы");
        } else {
            for (Office office : all) {
                boolean compare;
                boolean compareOrgId = office.getOrganization().getId().equals(view.orgId);
                compare = compareOrgId;
                if (view.name != null) {
                    boolean compareNames = office.getName().equals(view.name);
                    compare = compare && compareNames;
                }
                if (view.phone != null) {
                    boolean comparePhones = office.getPhone().equals(view.phone);
                    compare = compare && comparePhones;
                }
                if (compare && office.getIs_active()) {
                    result.add(office);
                }
            }
        }
        return result.stream()
                .map(mapOfficeList())
                .collect(Collectors.toList());
    }

    private Function<Office, OfficeListOutView> mapOfficeList() {
        return o -> {
            OfficeListOutView view = new OfficeListOutView();
            view.id = String.valueOf(o.getId());
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
    public OfficeView getOfficeById(Long id) {
        Office officeById = dao.loadById(id);
        if (officeById != null) {
            return mapOffice().apply(officeById);
        } else {
            throw new OfficeException("Нет офиса с Id=" + id);
        }
    }


    private Function<Office, OfficeView> mapOffice() {
        return o -> {
            OfficeView view = new OfficeView();
            view.id = String.valueOf(o.getId());
            view.name = o.getName();
            view.orgId = String.valueOf(o.getOrganization().getId());
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
    public SuccessView update(OfficeUpdView view) {
        if (view.id == null) {
            throw new OfficeException("Невозможно обновить офис: не задан ID");
        }
        if (dao.loadById(view.id) == null) {
            throw new OfficeException("Офиса, который вы пытаетесь обновить, нет в базе, id=" + view.id);
        } else {
            Office office = new Office();
            if (view.name == null) {
                throw new OfficeException("Не задано название офиса");
            }
            if (view.address == null) {
                throw new OfficeException("Не задан адрес офиса");
            } else {
                office.setName(view.name);
                office.setAddress(view.address);
                if (view.phone != null) {
                    office.setPhone(view.phone);
                }
                if (view.isActive) {
                    office.setIs_active(view.isActive);
                }
                dao.update(office, view.id);
            }
        }
        return new SuccessView();
    }
}
