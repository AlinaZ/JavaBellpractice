package com.example.demo.view.user;

import com.example.demo.model.Country;
import com.example.demo.model.Document;
import com.example.demo.model.Office;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(description = "users")
public class UserView {

    @NotEmpty
    @ApiModelProperty(value = "Уникальный идентификатор", hidden = true, example = "1")
    public String id;

    @Size(max = 50)
    @NotEmpty(message = "user first name cannot be null")
    @ApiModelProperty(value = "User first name", example = "Bellintegrator")
    public String firstName;

    @Size(max = 50)
    @NotEmpty(message = "user lastname cannot be null")
    @ApiModelProperty(value = "User lastname", example = "Bellintegrator")
    public String lastName;

    @Size(max = 50)
    @NotEmpty(message = "user middle name cannot be null")
    @ApiModelProperty(value = "User middle name", example = "Bellintegrator")
    public String middleName;

    @Size(max = 150)
    @NotEmpty(message = "position cannot be null")
    @ApiModelProperty(value = "User position", example = "backend developer")
    public String position;

    @ApiModelProperty(value="user's office",example = "1")
    public Long officeId;

    @Size(max = 20)
    @NotEmpty(message = "phone cannot be null")
    @ApiModelProperty(value = "User phone", example = "83472777877")
    public String phone;

    @ApiModelProperty(value="user's doc",example = "1")
    public Long docId;

    @ApiModelProperty(value="user's citizenship",example = "1")
    public Long citizenshipId;

    @NotNull(message = "is identified")
    @ApiModelProperty(value = "Is user's document identified?", example = "true")
    public boolean isIdentified;

   /* @Override
    public String toString() {
        return "{id:" + id + "\n"+";first_name:" + firstName + "\n"+";last_name:" + lastName + "\n"+ ";middle_name:" + middleName + "\n"+";position:" + position +"\n"+";office_id:" + office_id+"\n"+";phone:" + phone+"\n"+";doc_id:"+doc_id+";citizenship_id:"+citizenship_id+";is_identified:" + isIdentified+"}";
    }*/
}
