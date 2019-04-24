package com.example.demo.controller.doctype;

import com.example.demo.service.doctype.DocTypeService;
import com.example.demo.view.doctype.DocTypeView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value="ВщсЕнзуController", description="Управляет информацией о видах удостоверяющих документов сотрудников")
@RestController
@RequestMapping(value = "/api/doctype", produces = APPLICATION_JSON_VALUE)

public class DocTypeController {


    private final DocTypeService doctypeService;

    @Autowired
    public DocTypeController(DocTypeService doctypeService) {
        this.doctypeService = doctypeService;
    }

    @ApiOperation(value = "Получить список всех видов документов", httpMethod = "GET")
    @GetMapping("/list")
    public List<DocTypeView> doctypes() {
        return doctypeService.doctypes();
    }
}
