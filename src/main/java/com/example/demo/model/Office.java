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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="org_id")
    private Organization organization;

    @OneToMany(mappedBy="office",cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set <User> users;

    /**
     * Конструктор для hibernate
     */
    public Office() {

    }

    public Office(Organization organization, String address, String phone, boolean is_active) {
        this.organization = organization;
        this.address=address;
        this.phone=phone;
        this.is_active = is_active;
    }

    public Long getId() {  return id; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address=address; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone=phone; }

    public boolean getIs_active() { return is_active; }

    public void setIs_active(boolean is_active) { this.is_active=is_active; }

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
