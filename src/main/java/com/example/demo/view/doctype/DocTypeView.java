package com.example.demo.view.doctype;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;

@ApiModel(description = "countries")
public class DocTypeView {

    @NotEmpty
    @ApiModelProperty(value = "Уникальный идентификатор", hidden = true, example = "1")
    public String id;

    @Size(max = 100)
    @NotEmpty(message = "doctypename cannot be null")
    @ApiModelProperty(value = "DocType name", example = "Russia")
    public String name;

    @NotNull(message = "code cannot be null")
    @Min(18)
    @Max(200)
    @ApiModelProperty(value = "Country code", example = "21")
    public String code;

    @Override
    public String toString() {
        return "{id:" + id + ";name:" + name + ";code:" + code + "}";
    }
}
