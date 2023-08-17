package SpringMockito.SpringMockito.controller;

import SpringMockito.SpringMockito.util.EmployeeNameValidator;
import SpringMockito.SpringMockito.service.EmployeeServiceImpl;
import SpringMockito.SpringMockito.dto.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RequestMapping("/employee")
@RestController
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {

        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam String firstName,
                                @RequestParam String lastName,
                                @RequestParam double salary,
                                @RequestParam int department) {
        EmployeeNameValidator.checkName(firstName, lastName);
        return employeeService.addEmployee(firstName, lastName, salary, department);
    }
    @GetMapping(path = "/remove")
    public Employee removeEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        EmployeeNameValidator.checkName(firstName, lastName);
        return employeeService.removeEmployee(firstName, lastName);
    }
    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        EmployeeNameValidator.checkName(firstName, lastName);
        return employeeService.findEmployee(firstName, lastName);
    }
    @GetMapping
    public Collection<Employee> findALl(){
        return employeeService.findAll();
    }
}