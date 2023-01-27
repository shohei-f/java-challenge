package jp.co.axa.apidemo.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jp.co.axa.apidemo.constants.API;
import jp.co.axa.apidemo.constants.Message;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;

/**
 * Employee Controller
 *
 * @author shohei
 */
@RestController
@RequestMapping(API.URL_EMPlOYEEAPI)
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    private Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Get information on all employees
     * @return all employees
     */
    @GetMapping(API.URL_EMPlOYEES)
    public List<Employee> getEmployees() {

        logger.info(Message.INFO_EXEC, API.URL_EMPlOYEES);

        // select
        List<Employee> employees = employeeService.retrieveEmployees();

        logger.info(Message.INFO_RESULT, employees.size());
        return employees;
    }

    /**
     * Retrieve employee information with a given employeeId
     * @param employeeId
     * @return employee information
     */
    @GetMapping(API.URL_EMPlOYEE_ID)
    public Employee getEmployee(@PathVariable(name=Employee.FN_ID)Long employeeId) {

        logger.info(Message.INFO_EXEC_PRM, API.URL_EMPlOYEE_ID, employeeId);

        // select
        Employee emp = employeeService.getEmployee(employeeId);

        // id is not found
        if (emp == null) {
            logger.info(String.format(Message.EMPLOYEE_NOT_FOUND, employeeId));
            throw new EmployeeNotFoundException(String.format(Message.EMPLOYEE_NOT_FOUND, employeeId));
        }

        logger.info(Message.INFO_RESULT, emp);
        return emp;
    }

    /**
     * Register given employee information
     * @param employee
     * @return result
     */
    @PostMapping(API.URL_EMPlOYEES)
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee){

        logger.info(Message.INFO_EXEC_BODY, API.URL_EMPlOYEES, employee);

        // insert
        Employee emp = employeeService.saveEmployee(employee);

        logger.info(Message.INFO_SAVEDSUCCESS);
        logger.info(Message.INFO_RESULT, emp);

        // location header
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(API.URL_EMPlOYEE_PARAM)
                .buildAndExpand(emp.getId())
                .toUri();

        return ResponseEntity.created(location).body(emp);
    }

    /**
     * Delete employee information with a given employeeId
     * @param employeeId
     */
    @DeleteMapping(API.URL_EMPlOYEE_ID)
    public void deleteEmployee(@PathVariable(name=Employee.FN_ID)Long employeeId){

        logger.info(Message.INFO_EXEC_PRM, API.URL_EMPlOYEE_ID, employeeId);

        // id check
        Employee emp = employeeService.getEmployee(employeeId);

        // id is not found
        if (emp == null) {
            logger.info(String.format(Message.EMPLOYEE_NOT_FOUND, employeeId));
            throw new EmployeeNotFoundException(String.format(Message.EMPLOYEE_NOT_FOUND, employeeId));
        }

        // delete
        employeeService.deleteEmployee(employeeId);

        logger.info(Message.INFO_DELETEDSUCCESS);

    }

    /**
     * Update employee information
     * @param employee
     * @param employeeId
     * @return employee information
     */
    @PutMapping(API.URL_EMPlOYEE_ID)
    public Employee updateEmployee(@RequestBody Employee employee,
            @PathVariable(name=Employee.FN_ID)Long employeeId){

        logger.info(Message.INFO_EXEC_PRM_BODY, API.URL_EMPlOYEE_ID, employee, employeeId);

        // id check
        Employee emp = employeeService.getEmployee(employeeId);

        // id is not found
        if (emp == null) {
            logger.info(String.format(Message.EMPLOYEE_NOT_FOUND, employeeId));
            throw new EmployeeNotFoundException(String.format(Message.EMPLOYEE_NOT_FOUND, employeeId));
        }

        // update
        Employee updEmp = employeeService.updateEmployee(employee);

        logger.info(Message.INFO_RESULT, emp);

        return updEmp;
    }
}
