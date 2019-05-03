package com.example.demo.view.office;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;

@ApiModel(description = "offices")
public class OfficeUpdView {

    @ApiModelProperty(value = "Уникальный идентификатор", hidden = true, example = "1")
    public Long id;

    @Size(max = 20)
    @ApiModelProperty(value = "Office name", example = "83472777877")
    public String name;

    @Size(max = 250)
    @ApiModelProperty(value = "Office address", example = "Свердлова, 92")
    public String address;

    @Size(max = 20)
    @ApiModelProperty(value = "Office phone", example = "83472777877")
    public String phone;

    @ApiModelProperty(value = "Is office active?", example = "1")
    public boolean isActive;
}
