package com.example.demo.view.office;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Size;

public class OfficeListOutView {

    @ApiModelProperty(value = "Уникальный идентификатор", example = "1")
    public String id;

    @Size(max = 20)
    @ApiModelProperty(value = "Office name", example = "83472777877")
    public String name;

    @ApiModelProperty(value = "Is office active?", example = "1")
    public boolean isActive;
}
