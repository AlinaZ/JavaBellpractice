package com.example.demo.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Сотрудник
 */
@Entity
@Table(name = "User")

public class User {

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
     * имя сотрудника
     */
    @Column(name = "first_name", length = 50, nullable = false)
    private String first_name;

    /**
     * Фамилия сотрудника
     */
    @Column(name = "last_name", length = 50, nullable = false)
    private String last_name;

    /**
     * отчетство сотрудника
     */
    @Column(name = "middle_name", length = 50, nullable = false)
    private String middle_name;

    /**
     * Должность
     */
    @Column(name = "position", length = 150, nullable = false)
    private String position;

    /**
     * Телефон сотрудника
     */
    @Column(name = "phone", length = 11, nullable = false)
    private String phone;

    /**
     * Подтвержден ли документ
     */
    @Column(name = "is_identified", nullable = false)
    private boolean is_identified;

    /** TODO
     * связь с таблицами документы, офис, страны
     * */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="office_id")
    private Office office;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="citizenship_id")
    private Country country;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="doc_id")
    private Document document;

    /**
     * Конструктор для hibernate
     */
    public User() {

    }

    public User(String first_name, String last_name,String middle_name, String position, Office office, String phone, Document document, Country country, boolean is_identified) {
        this.first_name=first_name;
        this.last_name=last_name;
        this.middle_name=middle_name;
        this.position=position;
        this.office = office;
        this.phone=phone;
        this.document=document;
        this.country=country;
        this.is_identified = is_identified;
    }

    public Long getId() {  return id; }

    public String getFirst_name() { return first_name;    }

    public void setFirst_name(String first_name) {  this.first_name = first_name; }

    public String getLast_name() { return last_name; }

    public void setLast_name(String last_name) { this.last_name=last_name; }

    public String getMiddle_name() { return middle_name; }

    public void setMiddle_name(String middle_name) { this.middle_name=middle_name; }

    public Office getOffice() {  return office; }

    public void setOffice(Office office) { this.office = office; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone=phone; }

    public Country getCountry() { return country; }

    public void setCountry(Country country) {this.country=country;}

    public Document getDocument() { return document; }

    public void setDocument(Document document) {this.document=document;}

    public boolean getIs_identified() { return is_identified; }

    public void setIs_identified(boolean is_identified) { this.is_identified=is_identified; }


}

