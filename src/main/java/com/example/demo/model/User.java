package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;
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
     * ID офиса, за которым закреплен сотрудник
     */
    @Column(name = "office_id", nullable = false)
    private Long office_id;

    /**
     * Телефон сотрудника
     */
    @Column(name = "phone", length = 11, nullable = false)
    private String phone;

    /**
     * ID документа, удостоверяющего личность сотрудника
     */
    @Column(name = "doc_id", nullable = false)
    private Long doc_id;

    /**
     * ID страны гражданства
     */
    @Column(name = "citizenship_id", nullable = false)
    private Long citizenship_id;

    /**
     * Подтвержден ли документ
     */
    @Column(name = "is_identified", nullable = false)
    private boolean is_identified;

    /** TODO
     * связь с таблицами документы, офис, страны
     * */

    private Set<User> users;
    /**
     * Конструктор для hibernate
     */
    public User() {

    }

    public User(String first_name, String last_name,String middle_name, String position, Long office_id, String phone, Long doc_id, Long citizenship_id, boolean is_identified) {
        this.first_name=first_name;
        this.last_name=last_name;
        this.middle_name=middle_name;
        this.position=position;
        this.office_id = office_id;
        this.phone=phone;
        this.doc_id=doc_id;
        this.citizenship_id=citizenship_id;
        this.is_identified = is_identified;  // поставить =false по умолчанию?
    }

    public Long getId() {  return id; }

    public String getFirst_name() { return first_name;    }

    public void setFirst_name(String first_name) {  this.first_name = first_name; }

    public String getLast_name() { return last_name; }

    public void setLast_name(String last_name) { this.last_name=last_name; }

    public String getMiddle_name() { return middle_name; }

    public void setMiddle_name(String middle_name) { this.middle_name=middle_name; }

    public Long getOffice_id() {  return office_id; }

    public void setOffice_id(Long office_id) { this.office_id = office_id; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone=phone; }

    public boolean getIs_identified() { return is_identified; }

    public void setIs_identified(boolean is_identified) { this.is_identified=is_identified; }

    public Set<User> getUsers() {
        if (users == null) {
            users = new HashSet<>();
        }
        return users;
    }

   /* public void adduser(user user) {
        getusers().add(user);
        house.getPersons().add(this);
    }

    public void removeHouse(House house) {
        getHouses().remove(house);
        house.getPersons().remove(this);
    }

*/




}

