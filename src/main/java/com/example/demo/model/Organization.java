package com.example.demo.model;

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

    @OneToMany(mappedBy="organization",cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set <Office> offices;

    /**
     * Конструктор для hibernate
     */
    public Organization() {

    }

    public Organization(String name, String fullName, String inn, String kpp, String address, String phone, boolean isActive) {
        this.name = name;
        this.fullName=fullName;
        this.inn=inn;
        this.kpp=kpp;
        this.address=address;
        this.phone=phone;
        this.isActive = isActive;
    }

    /**
     * get organization Id
     * @return id
     */

    public Long getId() {
        return id;
    }

    /**
     * get organization name
     * @return name
     */

    public String getName() {
        return name;
    }

    /**
     * set organization name
     * @param name
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * get organization fullName
     * @return fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * set organization fullName
     * @param fullName
     */
    public void setFull_name(String fullName) { this.fullName=fullName; }

    /**
     * get organization inn
     * @return
     */
    public String getInn() {
        return inn;
    }

    /**
     * set organization inn
     * @param inn
     */

    public void setInn(String inn) { this.inn=inn; }

    /**
     * get organization kpp
     * @return
     */

    public String getKpp() {
        return kpp;
    }

    /**
     * set organization kpp
     * @param kpp
     */

    public void setKpp(String kpp) { this.kpp=kpp; }

    /**
     * get organization address
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * set organization address
     * @param address
     */

    public void setAddress(String address) { this.address=address; }

    /**
     * get org phone
     * @return
     */

    public String getPhone() {
        return phone;
    }

    /**
     * set phone
     * @param phone
     */

    public void setPhone(String phone) { this.phone=phone; }

    /**
     * get if is organization active
     * @return isActive
     */

    public boolean getIs_active() { return isActive; }

    /**
     * set is organizaiotn active
     * @param isActive
     */

    public void setIs_active(boolean isActive) { this.isActive=isActive; }

    /**
     * get organization's offices
     * @return
     */

    public Set<Office> getOffices() {
        if (offices == null) {
            offices = new HashSet<>();
        }
        return offices;
    }

    /**
     * set organization's offices
     * @param offices
     */

    public void setOffices(Set<Office> offices) {
        this.offices = offices;
    }

    /**
     * add office
     * @param office
     */
    public void addOffice(Office office) {
        getOffices().add(office);
        office.setOrganization(this);
    }

    /**remove office
     * @param office
     */
    public void removeOffice(Office office) {
        getOffices().remove(office);
        office.setOrganization(null);
    }

}
