package com.example.demo.dao.country;

import com.example.demo.model.Country;

import java.util.List;

/**
 * DAO для работы с Country
 */
public interface CountryDao {
    /**
     * Получить все объекты Country
     *
     * @return
     */
    List<Country> all();

    /**
     * Получить Country по идентификатору
     *
     * @param id
     * @return
     */
    Country loadById(Long id);

    Country loadByCode(String code);

    /**
     * Сохранить Country
     *
     * @param country
     */
    void save(Country country);
}
