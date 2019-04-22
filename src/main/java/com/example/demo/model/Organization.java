package com.example.demo.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.List;


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
    private String full_name;

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
    private boolean is_active;

    /** TODO
     * связь с таблицей офисы
     * */
   // @Access(AccessType.PROPERTY)
    @OneToMany(
            mappedBy="organization",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
 //   @ElementCollection
    private List<Office> offices;

    public List<Office> getOffices() {
        return offices;
    }

    private Set<Organization> organizations;
    /**
     * Конструктор для hibernate
     */
    public Organization() {

    }

    public Organization(String name, String full_name, String inn, String kpp, String address, String phone, boolean is_active) {
        this.name = name;
        this.full_name=full_name;
        this.inn=inn;
        this.kpp=kpp;
        this.address=address;
        this.phone=phone;
        this.is_active = is_active;  // поставить =false по умолчанию?
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

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name() { this.full_name=full_name; }

    public String getInn() {
        return inn;
    }

    public void setInn() { this.inn=inn; }

    public String getKpp() {
        return kpp;
    }

    public void setKpp() { this.kpp=kpp; }

    public String getAddress() {
        return address;
    }

    public void setAddress() { this.address=address; }

    public String getPhone() {
        return phone;
    }

    public void setPhone() { this.phone=phone; }

    public boolean getIs_active() { return is_active; }

    public void setIs_active() { this.is_active=is_active; }

    public Set<Organization> getOrganizations() {
        if (organizations == null) {
            organizations = new HashSet<>();
        }
        return organizations;
    }

   /* public void addOrganization(Organization organization) {
        getOrganizations().add(organization);
        house.getPersons().add(this);
    }

    public void removeHouse(House house) {
        getHouses().remove(house);
        house.getPersons().remove(this);
    }

*/




}
