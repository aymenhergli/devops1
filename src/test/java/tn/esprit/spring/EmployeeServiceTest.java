package tn.esprit.spring;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.services.IEmployeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {



    @Autowired
    IEmployeService employeService;

    @Test
    public void testContextLoads() {
        Employe e = new Employe("aymen", "hergli", "aymen.hergli@esprit.tn", "password", true, Role.INGENIEUR );
        this.employeService.addOrUpdateEmploye(e);
        assertThat(e.getNom()).isEqualTo("aymen");
    }

}