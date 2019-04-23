package com.example.demo.view.organization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;

@ApiModel(description = "organizations")
public class OrganizationView {

    @NotEmpty
    @ApiModelProperty(value = "Уникальный идентификатор", hidden = true, example = "1")
    public String id;

    @Size(max = 100)
    @NotEmpty(message = "org name cannot be null")
    @ApiModelProperty(value = "Organization name", example = "Bellintegrator")
    public String name;

    @Size(max = 250)
    @NotEmpty(message = "org fullname cannot be null")
    @ApiModelProperty(value = "Organization fullname", example = "Bellintegrator.JSC")
    public String full_name;

    @Size(max = 12)
    @NotEmpty(message = "inn cannot be null")
    @ApiModelProperty(value = "Organization inn", example = "123456789157")
    public String inn;

    @Size(max = 9)
    @NotEmpty(message = "kpp cannot be null")
    @ApiModelProperty(value = "Organization kpp", example = "123456789")
    public String kpp;

    @Size(max = 250)
    @NotEmpty(message = "address cannot be null")
    @ApiModelProperty(value = "Organization address", example = "Свердлова, 92")
    public String address;

    @Size(max = 20)
    @NotEmpty(message = "phone cannot be null")
    @ApiModelProperty(value = "Organization phone", example = "83472777877")
    public String phone;

    @NotNull(message = "")
    @ApiModelProperty(value = "Is organization active?", example = "1")
    public boolean is_active;

    @Override
    public String toString() {
        return "{id:" + id + "\n"+";name:" + name + "\n"+";full_name:" + full_name + "\n"+ ";inn:" + inn + "\n"+";kpp:" + kpp +"\n"+";address:" + address+"\n"+";phone:" + phone+"\n"+";is_active:" + is_active+"}";
    }
}
