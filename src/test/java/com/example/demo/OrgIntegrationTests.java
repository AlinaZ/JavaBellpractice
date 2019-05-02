package com.example.demo;

import com.example.demo.dao.organization.OrganizationDaoImpl;
import com.example.demo.service.organization.OrganizationServiceImpl;
import com.example.demo.view.organization.OrganizationView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.demo.DemoApplication;
import com.example.demo.dao.organization.OrganizationDao;
import com.example.demo.model.Organization;
import com.example.demo.service.organization.OrganizationService;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = DemoApplication.class)

public class OrgIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private OrganizationDao dao;

    @Autowired
    private OrganizationServiceImpl service;

    private HttpHeaders headers;

    @Before
    public void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        //repository.deleteAll();
    }

    /**
     * Тест на получение организации по идентификатеру - позитивный сценарий
     */
    @Test
    public void getOrgByIdPassTest() {
        OrganizationView orgView=new OrganizationView();
        orgView.name="someNewOrg";
        orgView.fullName="Some Org ltd.";
        orgView.address="Samara, Mashinostroenia st, 27A";
        orgView.inn="542634745674";
        orgView.kpp="322544987";
        orgView.phone="89107997878";
        orgView.isActive=true;
        service.add(orgView);
        long id = dao.loadByName("someNewOrg").getId();
        String url = "/api/organization/" + id;

        HttpEntity entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        String expected = "{\"data\":" +
                "{\"id\":"+ id + "," +
                "\"name\":\"someNewOrg\"," +
                "\"fullName\":\"Some Org ltd.\"," +
                "\"inn\":\"542634745674\"," +
                "\"kpp\":\"322544987\"," +
                "\"address\":\"Samara, Mashinostroenia st, 27A\"," +
                "\"phone\":\"89107997878\"," +
                "\"isActive\":true}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

    /**
     * Тест на получение организации по идентификатеру - отрицательный сценарий
     */

    @Test
    public void getOrgByIdFailTest() {
        String url = "/api/organization/10";
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        String expected = "{\"error\":{\"message\":\"Нет организации с Id=10\"}}";
        String result = response.getBody();
        assertThat(result, is(expected));
    }

}
