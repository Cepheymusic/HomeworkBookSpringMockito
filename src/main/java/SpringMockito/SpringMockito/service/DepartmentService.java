package SpringMockito.SpringMockito.service;

import SpringMockito.SpringMockito.dto.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee findMaxSalaryByDepartment(int department);

    double findSumSalaryByDepartment(int department);

    Employee findMinSalaryByDepartment(int department);

    Collection<Employee> getAllEmployeeByDepartment(int department);

    Map<Integer, List<Employee>> getEmployeeByDepartments();
}
