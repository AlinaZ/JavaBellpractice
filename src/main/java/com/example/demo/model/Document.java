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
     * Id типа документа
     */
    @Column(name = "doctype_id", nullable= false)
    private Long doctype_id;

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

    private Set<Document> documents;
    /**
     * Конструктор для hibernate
     */
    public Document() {

    }

    public Document(Long doctype_id, String doc_number,Date doc_date) {
        this.doctype_id = doctype_id;
        this.doc_number=doc_number;
        this.doc_date=doc_date;
    }

    public Long getId() {  return id; }

    public Long getDoctype_id() {  return doctype_id; }

    public void setDoctype_id(Long doctype_id) { this.doctype_id = doctype_id; }

    public String getDoc_number() { return doc_number; }

    public void setDoc_number(String doc_number) { this.doc_number=doc_number; }

    public Date getDoc_date() { return doc_date; }

    public void setDoc_date(Date doc_date) { this.doc_date=doc_date; }


    public Set<Document> getDocuments() {
        if (documents == null) {
            documents = new HashSet<>();
        }
        return documents;
    }

















}
