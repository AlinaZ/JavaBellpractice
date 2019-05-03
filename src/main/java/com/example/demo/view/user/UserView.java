package com.example.demo.view.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.tomcat.jni.Local;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDate;

@ApiModel(description = "users")
public class UserView {

    @ApiModelProperty(value = "Уникальный идентификатор", hidden = true, example = "1")
    public Long id;

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

    @Size(max = 20)
    @ApiModelProperty(value = "User phone", example = "83472777877")
    public String phone;

    @ApiModelProperty(value = "user's doc name", example = "1")
    public String docName;

    @ApiModelProperty(value = "user's doc name", example = "1")
    public String docNumber;

    @ApiModelProperty(value = "user's doc name", example = "1")
    public Date docDate;

    @ApiModelProperty(value = "user's citizenship", example = "1")
    public String citizenshipCode;

    @ApiModelProperty(value = "Is user's document identified?", example = "true")
    public boolean isIdentified;
}
