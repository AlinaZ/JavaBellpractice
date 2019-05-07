package com.example.demo.service.doctype;

import com.example.demo.dao.doctype.DocTypeDao;
import com.example.demo.model.DocType;
import com.example.demo.model.mapper.MapperFacade;
import com.example.demo.view.doctype.DocTypeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class DocTypeServiceImpl implements DocTypeService {
    private final DocTypeDao dao;
    private final MapperFacade mapperFacade;

    @Autowired
    public DocTypeServiceImpl(DocTypeDao dao, MapperFacade mapperFacade) {
        this.dao = dao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void add(DocTypeView view) {
        DocType doctype = new DocType(view.name, view.code);
        dao.save(doctype);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<DocTypeView> doctypes() {
        List<DocType> all = dao.all();
        return mapperFacade.mapAsList(all, DocTypeView.class);
    }
}
