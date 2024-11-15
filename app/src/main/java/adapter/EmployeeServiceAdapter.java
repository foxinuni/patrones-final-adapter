package adapter;

import java.util.List;

import adapter.types.Employee;

public interface EmployeeServiceAdapter {
    List<Employee> GetAllEmployees();
    void CreateEmployee(Employee employee);
    void UpdateEmployee(Employee employee);
    void DeleteEmployee(Employee employee);
}