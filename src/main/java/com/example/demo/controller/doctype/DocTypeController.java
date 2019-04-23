package com.example.demo.controller.doctype;

import com.example.demo.service.doctype.DocTypeService;
import com.example.demo.view.doctype.DocTypeView;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)

public class DocTypeController {


    private final DocTypeService doctypeService;

    @Autowired
    public DocTypeController(DocTypeService doctypeService) {
        this.doctypeService = doctypeService;
    }

    @ApiOperation(value = "Получить список всех стран", httpMethod = "GET")
    @GetMapping("api/doctypes")
    public List<DocTypeView> doctypes() {
        return doctypeService.doctypes();
    }
}
