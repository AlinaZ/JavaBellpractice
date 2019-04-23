package com.example.demo.dao.document;

import com.example.demo.model.Document;

import java.util.List;

/**
 * DAO для работы с Document
 */
public interface DocumentDao {
    /**
     * Получить все объекты Document
     *
     * @return
     */
    List<Document> all();

    /**
     * Получить Document по идентификатору
     *
     * @param id
     * @return
     */
    Document loadById(Long id);

    /**
     * Сохранить Document
     *
     * @param document
     */
    void save(Document document);
}
