package com.example.demo.dao.doctype;

import com.example.demo.model.DocType;

import java.util.List;

/**
 * DAO для работы с DocType
 */
public interface DocTypeDao {
    /**
     * Получить все объекты DocType
     *
     * @return
     */
    List<DocType> all();

    /**
     * Получить DocType по идентификатору
     *
     * @param id
     * @return
     */
    DocType loadById(Long id);

    /**
     * Сохранить DocType
     *
     * @param doctype
     */
    void save(DocType doctype);
}
