package SpringMockito.SpringMockito.service;

import SpringMockito.SpringMockito.dto.Employee;
import SpringMockito.SpringMockito.exception.EmployeeAlreadyAddedException;
import SpringMockito.SpringMockito.exception.EmployeeNotFoundException;
import SpringMockito.SpringMockito.exception.EmployeeStorageIsFullException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {

    EmployeeService underTest = new EmployeeServiceImpl();
    Employee employee = new Employee("Ivan", "Ivanor", 555, 1);
    Employee employee1 = new Employee("Ivans", "Ivanov", 555, 2);
    Employee employee2 = new Employee("Ivand", "Ivanok", 555, 2);
    Employee employee3 = new Employee("Ivanf", "Ivanop", 555, 2);
    Employee employee4 = new Employee("Ivang", "Ivanos", 555, 3);
    Employee employee5 = new Employee("Ivan", "Ivanor", 555, 1);
    List<Employee> employees = List.of(employee, employee1,employee2, employee3);
    @Test
    void addEmployee_employeeMaxCount_EmployeeStorageIsFullException() {
        for(Employee employee : employees) {
            underTest.addEmployee(
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getSalary(),
                    employee.getDepartment());
        }
        EmployeeStorageIsFullException ex =
                assertThrows(EmployeeStorageIsFullException.class,
                        () -> underTest.addEmployee(
                                employee4.getFirstName(),
                                employee4.getLastName(),
                                employee4.getSalary(),
                                employee4.getDepartment()));
        assertEquals("Превышен лимит сотрудников", ex.getMessage());
    }
    @Test
    void addEmployee_employeeIsInMap_EmployeeAlreadyAddedException() {
        underTest.addEmployee(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getSalary(),
                employee.getDepartment());
        EmployeeAlreadyAddedException ex =
                assertThrows(EmployeeAlreadyAddedException.class,
                        () -> underTest.addEmployee(
                                employee5.getFirstName(),
                                employee5.getLastName(),
                                employee5.getSalary(),
                                employee5.getDepartment()));
        assertEquals("Сотрудник есть", ex.getMessage());
    }
    @Test
    void addEmployee_employeeAddInMap_addAndReturnEmployee() {
        Employee result = underTest.addEmployee(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getSalary(),
                employee.getDepartment());
        assertEquals(employee, result);
        assertTrue(underTest.findAll().contains(employee));
    }
    @Test
    void removeEmployee_employeeNotIsMap_ThrowNotFoundException() {
        EmployeeNotFoundException ex =
                assertThrows(EmployeeNotFoundException.class,
                        () -> underTest.removeEmployee(
                                employee.getFirstName(),
                                employee.getLastName()));
        assertEquals("Сотрудник не найден", ex.getMessage());
    }
    @Test
    void removeEmployee_employeeInMap_removedAndReturnEmployee() {
        underTest.addEmployee(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getSalary(),
                employee.getDepartment());
        Employee result = underTest.removeEmployee(employee.getFirstName(), employee.getLastName());
        assertEquals(employee, result);
        assertFalse(underTest.findAll().contains(employee));
    }
    @Test
    void findEmployee_employeeNotIsMap_ThrowNotFoundException() {
        underTest.addEmployee(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getSalary(),
                employee.getDepartment());
        EmployeeNotFoundException ex =
                assertThrows(EmployeeNotFoundException.class,
                        () -> underTest.findEmployee(
                                employee2.getFirstName(),
                                employee2.getLastName()));
        assertEquals("Сотрудник не найден", ex.getMessage());
    }
    @Test
    void findEmployee_employeeInMap_returnEmployee() {
        underTest.addEmployee(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getSalary(),
                employee.getDepartment());
        Employee result = underTest.findEmployee(
                employee.getFirstName(),
                employee.getLastName());
        assertEquals(employee, result);
    }
    @Test
    void findAll_allEmployeeInMap_returnAllEmployee() {
        for(Employee employee : employees) {
            underTest.addEmployee(
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getSalary(),
                    employee.getDepartment());
        }
        assertTrue(underTest.findAll().containsAll(employees));
    }
}
