package com.example.demo.service.office;

import com.example.demo.model.Office;
import com.example.demo.view.SuccessView;
import com.example.demo.view.office.*;
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
    SuccessView add(@Valid OfficeView office);

    /**
     * Получить список offices
     *
     * @return {@Person}
     */
    List<OfficeListOutView> officeFilter(OfficeListInView view);
    /**
     * Получить office по идентификатору
     * @param id
     * @return
     */
    OfficeView getOfficeById(Long id);

    /**
     * Обновить office по идентификатору
     * @param office
     */
    SuccessView update(@Valid OfficeView office);
}
