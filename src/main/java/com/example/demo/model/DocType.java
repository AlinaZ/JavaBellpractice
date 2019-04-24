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
@Table(name = "Doc_type")

public class DocType {

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

    /**
     * Конструктор для hibernate
     */
    public DocType() {

    }

    public DocType(String name, String code) {
        this.name = name;
        this.code=code;
    }

    /**
     * get doctype id
     * @return id
     */
    public Long getId() {  return id; }

    /**
     * get doctype name
     * @return name
     */

    public String getName() {  return name; }

    /**
     * set doctype name
     * @param name
     */

    public void setName(String name) { this.name = name; }

    /**
     * get doctype code
     * @return code
     */

    public String getCode() { return code; }

    /**
     * set doctype code
     * @param code
     */

    public void setCode(String code) { this.code=code; }

}
