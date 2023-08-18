package SpringMockito.SpringMockito.controller;

import SpringMockito.SpringMockito.dto.Employee;
import SpringMockito.SpringMockito.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;
@RequestMapping("/department")
@RestController
public class DepartmentController {
    public DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/salary/max")
    public Employee findMaxSalaryByDepartment(@RequestParam int department) {
        return departmentService.findMaxSalaryByDepartment(department);
    }

    @GetMapping(path = "/salary/min")
    public Employee findMinSalaryByDepartment(@RequestParam int department) {
        return departmentService.findMinSalaryByDepartment(department);
    }
    @GetMapping("/salary/sum")
    public double findSumSalaryByDepartment(@RequestParam int department) {
        return departmentService.findSumSalaryByDepartment(department);
    }

    @GetMapping(path = "/all-employee")
    public Collection<Employee> getAllEmployeeByDepartment(@RequestParam int department) {
        return departmentService.getAllEmployeeByDepartment(department);
    }

    @GetMapping(path = "/employees")
    public Map<Integer, List<Employee>> getAllEmployee() {
        return departmentService.getEmployeeByDepartments();
    }
}
