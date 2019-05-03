package com.example.demo;

import com.example.demo.DemoApplication;
import com.example.demo.dao.office.OfficeDao;
import com.example.demo.model.Office;
import com.example.demo.service.office.OfficeServiceImpl;
import com.example.demo.service.organization.OrganizationServiceImpl;
import com.example.demo.view.office.OfficeListInView;
import com.example.demo.view.office.OfficeView;
import com.example.demo.view.organization.OrganizationView;
import com.example.demo.view.organization.OrgsListInView;
import org.junit.Before;
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
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = DemoApplication.class)

public class OfficeIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private OfficeDao dao;

    @Autowired
    private OfficeServiceImpl service;

    private HttpHeaders headers;

    @Before
    public void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        //repository.deleteAll();
    }

    /**
     * Тест на получение офиса по идентификатеру - позитивный сценарий
     */
    @Test
    public void getOfficeByIdPassTest() {
        OfficeView officeView = new OfficeView();
        officeView.name = "Office name";
        officeView.orgId = Long.valueOf(1);
        officeView.address = "Уфа, Свердлова, 92";
        officeView.phone = "89191489168";
        officeView.isActive = true;
        service.add(officeView);

        long id = dao.loadByName("Office name").getId();
        String url = "/api/office/" + id;

        HttpEntity entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        String expected = "{\"data\":" +
                "{\"id\":" + id + "," +
                "\"name\":\"Office name\"," +
                "\"orgId\":1,"  +
                "\"address\":\"Уфа, Свердлова, 92\"," +
                "\"phone\":\"89191489168\"," +
                "\"isActive\":true}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест на получение офиса по идентификатеру - отрицательный сценарий (нет такого Id)
     */

    @Test
    public void getOfficeByIdFailTest() {
        String url = "/api/office/10";
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        String expected = "{\"error\":{\"message\":\"Нет офиса с Id=10\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест Сохранение нового офиса - положительный сценарий
     */
    @Test
    public void saveOfficePassTest() {
        OfficeView officeView = new OfficeView();
        officeView.name = "Office name";
        officeView.orgId = Long.valueOf(1);
        officeView.address = "Уфа, Свердлова, 92";
        officeView.phone = "89191489168";
        officeView.isActive = true;
        service.add(officeView);

        HttpEntity entity = new HttpEntity<>(officeView, headers);
        String url = "/api/office/save";

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        String expected = "{\"data\":{\"result\":\"success\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест Сохранение нового офиса - не задан обязательный параметр Id организации
     */
    @Test
    public void saveOfficeFailTest() {
        OfficeView officeView = new OfficeView();
        officeView.name = "Office name";
        officeView.orgId = null;
        officeView.address = "Уфа, Свердлова, 92";
        officeView.phone = "89191489168";
        officeView.isActive = true;

        HttpEntity entity = new HttpEntity<>(officeView, headers);
        String url = "/api/office/save";

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        String expected = "{\"error\":{\"message\":\"Не задан идентификатор организации, которой принадлежит офис\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));

    }

    /**
     * Тест на обновление офиса - положительный сценарий
     */

    @Test
    public void updateOfficePassTest() {
        OfficeView officeView = new OfficeView();
        officeView.name = "Office name";
        officeView.orgId = Long.valueOf(1);
        officeView.address = "Уфа, Свердлова, 92";
        officeView.phone = "89191489168";
        officeView.isActive = true;
        service.add(officeView);
        long updId = dao.loadByName("Office name").getId();

        OfficeView officeView1 = new OfficeView();
        officeView1.id=updId;
        officeView1.name = "New Office name";
        officeView1.address = "Ufa, Свердлова, 92";

        String url = "/api/office/update";
        HttpEntity entity = new HttpEntity<>(officeView1, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        String expected = "{\"data\":{\"result\":\"success\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
        Office updatedOffice = dao.loadById(updId);
        assertEquals(updatedOffice.getName(), officeView1.name);
        assertEquals(updatedOffice.getOrganization().getId(), officeView.orgId);
        assertEquals(updatedOffice.getAddress(), officeView1.address);
        assertEquals(updatedOffice.getPhone(), officeView.phone);
        assertEquals(updatedOffice.getIsActive(), officeView.isActive);
    }

    /**
     * Тест на обновление офиса -  не задан обязательный параметр Id
     */

    @Test
    public void updateOfficeFailTest1() {
        OfficeView officeView1 = new OfficeView();
        officeView1.id=null;
        officeView1.name = "New Office name";
        officeView1.address = "Ufa, Свердлова, 92";

        String url = "/api/office/update";
        HttpEntity entity = new HttpEntity<>(officeView1, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        String expected = "{\"error\":{\"message\":\"Невозможно обновить офис: не задан ID\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест на обновление офиса -  не задан обязательный параметр Название офиса
     */

    @Test
    public void updateOfficeFailTest2() {
        OfficeView officeView1 = new OfficeView();
        officeView1.id=Long.valueOf(1);
        officeView1.name = null;
        officeView1.address = "Ufa, Свердлова, 92";

        String url = "/api/office/update";
        HttpEntity entity = new HttpEntity<>(officeView1, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        String expected = "{\"error\":{\"message\":\"Не задано название офиса\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест на обновление офиса -  не задан обязательный параметр адрес офиса
     */

    @Test
    public void updateOfficeFailTest3() {
        OfficeView officeView1 = new OfficeView();
        officeView1.id=Long.valueOf(1);
        officeView1.name = "Office name";
        officeView1.address = null;

        String url = "/api/office/update";
        HttpEntity entity = new HttpEntity<>(officeView1, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        String expected = "{\"error\":{\"message\":\"Не задан адрес офиса\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест на обновление офиса -  нет обновляемого офиса
     */

    @Test
    public void updateOfficeFailTest5() {
        OfficeView officeView1 = new OfficeView();
        officeView1.id=Long.valueOf(10);
        officeView1.name = "Office name";
        officeView1.address = "Ufa, Свердлова, 92";

        String url = "/api/office/update";
        HttpEntity entity = new HttpEntity<>(officeView1, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        String expected = "{\"error\":{\"message\":\"Офиса, который вы пытаетесь обновить, нет в базе, id=10\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест на вывод списка офисов по параметрам - положительный сценарий
     */

    @Test
    public void listParamOfficesPassTest() {
        OfficeView officeView = new OfficeView();
        officeView.name = "Office123 name";
        officeView.orgId = Long.valueOf(1);
        officeView.address = "Уфа, Свердлова, 921";
        officeView.phone = "79191489168";
        officeView.isActive = true;
        service.add(officeView);
        long id = dao.loadByName("Office123 name").getId();

        OfficeListInView officeListView = new OfficeListInView();
        officeListView.orgId=Long.valueOf(1);
        officeListView.name = "Office123 name";
        officeListView.phone = "79191489168";
        officeListView.isActive = true;

        String url = "/api/office/list";

        HttpEntity entity = new HttpEntity<>(officeListView, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        String expected = "{\"data\":" +
                "[{\"id\":" + id + "," +
                "\"name\":\"Office123 name\"," +
                "\"isActive\":true}]}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест на вывод списка офосов по параметрам - не задан оязательный параметр Id организации
     */

    @Test
    public void listParamOfficeFailTest() {
        OfficeListInView officeListView = new OfficeListInView();
        officeListView.orgId=null;
        officeListView.name = "Office123 name";
        officeListView.phone = "79191489168";
        officeListView.isActive = true;

        String url = "/api/office/list";

        HttpEntity entity = new HttpEntity<>(officeListView, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        String expected = "{\"error\":{\"message\":\"Не задано Id организации,котрой принадлежат офисы\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

}