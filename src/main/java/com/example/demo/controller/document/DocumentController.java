package com.example.demo.controller.document;

import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Validated
@RestController
//@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)

public class DocumentController {

    // @ApiOperation("Проверка доступности приложения")
    @RequestMapping(value = "/document", method = {GET, POST})
    public String ping() {
        return "pong";
    }

   /* @GetMapping("/test/{test}")
    public String test(@PathVariable @Size(min = 5, max = 100) String test) {
        return "OK";
    }*/
}
