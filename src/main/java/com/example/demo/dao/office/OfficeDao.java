package com.example.demo.dao.office;

import com.example.demo.model.Office;

import java.util.List;

/**
 * DAO для работы с Office
 */
public interface OfficeDao {
    /**
     * Получить все объекты Office
     *
     * @return
     */
    List<Office> all();

    /**
     * Получить Office по идентификатору
     *
     * @param id
     * @return
     */
    Office loadById(Long id);

    Office loadByName(String name);

    /**
     * Сохранить Office
     *
     * @param office
     */
    void save(Office office);

    /**
     * Обновить Office  по идентификатору
     * @param office
     * @param id
     */
    void update (Office office, Long id);

}
