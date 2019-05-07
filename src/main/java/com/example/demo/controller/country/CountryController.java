package com.example.demo.controller.country;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.country.CountryService;
import com.example.demo.view.country.CountryView;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api (value="CountryController", description="Управляет информацией о странах гражданства сотрудников")
@RestController
@RequestMapping(value = "/api/countries", produces = APPLICATION_JSON_VALUE)

public class CountryController {


    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @ApiOperation(value = "Получить список всех стран", httpMethod = "GET")
    @GetMapping("/")
    public List<CountryView> countries() {
        return countryService.countries();
    }
}
