package com.example.demo.controller.office;

import com.example.demo.service.office.OfficeService;
import com.example.demo.view.SuccessView;
import com.example.demo.view.office.*;
import com.sun.net.httpserver.Authenticator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "OfficeController", description = "Управляет информацией об организациях")
@RestController
@RequestMapping(value = "/api/office", produces = APPLICATION_JSON_VALUE)

public class OfficeController {


    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    /**
     * Получить список офисов по параметрам
     * orgId, name, phone, isActive
     *
     * @return
     */
    @ApiOperation(value = "Получить список всех офосов по параметрам", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 400, message = "Not found"),
            @ApiResponse(code = 500, message = "Failure")
    })
    @PostMapping(value = "/list", consumes = "application/json", produces = "application/json")
    public @ResponseBody
    List<OfficeListOutView> offices(@RequestBody OfficeListInView view) {
        return officeService.officeFilter(view);
    }

    /**
     * Получить офис по Id
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "Получить офис по Id", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 400, message = "Not found"),
            @ApiResponse(code = 500, message = "Failure")
    })
    @GetMapping(value = "/{id}", headers = "Accept=application/json")
    public @ResponseBody
    OfficeView getOfficeById(@PathVariable Long id) {
        return officeService.getOfficeById(id);
    }

    /**
     * Обновить существующий офис
     *
     * @param office
     */
    @ApiOperation(value = "Обновить офис", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 400, message = "Not found"),
            @ApiResponse(code = 500, message = "Failure")
    })
    @PostMapping(value = "/update", consumes = "application/json")
    public @ResponseBody
    SuccessView update(@RequestBody OfficeUpdView office) {
        return officeService.update(office);
    }

    /**
     * Добавить новый офис
     *
     * @param office
     */
    @ApiOperation(value = "Добавить новый офис", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping(value = "/save", consumes = "application/json")
    public @ResponseBody
    SuccessView addNewOffice(@RequestBody OfficeSaveView office) {
        return officeService.add(office);
    }
}
