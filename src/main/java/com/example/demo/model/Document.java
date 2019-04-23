package com.example.demo.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.sql.Date;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;

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
    @Column(name = "doc_number", length = 50, nullable = false)
    private String doc_number;

    /**
     * Дата выдачи документа
     */
    @Column(name = "doc_date", nullable = false)
    private Date doc_date;

    /** TODO
     * связь с таблицей doctype
     * */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="doctype_id")
    private DocType doctype;


    /**
     * Конструктор для hibernate
     */
    public Document() {

    }

    public Document(DocType doctype, String doc_number,Date doc_date) {
        this.doctype = doctype;
        this.doc_number=doc_number;
        this.doc_date=doc_date;
    }

    public Long getId() {  return id; }

    public DocType getDoctype() {  return doctype; }

    public void setDoctype_id(DocType doctype) { this.doctype = doctype; }

    public String getDoc_number() { return doc_number; }

    public void setDoc_number(String doc_number) { this.doc_number=doc_number; }

    public Date getDoc_date() { return doc_date; }

    public void setDoc_date(Date doc_date) { this.doc_date=doc_date; }




















}
