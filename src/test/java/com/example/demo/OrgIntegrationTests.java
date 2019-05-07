package com.example.demo;

import com.example.demo.view.organization.OrganizationView;
import com.example.demo.view.organization.OrgsListInView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.demo.dao.organization.OrganizationDao;
import com.example.demo.model.Organization;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class OrgIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    private void saveTestEntity(OrganizationView view) {
        String url = "/api/organization/save";
        ResponseEntity<String> response = restTemplate.postForEntity(url, view, String.class);
    }

    private Long findTestEntity(String name) {
        OrgsListInView orgListView = new OrgsListInView();
        orgListView.name = name;
        String url = "/api/organization/list";
        ResponseEntity<String> response = restTemplate.postForEntity(url, orgListView, String.class);
        int idPointer = response.getBody().indexOf("id");
        char idC = response.getBody().charAt(idPointer + 4);
        Long id = Long.valueOf(Character.getNumericValue(idC));
        return id;
    }

    /**
     * Тест на получение организации по идентификатеру - позитивный сценарий
     */
    @Test
    public void getOrgByIdPassTest() {
        OrganizationView orgView = new OrganizationView();
        orgView.name = "Organization name";
        orgView.fullName = "Organization full name";
        orgView.address = "Уфа, Свердлова, 92";
        orgView.inn = "123456789123";
        orgView.kpp = "123456789";
        orgView.phone = "89191489168";
        orgView.isActive = true;
        saveTestEntity(orgView);
        long id = findTestEntity("Organization name");
        String url = "/api/organization/" + id;

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String expected = "{\"data\":" +
                "{\"id\":" + id + "," +
                "\"name\":\"Organization name\"," +
                "\"fullName\":\"Organization full name\"," +
                "\"inn\":\"123456789123\"," +
                "\"kpp\":\"123456789\"," +
                "\"address\":\"Уфа, Свердлова, 92\"," +
                "\"phone\":\"89191489168\"," +
                "\"isActive\":true}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест на получение организации по идентификатеру - отрицательный сценарий (нет такого Id)
     */

    @Test
    public void getOrgByIdFailTest() {
        String url = "/api/organization/10";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        String expected = "{\"error\":{\"message\":\"Нет организации с Id=10\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест Сохранение новой организации - положительный сценарий
     */
    @Test
    public void saveOrganizationPassTest() {
        OrganizationView orgView1 = new OrganizationView();
        orgView1.name = "Organization name1";
        orgView1.fullName = "Organization full name1";
        orgView1.address = "Уфа, Свердлова, 192";
        orgView1.inn = "123456789127";
        orgView1.kpp = "123456787";
        orgView1.phone = "89191489167";
        orgView1.isActive = true;
        saveTestEntity(orgView1);

        String url = "/api/organization/save";

        ResponseEntity<String> response = restTemplate.postForEntity(url, orgView1, String.class);

        String expected = "{\"data\":{\"result\":\"success\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест Сохранение новой организации - не задан обязательный параметр Название организации
     */
    @Test
    public void saveOrganizationFailTest1() {
        OrganizationView orgView2 = new OrganizationView();
        orgView2.name = null;
        orgView2.fullName = "Organization full name";
        orgView2.address = "Уфа, Свердлова, 92";
        orgView2.inn = "123456789123";
        orgView2.kpp = "123456789";
        orgView2.phone = "89191489168";
        orgView2.isActive = true;
        String url = "/api/organization/save";

        ResponseEntity<String> response = restTemplate.postForEntity(url, orgView2, String.class);

        String expected = "{\"error\":{\"message\":\"Не задано название организации\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест Сохранение новой организации - не задан обязательный параметр Полное название организации
     */
    @Test
    public void saveOrganizationFailTest2() {
        OrganizationView orgView3 = new OrganizationView();
        orgView3.name = "Org Name";
        orgView3.fullName = null;
        orgView3.address = "Уфа, Свердлова, 92";
        orgView3.inn = "123456789123";
        orgView3.kpp = "123456789";
        orgView3.phone = "89191489168";
        orgView3.isActive = true;
        String url = "/api/organization/save";

        ResponseEntity<String> response = restTemplate.postForEntity(url, orgView3, String.class);

        String expected = "{\"error\":{\"message\":\"Не задано полное название организации\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест Сохранение новой организации - не задан обязательный параметр inn организации
     */
    @Test
    public void saveOrganizationFailTest3() {
        OrganizationView orgView3 = new OrganizationView();
        orgView3.name = "Org Name";
        orgView3.fullName = "Full Org name";
        orgView3.address = "Уфа, Свердлова, 92";
        orgView3.inn = null;
        orgView3.kpp = "123456789";
        orgView3.phone = "89191489168";
        orgView3.isActive = true;
        String url = "/api/organization/save";

        ResponseEntity<String> response = restTemplate.postForEntity(url, orgView3, String.class);

        String expected = "{\"error\":{\"message\":\"Не задан inn организации\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест Сохранение новой организации - не задан обязательный параметр kpp организации
     */
    @Test
    public void saveOrganizationFailTest4() {
        OrganizationView orgView3 = new OrganizationView();
        orgView3.name = "Org Name";
        orgView3.fullName = "Full Org name";
        orgView3.address = "Уфа, Свердлова, 92";
        orgView3.inn = "123456789123";
        orgView3.kpp = null;
        orgView3.phone = "89191489168";
        orgView3.isActive = true;
        String url = "/api/organization/save";

        ResponseEntity<String> response = restTemplate.postForEntity(url, orgView3, String.class);

        String expected = "{\"error\":{\"message\":\"Не задан kpp организации\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест Сохранение новой организации - не задан обязательный параметр inn организации
     */
    @Test
    public void saveOrganizationFailTest5() {
        OrganizationView orgView3 = new OrganizationView();
        orgView3.name = "Org Name";
        orgView3.fullName = "Full Org name";
        orgView3.address = null;
        orgView3.inn = "123456789123";
        orgView3.kpp = "123456789";
        orgView3.phone = "89191489168";
        orgView3.isActive = true;
        String url = "/api/organization/save";

        ResponseEntity<String> response = restTemplate.postForEntity(url, orgView3, String.class);

        String expected = "{\"error\":{\"message\":\"Не задан адрес организации\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест на обновление организации - положительный сценарий
     */

    @Test
    public void updateOrganizationPassTest() {
        OrganizationView orgView = new OrganizationView();
        orgView.name = "Organization name";
        orgView.fullName = "Organization full name";
        orgView.address = "Уфа, Свердлова, 92";
        orgView.inn = "123456789123";
        orgView.kpp = "123456789";
        orgView.phone = "89191489168";
        orgView.isActive = true;
        saveTestEntity(orgView);
        long updId = findTestEntity("Organization name");

        OrganizationView orgUpdView = new OrganizationView();
        orgUpdView.name = "Organization1 name";
        orgUpdView.fullName = "Organization1 full name";
        orgUpdView.address = "Уфа, Свердлова, 921";
        orgUpdView.inn = "123456789125";
        orgUpdView.kpp = "123456787";
        orgUpdView.id = updId;

        String url = "/api/organization/update";

        ResponseEntity<String> response = restTemplate.postForEntity(url, orgUpdView, String.class);

        String expected = "{\"data\":{\"result\":\"success\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест на обновление организации -   не задан Id
     */

    @Test
    public void updateOrganizationFailTest() {
        OrganizationView orgUpdView = new OrganizationView();
        orgUpdView.name = "Organization1 name";
        orgUpdView.fullName = "Organization1 full name";
        orgUpdView.address = "Уфа, Свердлова, 921";
        orgUpdView.inn = "123456789125";
        orgUpdView.kpp = "123456787";

        String url = "/api/organization/update";

        ResponseEntity<String> response = restTemplate.postForEntity(url, orgUpdView, String.class);

        String expected = "{\"error\":{\"message\":\"Невозможно обновить организацию: не задан ID\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест на обновление организации -   нет обновляемой организации по Id
     */
    @Test
    public void updateOrganizationFailTest1() {
        OrganizationView orgUpdView = new OrganizationView();
        orgUpdView.name = "Organization1 name";
        orgUpdView.fullName = "Organization1 full name";
        orgUpdView.address = "Уфа, Свердлова, 921";
        orgUpdView.inn = "123456789125";
        orgUpdView.kpp = "123456787";
        orgUpdView.id = Long.valueOf(10);

        String url = "/api/organization/update";

        ResponseEntity<String> response = restTemplate.postForEntity(url, orgUpdView, String.class);

        String expected = "{\"error\":{\"message\":\"Организации, которую вы пытаетесь обновить, нет в базе, id=10\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест на вывод списка организаций по параметрам - положительный сценарий
     */

    @Test
    public void listParamOrgsPassTest() {
        OrganizationView orgView = new OrganizationView();
        orgView.name = "Organization123 name";
        orgView.fullName = "Organization full name";
        orgView.address = "Уфа, Свердлова, 92";
        orgView.inn = "111456789123";
        orgView.kpp = "123456789";
        orgView.phone = "89191489168";
        orgView.isActive = true;
        saveTestEntity(orgView);
        long id = findTestEntity("Organization123 name");

        OrgsListInView orgListView = new OrgsListInView();
        orgListView.name = "Organization123 name";
        orgListView.inn = "111456789123";
        orgListView.isActive = true;

        String url = "/api/organization/list";

        ResponseEntity<String> response = restTemplate.postForEntity(url, orgListView, String.class);
        String expected = "{\"data\":" +
                "[{\"id\":" + id + "," +
                "\"name\":\"Organization123 name\"," +
                "\"isActive\":true}]}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест на вывод списка организаций по параметрам - не задан оязательный параметр Название организации
     */

    @Test
    public void listParamOrgsFailTest() {
        OrgsListInView orgListView = new OrgsListInView();
        orgListView.name = null;
        orgListView.inn = "111456789123";
        orgListView.isActive = true;

        String url = "/api/organization/list";

        ResponseEntity<String> response = restTemplate.postForEntity(url, orgListView, String.class);
        String expected = "{\"error\":{\"message\":\"Не задано имя организации\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }


}
