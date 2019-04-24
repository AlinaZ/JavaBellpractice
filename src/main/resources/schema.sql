CREATE TABLE IF NOT EXISTS Organization (
    id         INTEGER               COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
    version    INTEGER      NOT NULL COMMENT 'Служебное поле hibernate',
    name	   VARCHAR(100) NOT NULL COMMENT 'Краткое наименование организации',
    full_name  VARCHAR(250) NOT NULL COMMENT 'Полное наименование организации',
    inn        VARCHAR(12)  NOT NULL COMMENT 'ИНН организации',
    kpp        VARCHAR(9)   NOT NULL COMMENT 'кПП организации',
    address    VARCHAR(250) NOT NULL COMMENT 'адрес головного офиса организации',
    phone      VARCHAR(20)  NOT NULL COMMENT 'телефон главного офиса организации',
    is_active   BOOLEAN               COMMENT 'Действующая или ликвидирована'
);
COMMENT ON TABLE Organization IS 'Организация';

CREATE TABLE IF NOT EXISTS Office (
    id         INTEGER               COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
    version    INTEGER      NOT NULL COMMENT 'Служебное поле hibernate',
    org_id     INTEGER      NOT NULL COMMENT 'Уникальный идентификатор организации, которой принадлежит офис',
    address    VARCHAR(250) NOT NULL COMMENT 'Адрес офиса',
    phone      VARCHAR(20)  NOT NULL COMMENT 'телефон офиса',
    is_active   BOOLEAN               COMMENT 'офис действующий или ликвидирован',
);
COMMENT ON TABLE Office IS 'Офис';

CREATE TABLE IF NOT EXISTS User (
    id             INTEGER               COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
    version        INTEGER      NOT NULL COMMENT 'Служебное поле hibernate',     
    first_name     VARCHAR(50)  NOT NULL COMMENT 'Имя',
    last_name      VARCHAR(50)  NOT NULL COMMENT 'Фамилия',
    middle_name    VARCHAR(50)  NOT NULL COMMENT 'Отчество/второе имя',
    position       VARCHAR(150) NOT NULL COMMENT 'Должность',
    office_id      INTEGER      NOT NULL COMMENT 'уникальный идентификатор офиса сотрудника',
    phone          VARCHAR(11)  NOT NULL COMMENT 'телефон сотрудника',
    doc_id         INTEGER      NOT NULL COMMENT 'Уникальный идентификатор документа сотруника',
    citizenship_id INTEGER      NOT NULL COMMENT 'Уникальный идентификатор страны гражданства сотрудника',
    is_identified  BOOLEAN               COMMENT 'подтвержден ли документ'
);
COMMENT ON TABLE User IS 'Сотрудник';

CREATE TABLE IF NOT EXISTS Country (
	id           INTEGER               COMMENT 'Уникальный идентификатор страны гражданства' PRIMARY KEY AUTO_INCREMENT,
	version      INTEGER      NOT NULL COMMENT 'Служебное поле hibernate',
	name         VARCHAR(100) NOT NULL COMMENT 'Название страны',
	code         VARCHAR(3)   NOT NULL COMMENT 'Код страны по ОКСМ'
);
COMMENT ON TABLE Country IS 'Страна гражданства';

CREATE TABLE IF NOT EXISTS Doc_type (
	id       INTEGER               COMMENT 'Уникальный идентификатор вида документа' PRIMARY KEY AUTO_INCREMENT,
	version  INTEGER      NOT NULL COMMENT 'Служебное поле hibernate',
	name VARCHAR(150) NOT NULL COMMENT 'Название документа',
	code VARCHAR(2)   NOT NULL COMMENT 'Код вида документа'
);
COMMENT ON TABLE Doc_type IS 'Виды документов сотрудников';

CREATE TABLE IF NOT EXISTS Document (
	id         INTEGER              COMMENT 'Уникальный идентификатор документа, удостоверяющего личность' PRIMARY KEY AUTO_INCREMENT,
	version    INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
	doctype_id INTEGER     NOT NULL COMMENT 'Уникальный идентификатор вида документа',
	number VARCHAR(50) NOT NULL COMMENT 'Номер индивидуального документа',
    date   DATE        NOT NULL COMMENT 'Дата выдачи индивидуального документа',
);
COMMENT ON TABLE Document IS 'Документы, удостоверяющие личность сотрудника';


ALTER TABLE Office 	 ADD FOREIGN KEY (org_id)         REFERENCES Organization(id);
ALTER TABLE Document ADD FOREIGN KEY (doctype_id)     REFERENCES Doc_type(id);
ALTER TABLE User 	 ADD FOREIGN KEY (office_id)      REFERENCES Office(id);
ALTER TABLE User 	 ADD FOREIGN KEY (citizenship_id) REFERENCES Country(id);
ALTER TABLE User 	 ADD FOREIGN KEY (doc_id)         REFERENCES Document(id);


CREATE INDEX IX_Office_Org_Id        ON Office   (org_id);
CREATE INDEX IX_Document_Doctype_Id ON Document (doctype_id);
CREATE INDEX IX_User_Office_Id       ON User     (office_id);
CREATE INDEX IX_User_Citizenship_Id  ON User     (citizenship_id);
CREATE INDEX IX_User_Doc_Id          ON User     (doc_id);


ALTER TABLE Organization ALTER COLUMN is_active     SET DEFAULT false;
ALTER TABLE Office       ALTER COLUMN is_active     SET DEFAULT false;
ALTER TABLE User         ALTER COLUMN is_identified SET DEFAULT false;

	
	

	
