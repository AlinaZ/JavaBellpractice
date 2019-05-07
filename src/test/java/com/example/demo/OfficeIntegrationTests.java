package com.example.demo;

import com.example.demo.view.office.OfficeListInView;
import com.example.demo.view.office.OfficeView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class OfficeIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    private void saveTestEntity(OfficeView view) {
        String url = "/api/office/save";
        ResponseEntity<String> response = restTemplate.postForEntity(url, view, String.class);
    }

    private Long findTestEntity(String name, Long orgId) {
        OfficeListInView offListView = new OfficeListInView();
        offListView.name = name;
        offListView.orgId = orgId;

        String url = "/api/office/list";
        ResponseEntity<String> response = restTemplate.postForEntity(url, offListView, String.class);
        int idPointer = response.getBody().indexOf("id");
        char idC = response.getBody().charAt(idPointer + 4);
        Long id = Long.valueOf(Character.getNumericValue(idC));
        return id;
    }

    /**
     * Тест на получение офиса по идентификатеру - позитивный сценарий
     */
    @Test
    public void getOfficeByIdPassTest() {
        OfficeView officeView = new OfficeView();
        officeView.name = "Office7 name";
        officeView.orgId = Long.valueOf(1);
        officeView.address = "Уфа, Свердлова, 92";
        officeView.phone = "89191489168";
        officeView.isActive = true;
        saveTestEntity(officeView);

        long id = findTestEntity(officeView.name, officeView.orgId);
        String url = "/api/office/" + id;

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String expected = "{\"data\":" +
                "{\"id\":" + id + "," +
                "\"name\":\"Office7 name\"," +
                "\"orgId\":1," +
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

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

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

        String url = "/api/office/save";

        ResponseEntity<String> response = restTemplate.postForEntity(url, officeView, String.class);

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

        String url = "/api/office/save";

        ResponseEntity<String> response = restTemplate.postForEntity(url, officeView, String.class);

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
        officeView.name = "Office name7";
        officeView.orgId = Long.valueOf(1);
        officeView.address = "Уфа, Свердлова, 92";
        officeView.phone = "89191489168";
        officeView.isActive = true;
        saveTestEntity(officeView);
        long updId = findTestEntity(officeView.name, officeView.orgId);

        OfficeView officeView1 = new OfficeView();
        officeView1.id = updId;
        officeView1.name = "New Office name";
        officeView1.address = "Ufa, Свердлова, 92";

        String url = "/api/office/update";

        ResponseEntity<String> response = restTemplate.postForEntity(url, officeView1, String.class);

        String expected = "{\"data\":{\"result\":\"success\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест на обновление офиса -  не задан обязательный параметр Id
     */

    @Test
    public void updateOfficeFailTest1() {
        OfficeView officeView1 = new OfficeView();
        officeView1.id = null;
        officeView1.name = "New Office name";
        officeView1.address = "Ufa, Свердлова, 92";

        String url = "/api/office/update";

        ResponseEntity<String> response = restTemplate.postForEntity(url, officeView1, String.class);

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
        officeView1.id = Long.valueOf(1);
        officeView1.name = null;
        officeView1.address = "Ufa, Свердлова, 92";

        String url = "/api/office/update";

        ResponseEntity<String> response = restTemplate.postForEntity(url, officeView1, String.class);

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
        officeView1.id = Long.valueOf(1);
        officeView1.name = "Office name";
        officeView1.address = null;

        String url = "/api/office/update";

        ResponseEntity<String> response = restTemplate.postForEntity(url, officeView1, String.class);

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
        officeView1.id = Long.valueOf(10);
        officeView1.name = "Office name";
        officeView1.address = "Ufa, Свердлова, 92";

        String url = "/api/office/update";

        ResponseEntity<String> response = restTemplate.postForEntity(url, officeView1, String.class);

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
        saveTestEntity(officeView);
        long id = findTestEntity(officeView.name, officeView.orgId);

        OfficeListInView officeListView = new OfficeListInView();
        officeListView.orgId = Long.valueOf(1);
        officeListView.name = "Office123 name";
        officeListView.phone = "79191489168";
        officeListView.isActive = true;

        String url = "/api/office/list";

        ResponseEntity<String> response = restTemplate.postForEntity(url, officeListView, String.class);
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
        officeListView.orgId = null;
        officeListView.name = "Office123 name";
        officeListView.phone = "79191489168";
        officeListView.isActive = true;

        String url = "/api/office/list";

        ResponseEntity<String> response = restTemplate.postForEntity(url, officeListView, String.class);
        String expected = "{\"error\":{\"message\":\"Не задано Id организации,котрой принадлежат офисы\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

}