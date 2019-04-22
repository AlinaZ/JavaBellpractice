package com.example.demo.service.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.dao.country.CountryDao;
import com.example.demo.model.Country;
import com.example.demo.model.mapper.MapperFacade;
import com.example.demo.view.country.CountryView;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class CountryServiceImpl implements CountryService {
    private final CountryDao dao;
    private final MapperFacade mapperFacade;

    @Autowired
    public CountryServiceImpl(CountryDao dao, MapperFacade mapperFacade) {
        this.dao = dao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void add(CountryView view) {
        Country country = new Country(view.name, view.code);
        dao.save(country);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<CountryView> countries() {
        List<Country> all = dao.all();
        return mapperFacade.mapAsList(all, CountryView.class);
    }
}
