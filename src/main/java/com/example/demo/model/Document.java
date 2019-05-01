package com.example.demo.model;


import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.Version;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import java.sql.Date;


/**
 * Страна гражданства сотрудника
 */
@Entity
@Table(name = "Document")
public class Document {

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
     * Номер документа
     */
    @Column(name = "number", length = 50, nullable = false)
    private String number;

    /**
     * Дата выдачи документа
     */
    @Column(name = "date", nullable = false)
    private Date date;

    /**
     * Типа документа, связь с DocType
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctype_id")
    private DocType doctype;


    /**
     * Конструктор для hibernate
     */
    public Document() {

    }

    public Document(DocType doctype, String number, Date date) {
        this.doctype = doctype;
        this.number = number;
        this.date = date;
    }


    public Long getId() {
        return id;
    }

    public DocType getDoctype() {
        return doctype;
    }

    public void setDoctype(DocType doctype) {
        this.doctype = doctype;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
