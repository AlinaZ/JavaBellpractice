package com.example.demo.view.organization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;


@ApiModel(description = "organizations_list_params")
public class OrgsListInView {

    public String name;

    public String inn;

    public boolean isActive;
}
