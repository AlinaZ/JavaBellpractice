package com.example.demo.model;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;


/**
 * Документ сотрудника
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
    private LocalDate date;

    /**
     * Тип документа, связь с DocType
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctype_id")
    private DocType docType;


    /**
     * Конструктор для hibernate
     */
    public Document() {

    }

    public Document(DocType docType, String number, LocalDate date) {
        this.docType = docType;
        this.number = number;
        this.date = date;
    }


    public Long getId() {
        return id;
    }

    public DocType getDocType() {
        return docType;
    }

    public void setDocType(DocType docType) {
        this.docType = docType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
