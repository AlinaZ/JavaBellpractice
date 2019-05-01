package com.example.demo.model;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.Version;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
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
     * Название офиса
     */

    @Column(name = "name", length = 50, nullable = false)
    private String name;

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

    /**
     * Организация, которой принадлжеит офис, связь с organization
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    private Organization organization;

    /**
     * Сотрудники офиса, связь с users
     */

    @OneToMany(mappedBy = "office", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<User> users;

    /**
     * Конструктор для hibernate
     */
    public Office() {

    }

    public Office(Organization organization, String name, String address, String phone, boolean is_active) {
        this.organization = organization;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.is_active = is_active;
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

    public boolean getIsActive() {
        return is_active;
    }

    public void setIsActive(boolean is_active) {
        this.is_active = is_active;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Set<User> getUsers() {
        if (users == null) {
            users = new HashSet<>();
        }
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        getUsers().add(user);
        user.setOffice(this);
    }

    public void removeUser(User user) {
        getUsers().remove(user);
        user.setOffice(null);
    }

}
