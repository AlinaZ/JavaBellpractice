package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.HashSet;
import java.util.Set;

/**
 * Страна гражданства сотрудника
 */
@Entity
@Table(name = "Country")

public class Country {

    /**
     * Первичный ключ
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Название страны
     */
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    /**
            * Телефон офиса
     */
    @Column(name = "code", length = 3, nullable = false)
    private String code;

       /** TODO
     * связь с таблицей user
     * */

    private Set<Country> countries;
    /**
     * Конструктор для hibernate
     */
    public Country() {

    }

    public Country(String name, String code) {
        this.name = name;
        this.code=code;
    }

    public Long getId() {  return id; }

    public String getName() {  return name; }

    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }

    public void setCode(String code) { this.code=code; }


    public Set<Country> getCountries() {
        if (countries == null) {
            countries = new HashSet<>();
        }
        return countries;
    }

   /* public void addcountry(country country) {
        getcountrys().add(country);
        house.getPersons().add(this);
    }

    public void removeHouse(House house) {
        getHouses().remove(house);
        house.getPersons().remove(this);
    }

*/




}
