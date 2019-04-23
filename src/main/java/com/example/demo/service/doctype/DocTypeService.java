package com.example.demo.service.doctype;

import com.example.demo.view.doctype.DocTypeView;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * Сервис
 */
@Validated
public interface DocTypeService {

    /**
     * Добавить doctype в БД
     *
     * @param doctype
     */
    void add(@Valid DocTypeView doctype);

    /**
     * Получить список doctypes
     *
     * @return {@Person}
     */
    List<DocTypeView> doctypes();
}
