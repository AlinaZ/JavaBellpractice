package com.example.demo.view.organization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;


@ApiModel(description = "organizations_list_out")
public class OrgsListOutView {

    @ApiModelProperty(value = "Уникальный идентификатор", example = "1")
    public Long id;

    @Size(max = 100)
    @ApiModelProperty(value = "Organization name", example = "Bellintegrator")
    public String name;

    @ApiModelProperty(value = "Is organization active?", example = "1")
    public boolean isActive;
}