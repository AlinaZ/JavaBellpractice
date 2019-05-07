package com.example.demo.view.country;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(description = "citizenship countries")
public class CountryView {

    @NotEmpty
    @ApiModelProperty(value = "Уникальный идентификатор", hidden = true, example = "1")
    public String id;

    @Size(max = 100)
    @NotEmpty(message = "countryname cannot be null")
    @ApiModelProperty(value = "Country name", example = "Russia")
    public String name;

    @NotNull(message = "code cannot be null")
    @Min(18)
    @Max(200)
    @ApiModelProperty(value = "Country code", example = "643")
    public String code;

    @Override
    public String toString() {
        return "{i111d:" + id + ";name:" + name + ";code:" + code + "}";
    }
}
