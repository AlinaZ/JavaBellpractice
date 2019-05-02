package com.example.demo.dao.organization;

import com.example.demo.model.Organization;

import java.util.List;

/**
 * DAO для работы с Organization
 */
public interface OrganizationDao {
    /**
     * Получить все объекты Organization
     *
     * @return
     */
    List<Organization> all();

    /**
     * Получить Organization по идентификатору
     *
     * @param id
     * @return
     */
    Organization loadById(Long id);

    Organization loadByName(String name);

    /**
     * Сохранить Organization
     *
     * @param organization
     */
    void save(Organization organization);

    /**
     * Обновить организацию по Id
     * @param organization
     *
     */
    void update(Organization organization,Long id);
}
