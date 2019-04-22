package com.example.demo.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Офис
 */
@Entity
@Table(name = "office")

public class Office {

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
     * ID организации, которой принадлежит офис
     */
    //@Column(name = "org_id", length = 100, nullable = false)


       /**
     * Адрес офиса
     */
    @Column(name = "address", length = 250, nullable = false)
    private String address;

    /**
     * Телефон офиса
     */
    @Column(name = "phone", length = 20, nullable = false)
    private String phone;

    /**
     * Действующий или ликвидирован
     */
    @Column(name = "is_active", nullable = false)
    private boolean is_active;

    /** TODO
     * связь с таблицей огранизации
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
   // private Long org_id;
    private Organization organization;
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Organization getOrganization() {
        return organization;
    }


    private Set<Office> offices;
    /**
     * Конструктор для hibernate
     */
    public Office() {

    }

    public Office(/*Long org_id,*/ String address, String phone, boolean is_active) {
      //  this.org_id = org_id;
        this.address=address;
        this.phone=phone;
        this.is_active = is_active;  // поставить =false по умолчанию?
    }

    public Long getId() {  return id; }

   // public Long getOrg_id() {  return org_id; }

 //   public void setOrg_id(Long org_id) { this.org_id = org_id; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address=address; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone=phone; }

    public boolean getIs_active() { return is_active; }

    public void setIs_active(boolean is_active) { this.is_active=is_active; }

    public Set<Office> getOffices() {
        if (offices == null) {
            offices = new HashSet<>();
        }
        return offices;
    }

   /* public void addoffice(office office) {
        getoffices().add(office);
        house.getPersons().add(this);
    }

    public void removeHouse(House house) {
        getHouses().remove(house);
        house.getPersons().remove(this);
    }

*/




}
