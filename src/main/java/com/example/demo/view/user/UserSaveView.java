package com.example.demo.view.user;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDate;

public class UserSaveView extends UserView {

    @ApiModelProperty(value = "user's doc code", example = "1")
    public String docCode;

}
