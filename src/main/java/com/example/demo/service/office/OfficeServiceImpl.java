package com.example.demo.service.office;

import com.example.demo.dao.office.OfficeDao;
import com.example.demo.dao.organization.OrganizationDao;
import com.example.demo.model.Office;
import com.example.demo.model.Organization;
import com.example.demo.model.mapper.MapperFacade;
import com.example.demo.view.office.OfficeView;
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
public class OfficeServiceImpl implements OfficeService {
    private final OfficeDao dao;
    private final OrganizationDao orgD;
    private final MapperFacade mapperFacade;

    @Autowired
    public OfficeServiceImpl(OfficeDao dao, OrganizationDao orgD, MapperFacade mapperFacade) {
        this.dao = dao;
        this.orgD=orgD;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void add(OfficeView view) {
        Office office = new Office(orgD.loadById(Long.valueOf(view.orgId)),view.name,view.address,view.phone,view.isActive);
        dao.save(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OfficeView> offices() {
        List<Office> all = dao.all();

        return all.stream()
                .map(mapOffice())
                .collect(Collectors.toList());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public OfficeView getOfficeById(Long id) {
        Office officeById=dao.loadById(id);
        return mapOffice().apply(officeById);
    }


    private Function<Office,OfficeView> mapOffice(){
        return o -> {
            OfficeView view= new OfficeView();
            view.id=String.valueOf(o.getId());
            view.name=o.getName();
            view.orgId=String.valueOf(o.getOrganization().getId());
            view.address=o.getAddress();
            view.phone=o.getPhone();
            view.isActive=o.getIs_active();
            return view;
        };

    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OfficeView view,Long id) {
        Organization organization=orgD.loadById(Long.valueOf(view.orgId));
        Office office = new Office(organization,view.name, view.address,view.phone,view.isActive);
        dao.update(office,id);
    }
}
