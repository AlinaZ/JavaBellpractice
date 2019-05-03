package com.example.demo.view.office;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;

public class OfficeListInView {

    @Size(max = 20)
    @ApiModelProperty(value = "Office name", example = "83472777877")
    public String name;

    @ApiModelProperty
    public Long orgId;

    @Size(max = 20)
    @ApiModelProperty(value = "Office phone", example = "83472777877")
    public String phone;

    @ApiModelProperty(value = "Is office active?", example = "1")
    public boolean isActive;
}
