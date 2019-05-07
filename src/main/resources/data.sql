INSERT INTO Organization (id, version, name, full_name, inn, kpp, address, phone, is_active) VALUES (1, 0, 'АО "Белл-Интегратор"', 'Акционерное общество "Белл-Интегратор"', '7733180847','502401001','Г МОСКВА,УЛ МИТИНСКАЯ, Д 52, КОРП 1','8(495)913-22-62',1);

INSERT INTO Office (id, version, org_id, name, address, phone, is_active) VALUES (1, 0, 1, 'Офис в Уфе','ул. Свердлова, 92, 1, 3 этаж, Уфа, Респ. Башкортостан', '8(347)222–86–30',true);
INSERT INTO Office (id, version, org_id, name,address, phone, is_active) VALUES (2, 0, 1, 'Офис в Пензе','ул. Московская,27, 6 этаж, ТОЦ Новый Арбат, Пенза, Пензенская обл', '8(841)220-15-98',true);
INSERT INTO Office (id, version, org_id, name, address, phone, is_active) VALUES (3, 0, 1, 'Офис в Москве', 'ул. Московская, 84, Саратов, Саратовская обл.', '8(8452)65-94-83',true);

INSERT INTO Doc_type (id, version, name, code) VALUES (1, 0, 'Свидетельство о рождении', '03');
INSERT INTO Doc_type (id, version, name, code) VALUES (2, 0, 'Военный билет', '07');
INSERT INTO Doc_type (id, version, name, code) VALUES (3, 0, 'Временное удостоверение, выданное взамен военного билета', '08');	
INSERT INTO Doc_type (id, version, name, code) VALUES (4, 0, 'Паспорт иностранного гражданина', '10');
INSERT INTO Doc_type (id, version, name, code) VALUES (5, 0, 'Свидетельство о рассмотрении ходатайства о признании лица беженцем на территории Российской Федерации по существу', '11');
INSERT INTO Doc_type (id, version, name, code) VALUES (6, 0, 'Вид на жительство в Российской Федерации', '12');	
INSERT INTO Doc_type (id, version, name, code) VALUES (7, 0, 'Удостоверение беженца', '13');
INSERT INTO Doc_type (id, version, name, code) VALUES (8, 0, 'Разрешение на временное проживание в Российской Федерации', '15');
INSERT INTO Doc_type (id, version, name, code) VALUES (9, 0, 'Свидетельство о предоставлении временного убежища на территории Российской Федерации ', '18');	                                       
INSERT INTO Doc_type (id, version, name, code) VALUES (10, 0, 'Паспорт гражданина Российской Федерации', '21');	
INSERT INTO Doc_type (id, version, name, code) VALUES (11, 0, 'Свидетельство о рождении, выданное уполномоченным органом иностранного государства', '23');
INSERT INTO Doc_type (id, version, name, code) VALUES (12, 0, 'Удостоверение личности военнослужащего Российской Федерации', '24');
INSERT INTO Doc_type (id, version, name, code) VALUES (13, 0, 'Иные документы', '91');	  
     	
INSERT INTO Country (id, version, name, code) VALUES (1, 0, 'Российская Федерация', '643');

INSERT INTO Document (id, version, doctype_id, number,date) VALUES (1, 0, 10, '8012123457', '2012-10-11');
INSERT INTO Document (id, version, doctype_id, number,date) VALUES (2, 0, 10, '8012356421', '2012-10-11');
INSERT INTO Document (id, version, doctype_id, number,date) VALUES (3, 0, 10, '8012321987', '2012-10-11');

INSERT INTO User (id, version, first_name, last_name, middle_name, position, office_id, phone, doc_id, citizenship_id, is_identified) VALUES (1, 0, 'Иван', 'Иванов', 'Иванович', 'Ведущий Java-разработчик', 1, '89191475847', 1, 1, true);	
INSERT INTO User (id, version, first_name, last_name, middle_name, position, office_id, phone, doc_id, citizenship_id, is_identified) VALUES (2, 0, 'Шултаева', 'Екатерина', '?', 'Администратор проектов', 2, '89191475858', 2, 1, true);		
INSERT INTO User (id, version, first_name, last_name, middle_name, position, office_id, phone, doc_id, citizenship_id, is_identified) VALUES (3, 0, 'Габбасов', 'Альберт', '?', 'Руководитель центра разработки в г. Уфа', 2, '89191475882', 2, 1, true);
