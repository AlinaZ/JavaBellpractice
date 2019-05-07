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
    @JoinColumn(name="org_id")
    private Organization organization;

   /* private Long orgIg=organization.getId(); */

    /**
     * Сотрудники офиса, связь с users
     */

    @OneToMany(mappedBy="office",cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set <User> users;

    /**
     * Конструктор для hibernate
     */
    public Office() {

    }

    public Office(Organization organization, String name,String address, String phone, boolean is_active) {
        this.organization = organization;
        this.name=name;
        this.address=address;
        this.phone=phone;
        this.is_active = is_active;
    }


    /**
     * ID getter
     * @return id
     */

    public Long getId() {  return id; }

    /**
     * address getter
     * @return address
     */
    public String getName(){return name;}
    public void setName(String name) {this.name=name;}

    public String getAddress() { return address; }

    /**
     * set address
     * @param address
     */

    public void setAddress(String address) { this.address=address; }

    /**
     * phone getter
     * @return phone
     */
    public String getPhone() { return phone; }

    /**
     * set phone
     * @param phone
     */

    public void setPhone(String phone) { this.phone=phone; }

    /**
     * is_active getter
     * @return is_active
     */

    public boolean getIs_active() { return is_active; }

    /**
     * set is_active
     * @param is_active
     */

    public void setIs_active(boolean is_active) { this.is_active=is_active; }

    /**
     * Organization getter
     * @return organization
     */
    public Organization getOrganization() {
        return organization;
    }

    /**
     * set organizaton
     * @param organization
     */

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    /**
     * Users getter
     * @return users
     */

    public Set<User> getUsers() {
        if (users == null) {
            users = new HashSet<>();
        }
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    /**
     * add user to office
     * @param user
     */

    public void addUser(User user) {
        getUsers().add(user);
        user.setOffice(this);
    }

    /**
     * remove user from office
     * @param user
     */
    public void removeUser(User user) {
        getUsers().remove(user);
        user.setOffice(null);
    }

}
