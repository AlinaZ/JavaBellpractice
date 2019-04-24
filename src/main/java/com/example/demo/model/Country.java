package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

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
     *  Код страны
     */
    @Column(name = "code", length = 3, nullable = false)
    private String code;

       /** TODO
     * связь с таблицей user
     * */

    /**
     * Конструктор для hibernate
     */
    public Country() {

    }

    public Country(String name, String code) {
        this.name = name;
        this.code=code;
    }

    /**
     * get country id
     * @return id
     */

    public Long getId() {  return id; }

    /**
     * get country name
     * @return name
     */

    public String getName() {  return name; }

    /**
     * set country name
     * @param name
     */

    public void setName(String name) { this.name = name; }

    /**
     * get country code
     * @return
     */

    public String getCode() { return code; }

    /**
     * set country code
     * @param code
     */

    public void setCode(String code) { this.code=code; }

}
