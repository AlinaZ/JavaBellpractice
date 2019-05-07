package com.example.demo;

import com.example.demo.dao.user.UserDao;
import com.example.demo.view.user.UserListInView;
import com.example.demo.view.user.UserSaveView;
import com.example.demo.view.user.UserView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class UserIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    private void saveTestEntity(UserView view) {
        String url = "/api/user/save";
        ResponseEntity<String> response = restTemplate.postForEntity(url, view, String.class);
    }

    private Long findTestEntity(String name, Long officeId) {
        UserListInView userListView = new UserListInView();
        userListView.firstName = name;
        userListView.officeId = officeId;

        String url = "/api/user/list";
        ResponseEntity<String> response = restTemplate.postForEntity(url, userListView, String.class);
        int idPointer = response.getBody().indexOf("id");
        char idC = response.getBody().charAt(idPointer + 4);
        Long id = Long.valueOf(Character.getNumericValue(idC));
        return id;
    }


    public void fillTestUser(UserSaveView userView) {
        userView.officeId = Long.valueOf(1);
        userView.firstName = "Name";
        userView.lastName = "Lastname";
        userView.middleName = "Middlename";
        userView.position = "dolzhnost";
        userView.phone = "89191489168";
        userView.docName = "Паспорт гражданина Российской Федерации";
        userView.docCode = "21";
        userView.docNumber = "8012619159";
        userView.docDate = LocalDate.of(2012, 11, 12);
        userView.citizenshipCode = "643";
        userView.isIdentified = true;
    }

    /**
     * Тест на получение сотрудника по идентификатеру - позитивный сценарий
     */
    @Test
    public void getUserByIdPassTest() {
        UserSaveView userView = new UserSaveView();
        fillTestUser(userView);
        saveTestEntity(userView);

        long id = findTestEntity(userView.firstName, userView.officeId);

        String url = "/api/user/" + id;

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String expected = "{\"data\":" +
                "{\"id\":" + id + "," +
                "\"officeId\":1," +
                "\"firstName\":\"Name\"," +
                "\"lastName\":\"Lastname\"," +
                "\"middleName\":\"Middlename\"," +
                "\"position\":\"dolzhnost\"," +
                "\"phone\":\"89191489168\"," +
                "\"docName\":\"Паспорт гражданина Российской Федерации\"," +
                "\"docNumber\":\"8012619159\"," +
                "\"docDate\":\"2012-11-12\"," +
                "\"citizenshipCode\":\"643\"," +
                "\"isIdentified\":true}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест на получение сотрудника по идентификатеру - отрицательный сценарий (нет такого Id)
     */
    @Test
    public void getUserByIdFailTest() {
        String url = "/api/user/10";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String expected = "{\"error\":{\"message\":\"Нет сотрудника с Id=10\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест Сохранение нового сотрудника - положительный сценарий
     */
    @Test
    public void saveUserPassTest() {
        UserSaveView userView = new UserSaveView();
        fillTestUser(userView);

        String url = "/api/user/save";

        ResponseEntity<String> response = restTemplate.postForEntity(url, userView, String.class);

        String expected = "{\"data\":{\"result\":\"success\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест Сохранение нового сотрудника - не задан идентификатор офиса
     */
    @Test
    public void saveUserFailTest() {
        UserSaveView userView = new UserSaveView();
        fillTestUser(userView);
        userView.officeId = null;

        String url = "/api/user/save";

        ResponseEntity<String> response = restTemplate.postForEntity(url, userView, String.class);

        String expected = "{\"error\":{\"message\":\"Не задан идентификатор офиса, за которым закреплен сотрудник\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест Сохранение нового сотрудника - не задано имя сотрудника
     */
    @Test
    public void saveUserFailTest1() {
        UserSaveView userView = new UserSaveView();
        fillTestUser(userView);
        userView.firstName = null;

        String url = "/api/user/save";

        ResponseEntity<String> response = restTemplate.postForEntity(url, userView, String.class);

        String expected = "{\"error\":{\"message\":\"Не задано имя сотрудника\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест Сохранение нового сотрудника - не задана должность сотрудника
     */
    @Test
    public void saveUserFailTest2() {
        UserSaveView userView = new UserSaveView();
        fillTestUser(userView);
        userView.position = null;

        String url = "/api/user/save";

        ResponseEntity<String> response = restTemplate.postForEntity(url, userView, String.class);

        String expected = "{\"error\":{\"message\":\"Не задана должность сотрудника\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест Сохранение нового сотрудника - нет такого типа документа  по коду
     * /
     */
    @Test
    public void saveUserFailTest3() {
        UserSaveView userView = new UserSaveView();
        fillTestUser(userView);
        userView.docCode = "227";

        String url = "/api/user/save";

        ResponseEntity<String> response = restTemplate.postForEntity(url, userView, String.class);

        String expected = "{\"error\":{\"message\":\"Нет типа документа с таким кодом\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест Сохранение нового сотрудника - нет такого типа документа  по коду
     * /
     */
    @Test
    public void saveUserFailTest4() {
        UserSaveView userView = new UserSaveView();
        fillTestUser(userView);
        userView.docName = "Паспорт";

        String url = "/api/user/save";

        ResponseEntity<String> response = restTemplate.postForEntity(url, userView, String.class);

        String expected = "{\"error\":{\"message\":\"Нет типа документа с таким названием\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест Сохранение нового сотрудника - не совпадают типы документов по коду и по названию
     * /
     */
    @Test
    public void saveUserFailTest5() {
        UserSaveView userView = new UserSaveView();
        fillTestUser(userView);
        userView.docName = "Паспорт гражданина Российской Федерации";
        userView.docCode = "03";

        String url = "/api/user/save";

        ResponseEntity<String> response = restTemplate.postForEntity(url, userView, String.class);

        String expected = "{\"error\":{\"message\":\"Код и название типа документа не соответствуют\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест Сохранение нового сотрудника - нельзя определить тип документа
     * /
     */
    @Test
    public void saveUserFailTest7() {
        UserSaveView userView = new UserSaveView();
        fillTestUser(userView);
        userView.docName = null;
        userView.docCode = null;

        String url = "/api/user/save";

        ResponseEntity<String> response = restTemplate.postForEntity(url, userView, String.class);

        String expected = "{\"error\":{\"message\":\"Нельзя определить тип документа сотрудника\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест Сохранение нового сотрудника - нет такой страны по коду в справочнике
     * /
     */
    @Test
    public void saveUserFailTest9() {
        UserSaveView userView = new UserSaveView();
        fillTestUser(userView);
        userView.citizenshipCode = "19";

        String url = "/api/user/save";

        ResponseEntity<String> response = restTemplate.postForEntity(url, userView, String.class);

        String expected = "{\"error\":{\"message\":\"В справочнике Страны нет такой страны\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест обновление сотрудника - положительный сценарий
     */
    @Test
    public void updateUserPassTest() {
        UserSaveView userView = new UserSaveView();
        fillTestUser(userView);
        saveTestEntity(userView);
        long updId = findTestEntity(userView.firstName, userView.officeId);

        UserView user = new UserView();
        user.id = updId;
        user.firstName = "Name1";
        user.position = "dolzhnost1";
        user.docDate = LocalDate.of(2012, 11, 11);

        String url = "/api/user/update";

        ResponseEntity<String> response = restTemplate.postForEntity(url, user, String.class);

        String expected = "{\"data\":{\"result\":\"success\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест обновление сотрудника - не задан Id обновляемого сотрудника
     */
    @Test
    public void updateUserFailTest() {
        UserView user = new UserView();
        user.firstName = "Name1";
        user.position = "dolzhnost1";

        String url = "/api/user/update";

        ResponseEntity<String> response = restTemplate.postForEntity(url, user, String.class);

        String expected = "{\"error\":{\"message\":\"Невозможно обновить сотрудника: не задан ID\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест обновление сотрудника - нет такого Id обновляемого сотрудника
     */
    @Test
    public void updateUserFailTest1() {
        UserView user = new UserView();
        user.id = Long.valueOf(10);
        user.firstName = "Name1";
        user.position = "dolzhnost1";

        String url = "/api/user/update";

        ResponseEntity<String> response = restTemplate.postForEntity(url, user, String.class);

        String expected = "{\"error\":{\"message\":\"Сотрудника, которого вы пытаетесь обновить, нет в базе, id=10\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест обновление сотрудника - не задан обязательный параметр Имя сотрудника
     */
    @Test
    public void updateUserFailTest2() {
        UserView user = new UserView();
        user.id = Long.valueOf(1);
        user.firstName = null;
        user.position = "dolzhnost1";

        String url = "/api/user/update";

        ResponseEntity<String> response = restTemplate.postForEntity(url, user, String.class);

        String expected = "{\"error\":{\"message\":\"Не задано имя обновляемого сотруника\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест обновление сотрудника - не задан обязательный параметр Должность сотрудника
     */
    @Test
    public void updateUserFailTest3() {
        UserView user = new UserView();
        user.id = Long.valueOf(1);
        user.firstName = "NAme1";
        user.position = null;

        String url = "/api/user/update";

        ResponseEntity<String> response = restTemplate.postForEntity(url, user, String.class);

        String expected = "{\"error\":{\"message\":\"Не задана должность обновляемого сотруника\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест на вывод списка сотрудников по параметрам - положительный сценарий
     */
    @Test
    public void ListUsersPassTest() {
        UserSaveView userView = new UserSaveView();
        fillTestUser(userView);
        userView.firstName = "USer123";
        saveTestEntity(userView);
        long id = findTestEntity(userView.firstName, userView.officeId);

        UserListInView userListView = new UserListInView();
        userListView.officeId = Long.valueOf(1);
        userListView.firstName = userView.firstName;

        String url = "/api/user/list";

        ResponseEntity<String> response = restTemplate.postForEntity(url, userListView, String.class);

        String expected = "{\"data\":" +
                "[{\"id\":" + id + "," +
                "\"firstName\":\"USer123\"," +
                "\"lastName\":\"Lastname\"," +
                "\"middleName\":\"Middlename\"," +
                "\"position\":\"dolzhnost\"}]}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест на вывод списка сотрудников - не задан обязательный параметр - Id офиса
     */
    @Test
    public void ListUserFailTest() {
        UserListInView userListView = new UserListInView();
        userListView.officeId = null;
        userListView.firstName = "UserName";

        String url = "/api/user/list";

        ResponseEntity<String> response = restTemplate.postForEntity(url, userListView, String.class);

        String expected = "{\"error\":{\"message\":\"Не задан Id офиса, за которым закреплен сотрудник\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }


}