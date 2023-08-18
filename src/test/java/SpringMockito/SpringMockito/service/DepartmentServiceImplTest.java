package SpringMockito.SpringMockito.service;

import SpringMockito.SpringMockito.dto.Employee;
import SpringMockito.SpringMockito.exception.EmployeeNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Incubating;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    @Mock
    EmployeeService employeeService;
    @InjectMocks
    DepartmentServiceImpl underTest;

    Employee employee = new Employee("Ivan", "Ivanor", 555, 1);
    Employee employee1 = new Employee("Ivans", "Ivanov", 555, 2);
    Employee employee2 = new Employee("Ivand", "Ivanok", 55, 2);
    Employee employee3 = new Employee("Ivanf", "Ivanop", 5550, 2);
    Employee employee4 = new Employee("Ivang", "Ivanos", 555, 3);
    Employee employee5 = new Employee("Ivan", "Ivanor", 555, 1);
    List<Employee> employees = List.of(employee, employee1,employee2, employee3, employee4, employee5);

    @Test
    void findMaxSalaryEmployee_employeeFind_employeeWithMaxSalary() {
        when(employeeService.findAll()).thenReturn(employees);
        Employee result = underTest.findMaxSalaryByDepartment(2);
        assertEquals(employee3, result);
    }

    @Test
    void findMaxSalaryEmployee_employeeFind_employeeWithMaxSalaryNotFind() {
        when(employeeService.findAll()).thenReturn(employees);
        EmployeeNotFoundException ex =
        assertThrows(EmployeeNotFoundException.class,
                () -> underTest.findMaxSalaryByDepartment(4));
        assertEquals("Сотрудник не найден", ex.getMessage());
    }
    @Test
    void findSumSalaryEmployeeByDepartment_sumSalaryEmployeesByDepartmentFind_sumSalaryEmployeeByDepartment() {
        when(employeeService.findAll()).thenReturn(employees);
        double result = underTest.findSumSalaryByDepartment(2);
        assertEquals(6160.0, result);
    }
    @Test
    void findSumSalaryEmployee_employeeFind_employeeWithMaxSalaryNotFind() {
        when(employeeService.findAll()).thenReturn(employees);
        EmployeeNotFoundException ex =
                assertThrows(EmployeeNotFoundException.class,
                        () -> underTest.findMaxSalaryByDepartment(4));
        assertEquals("Сотрудник не найден", ex.getMessage());
    }

    @Test
    void findMinSalaryEmployee_employeeFind_employeeWithMinSalary() {
        when(employeeService.findAll()).thenReturn(employees);
        Employee result = underTest.findMinSalaryByDepartment(2);
        assertEquals(employee3, result);
    }

    @Test
    void findMinSalaryEmployee_employeeFind_employeeWithMinSalaryNotFind() {
        when(employeeService.findAll()).thenReturn(employees);
        EmployeeNotFoundException ex =
                assertThrows(EmployeeNotFoundException.class,
                        () -> underTest.findMinSalaryByDepartment(4));
        assertEquals("Сотрудник не найден", ex.getMessage());
    }
    @Test
    void getAllEmployeeByDepartment_allEmployeeIsFind_returnAllEmployeeByDepartment() {
        when(employeeService.findAll()).thenReturn(employees);
        Collection<Employee> result = underTest.getEmployeeByDepartments(2);

    }
}