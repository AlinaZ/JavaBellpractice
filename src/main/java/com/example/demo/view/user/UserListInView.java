package com.example.demo.view.user;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Size;

public class UserListInView {

    @ApiModelProperty(value = "user's office", example = "1")
    public Long officeId;

    @Size(max = 50)
    @ApiModelProperty(value = "User first name", example = "Bellintegrator")
    public String firstName;

    @Size(max = 50)
    @ApiModelProperty(value = "User lastname", example = "Bellintegrator")
    public String lastName;

    @Size(max = 50)
    @ApiModelProperty(value = "User middle name", example = "Bellintegrator")
    public String middleName;

    @Size(max = 150)
    @ApiModelProperty(value = "User position", example = "backend developer")
    public String position;

    @ApiModelProperty(value = "user's doctype code", example = "1")
    public Long docCode;

    @ApiModelProperty(value = "user's citizenship code", example = "1")
    public Long citizenshipCode;
}
