package com.example.demo.view.organization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;


@ApiModel(description = "organizations")
public class OrgSaveView {

    @Size(max = 100)
    @ApiModelProperty(value = "Organization name", example = "Bellintegrator")
    public String name;

    @Size(max = 250)
    @ApiModelProperty(value = "Organization fullname", example = "Bellintegrator.JSC")
    public String fullName;

    @Size(max = 12)
    @ApiModelProperty(value = "Organization inn", example = "123456789157")
    public String inn;

    @Size(max = 9)
    @ApiModelProperty(value = "Organization kpp", example = "123456789")
    public String kpp;

    @Size(max = 250)
    @NotEmpty(message = "address cannot be null")
    @ApiModelProperty(value = "Organization address", example = "Свердлова, 92")
    public String address;

    @Size(max = 20)
    @ApiModelProperty(value = "Organization phone", example = "83472777877")
    public String phone;

    @NotNull(message = "")
    @ApiModelProperty(value = "Is organization active?", example = "1")
    public boolean isActive;

    @Override
    public String toString() {
        return "{name:" + name + "\n"+";full_name:" + fullName + "\n"+ ";inn:" + inn + "\n"+";kpp:" + kpp +"\n"+";address:" + address+"\n"+";phone:" + phone+"\n"+";is_active:" + isActive+"}";
    }
}

