package com.example.demo.controller.office;

import com.example.demo.service.office.OfficeService;
import com.example.demo.view.office.OfficeView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value="OfficeController", description="Управляет информацией об организациях")
@RestController
@RequestMapping(value = "/api/office", produces = APPLICATION_JSON_VALUE)

public class OfficeController {


    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService){
        this.officeService=officeService;
    }

    /**
     * Получить список всех офисов
     *
     * @return
     */
    @ApiOperation(value = "Получить список всех офосов", httpMethod = "GET")
    @GetMapping("/list")
    public List<OfficeView> offices() {
        return officeService.offices();
    }

    /**
     * Получить офис по Id
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "Получить офис по Id", httpMethod = "GET")
    @GetMapping(value = "/{id}", headers = "Accept=application/json")
    public OfficeView getOfficeById(@PathVariable Long id) {
        return officeService.getOfficeById(id);
    }

    /**
     * Обновить существующий офис (по Id)
     *
     * @param office
     * @param id
     */
    @ApiOperation(value = "Обновить офис по Id", httpMethod = "POST")
    @PostMapping(value = "/{id}", headers = "Accept=application/json")
    public void updateOfficeById(@RequestBody OfficeView office, @PathVariable Long id) {
        officeService.update(office, id);
    }

    /**
     * Добавить новый офис
     *
     * @param office
     */
    @ApiOperation(value = "Добавить новуый офис", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/save")
    public void addNewOrg(@RequestBody OfficeView office) {
        officeService.add(office);
    }
}
