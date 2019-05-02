package com.example.demo.model;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.Version;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;

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
    private String firstName;

    /**
     * Фамилия сотрудника
     */
    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    /**
     * отчетство сотрудника
     */
    @Column(name = "middle_name", length = 50, nullable = false)
    private String middleName;

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
    private Boolean isIdentified;

    /**
     * Офис, в котором работает сотрудникб связь с Office
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id")
    private Office office;

    /**
     * Гражданство сотрудника, связь с Country
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizenship_id")
    private Country country;

    /**
     * Документ, удостоверяющий личность сотрудника, связь с Document
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doc_id")
    private Document document;

    /**
     * Конструктор для hibernate
     */
    public User() {

    }

    public User(String firstName, String lastName, String middleName, String position, Office office, String phone, Document document, Country country, boolean isIdentified) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.position = position;
        this.office = office;
        this.phone = phone;
        this.document = document;
        this.country = country;
        this.isIdentified = isIdentified;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Boolean getIsIdentified() {
        return isIdentified;
    }

    public void setIsIdentified(boolean isIdentified) {
        this.isIdentified = isIdentified;
    }


}

