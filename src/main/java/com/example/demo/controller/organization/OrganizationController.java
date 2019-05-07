package com.example.demo.controller.organization;

import com.example.demo.model.Organization;
import com.example.demo.service.organization.OrganizationService;
import com.example.demo.view.organization.OrganizationView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "OrganizationController", description = "Управляет информацией об организациях")
@RestController
@RequestMapping(value = "/api/organization", produces = APPLICATION_JSON_VALUE)

public class OrganizationController {


    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    /**
     * Возвращает список организаций
     *
     * @return
     */
    @ApiOperation(value = "Получить список всех организаций", httpMethod = "GET")
    @GetMapping("/list")
    public List<OrganizationView> organizations() {
        return organizationService.organizations();
    }

    /**
     * Получить организацию по Id
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "Получить организацию по Id", httpMethod = "GET")
    @GetMapping(value = "/{id}", headers = "Accept=application/json")
    public OrganizationView getOrgById(@PathVariable Long id) {
        return organizationService.getOrgById(id);
    }

    /**
     * Обновить существующую оганизацию (по Id)
     *
     * @param organization
     * @param id
     */
    @ApiOperation(value = "Обновить организацию по Id", httpMethod = "POST")
    @PostMapping(value = "/{id}", headers = "Accept=application/json")
    public void updateOrgById(@RequestBody OrganizationView organization, @PathVariable Long id) {
        organizationService.update(organization, id);
    }

    /**
     * Добавить новую организацию
     *
     * @param organization
     */
    @ApiOperation(value = "Добавить новую организацию", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/save")
    public void addNewOrg(@RequestBody OrganizationView organization) {
        organizationService.add(organization);
    }
}
