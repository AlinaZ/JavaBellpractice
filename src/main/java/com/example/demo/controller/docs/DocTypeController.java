package com.example.demo.controller.docs;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.doctype.DocTypeService;
import com.example.demo.view.doctype.DocTypeView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Api(value = "DocTypeController", description = "Управляет информацией о странах гражданства сотрудников")
@RestController
@RequestMapping(value = "/api/docs", produces = APPLICATION_JSON_VALUE)

public class DocTypeController {
    private final DocTypeService doctypeService;

    @Autowired
    public DocTypeController(DocTypeService doctypeService) {
        this.doctypeService = doctypeService;
    }

    @ApiOperation(value = "Получить список всех видов документов", httpMethod = "GET")
    @GetMapping("/")
    public List<DocTypeView> doctypes() {
        return doctypeService.doctypes();
    }
}
