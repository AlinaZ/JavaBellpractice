package com.example.demo.service.organization;

import com.example.demo.view.SuccessView;
import com.example.demo.view.organization.*;
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
    SuccessView add(@Valid OrganizationView organization);

    /**
     * Получить список organizations
     * по параметрам name, inn, isActive
     * @return {@Organization}
     */
    List<OrgsListOutView> orgsFilter(OrgsListInView view);

    /**
     * Получить организацию по идентификатору
     * @param id
     * @return
     */
    OrganizationView getOrgById(Long id);

    /**
     * Обновить организацию по идентификатору
     * @param organization
     *
     */
    SuccessView update(@Valid OrganizationView organization);

}
