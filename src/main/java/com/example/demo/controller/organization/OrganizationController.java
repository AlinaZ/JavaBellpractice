package com.example.demo.controller.organization;

import com.example.demo.view.SuccessView;
import com.example.demo.view.organization.*;
import com.example.demo.service.organization.OrganizationService;
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
     * по параметрам name, inn, isAcitve
     *
     * @return
     */
    @ApiOperation(value = "Получить список организаций по параметрам", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 400, message = "Not found"),
            @ApiResponse(code = 500, message = "Failure")
    })
    @PostMapping(value = "/list", consumes = "application/json", produces = "application/json")
    public @ResponseBody
    List<OrgsListOutView> getOrgsByParams(@RequestBody OrgsListInView view) {
        List<OrgsListOutView> orgs = organizationService.orgsFilter(view);
        return orgs;
    }

    /**
     * Получить организацию по Id
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "Получить организацию по Id", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 400, message = "Not found"),
            @ApiResponse(code = 500, message = "Failure")
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody
    OrganizationView getOrgById(@PathVariable Long id) {
        OrganizationView org = organizationService.getOrgById(id);
        return org;
    }

    /**
     * Обновить существующую оганизацию
     *
     * @param organization
     */
    @ApiOperation(value = "Обновить организацию", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 400, message = "Not found"),
            @ApiResponse(code = 500, message = "Failure")
    })
    @PostMapping(value = "/update", consumes = "application/json")
    public @ResponseBody
    SuccessView updateOrgById(@RequestBody OrgUpdView organization) {
        return organizationService.update(organization);
    }

    /**
     * Добавить новую организацию
     *
     * @param organization
     */
    @ApiOperation(value = "Добавить новую организацию", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 400, message = "Not found"),
            @ApiResponse(code = 500, message = "Failure")
    })
    @PostMapping(value = "/save", consumes = "application/json")
    public @ResponseBody
    SuccessView addNewOrg(@RequestBody OrgSaveView organization) {
        return organizationService.add(organization);
    }
}
