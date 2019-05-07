package com.example.demo.service.organization;

import com.example.demo.model.Organization;
import com.example.demo.view.organization.OrganizationView;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * Сервис
 */
@Validated
public interface OrganizationService {

    /**
     * Добавить organization в БД
     *
     * @param organization
     */
    void add(@Valid OrganizationView organization);

    /**
     * Получить список organizations
     *
     * @return {@Organization}
     */
    List<OrganizationView> organizations();

    /**
     * Получить организацию по идентификатору
     * @param id
     * @return
     */
    OrganizationView getOrgById(Long id);

    /**
     * Обновить организацию по идентификатору
     * @param organization
     * @param id
     */
    void update(@Valid OrganizationView organization,Long id);

}
