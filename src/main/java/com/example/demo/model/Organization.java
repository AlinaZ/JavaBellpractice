package com.example.demo.model;

import org.hibernate.annotations.Type;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.Version;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.util.HashSet;
import java.util.Set;

/**
 * Организация
 */
@Entity
@Table(name = "Organization")
public class Organization {

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
     * Краткое нименование организации
     */
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    /**
     * Полное нименование организации
     */
    @Column(name = "full_name", length = 250, nullable = false)
    private String fullName;

    /**
     * ИНН организации
     */
    @Column(name = "inn", length = 12, nullable = false)
    private String inn;

    /**
     * КПП организации
     */
    @Column(name = "kpp", length = 9, nullable = false)
    private String kpp;

    /**
     * Адрес головного офиса организации
     */
    @Column(name = "address", length = 250, nullable = false)
    private String address;

    /**
     * Адрес головного офиса организации
     */
    @Column(name = "phone", length = 20, nullable = false)
    private String phone;

    /**
     * Действующая или ликвидирована
     */
    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    /**
     * Офисы организации
     */

  /*  @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Office> offices;*/

    /**
     * Конструктор для hibernate
     */
    public Organization() {

    }

    public Organization(String name, String fullName, String inn, String kpp, String address, String phone, boolean isActive) {
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFull_name(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean getIs_active() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

  /*  public Set<Office> getOffices() {
        if (offices == null) {
            offices = new HashSet<>();
        }
        return offices;
    }*/

   /* public void setOffices(Set<Office> offices) {
        this.offices = offices;
    }*/

  /*  public void addOffice(Office office) {
        getOffices().add(office);
        office.setOrganization(this);
    }
*//*
    public void removeOffice(Office office) {
        getOffices().remove(office);
        office.setOrganization(null);
    }*/

}
