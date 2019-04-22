package com.example.demo.service.country;

import org.springframework.validation.annotation.Validated;
import com.example.demo.view.country.CountryView;

import javax.validation.Valid;
import java.util.List;

/**
 * Сервис
 */
@Validated
public interface CountryService {

    /**
     * Добавить country в БД
     *
     * @param country
     */
    void add(@Valid CountryView country);

    /**
     * Получить список countries
     *
     * @return {@Person}
     */
    List<CountryView> countries();
}
