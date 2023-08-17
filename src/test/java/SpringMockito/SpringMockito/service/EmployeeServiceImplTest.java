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
    @Test
    void addEmployee_employeeMaxCount_EmployeeStorageIsFullException() {
        underTest.addEmployee(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getSalary(),
                employee.getDepartment());
        underTest.addEmployee(
                employee1.getFirstName(),
                employee1.getLastName(),
                employee1.getSalary(),
                employee1.getDepartment());
        underTest.addEmployee(
                employee2.getFirstName(),
                employee2.getLastName(),
                employee2.getSalary(),
                employee2.getDepartment());
        underTest.addEmployee(
                employee3.getFirstName(),
                employee3.getLastName(),
                employee3.getSalary(),
                employee3.getDepartment());
        EmployeeStorageIsFullException ex =
                assertThrows(EmployeeStorageIsFullException.class,
                        () -> underTest.addEmployee("Ivanan", "Ivanora", 555, 1));
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
                        () -> underTest.addEmployee("Ivan", "Ivanor", 555, 1));
        assertEquals("Сотрудник есть", ex.getMessage());
    }
    @Test
    void addEmployee_employeeAddInMap_addAndReturnEmployee() {
        Employee result = underTest.addEmployee("Ivan", "Ivanor", 123, 1);
        assertEquals(employee, result);
    }
    //думал что сравнивать нужно с тем что добавил в метод addEmployee("Ivan", "Ivanor", 123, 1)- то есть это.
    //И думал что будет так assertEquals(employee, result);
    // Но, мы получается сравниваем с сотрудником, который уже как бы есть.
    // ps Метод Работает)))Верно ли?
    @Test
    void removeEmployee_employeeNotIsMap_ThrowNotFoundException() {
        EmployeeNotFoundException ex =
                assertThrows(EmployeeNotFoundException.class,
                        () -> underTest.removeEmployee("Ivan", "Ivanor"));
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
                        () -> underTest.findEmployee("Ivan", "Boobnov"));
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

}
