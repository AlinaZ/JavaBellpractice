package com.example.demo.view.doctype;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;

public class DocTypeView {
    @NotEmpty
    @ApiModelProperty(value = "Уникальный идентификатор", hidden = true, example = "1")
    public String id;

    @Size(max = 100)
    @NotEmpty(message = "name cannot be null")
    @ApiModelProperty(value = "doctype name")
    public String name;

    @NotNull(message = "code cannot be null")
    @Min(18)
    @Max(200)
    @ApiModelProperty(value = "doctype code", example = "03")
    public String code;

    @Override
    public String toString() {
        return "{i111d:" + id + ";name:" + name + ";code:" + code + "}";
    }
}
