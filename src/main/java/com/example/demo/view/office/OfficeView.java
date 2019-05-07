package com.example.demo.view.office;

import com.example.demo.model.Office;
import com.example.demo.model.Organization;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;
import java.util.Set;

@ApiModel(description = "offices")
public class OfficeView {

    @NotEmpty
    @ApiModelProperty(value = "Уникальный идентификатор", hidden = true, example = "1")
    public String id;

    @Size(max = 20)
    @NotEmpty(message = "name cannot be null")
    @ApiModelProperty(value = "Office name", example = "83472777877")
    public String name;

   /* @ApiModelProperty(value = "Office organization",hidden = true)
    private Organization organization;
    */

    @ApiModelProperty
    public String orgId;
    //orgId=organization.getName();

    @Size(max = 250)
    @NotEmpty(message = "address cannot be null")
    @ApiModelProperty(value = "Office address", example = "Свердлова, 92")
    public String address;

    @Size(max = 20)
    @NotEmpty(message = "phone cannot be null")
    @ApiModelProperty(value = "Office phone", example = "83472777877")
    public String phone;

    @NotNull(message = "")
    @ApiModelProperty(value = "Is office active?", example = "1")
    public boolean isActive;


/*   @Override
    public String toString() {
        return "{i1d:" + id + "\n"+";name:" + name + "\n"+";full_name:" + fullName + "\n"+ ";inn:" + inn + "\n"+";kpp:" + kpp +"\n"+";address:" + address+"\n"+";phone:" + phone+"\n"+";is_active:" + isActive+"}";
    }
    */
   
}
