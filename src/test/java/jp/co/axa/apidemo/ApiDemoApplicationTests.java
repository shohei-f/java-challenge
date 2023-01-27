package jp.co.axa.apidemo;

import java.math.BigInteger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.axa.apidemo.entities.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApiDemoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void test_getall_zero() throws Exception {

        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/employees"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Order(2)
    public void test_post() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        Employee emp = new Employee();
        emp.setName("test");
        emp.setSalary(new BigInteger("300000"));
        emp.setDepartment("sales");

        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/employees", emp)
                .content(objectMapper.writeValueAsString(emp))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @Order(3)
    public void test_get() throws Exception {

        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/employees/1"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Order(4)
    public void test_put() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        Employee emp = new Employee();
        emp.setId(1L);
        emp.setName("test");
        emp.setSalary(new BigInteger("400000"));
        emp.setDepartment("sales");

        this.mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1/employees/1")
                .content(objectMapper.writeValueAsString(emp))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Order(5)
    public void test_delete() throws Exception {

        this.mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/v1/employees/1"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }


}
