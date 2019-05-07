package com.example.demo.service.office;

import com.example.demo.model.Office;
import com.example.demo.view.office.OfficeView;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * Сервис
 */
@Validated
public interface OfficeService {

    /**
     * Добавить office в БД
     *
     * @param office
     */
    void add(@Valid OfficeView office);

    /**
     * Получить список offices
     *
     * @return {@Person}
     */
    List<OfficeView> offices();
    /**
     * Получить office по идентификатору
     * @param id
     * @return
     */
    OfficeView getOfficeById(Long id);

    /**
     * Обновить office по идентификатору
     * @param office
     * @param id
     */
    void update(@Valid OfficeView office, Long id);
}
