package adapter.adapters;

import java.util.List;

import adapter.EmployeeServiceAdapter;
import adapter.services.RestEmployeeService;
import adapter.types.Employee;

public class EmployeeRestAdapter implements EmployeeServiceAdapter {
    final RestEmployeeService employeeService;

    public EmployeeRestAdapter(RestEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public List<Employee> GetAllEmployees() {
        try {
            return employeeService.GetAllEmployees();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    @Override
    public void CreateEmployee(Employee employee) {
        try {
            employeeService.CreateEmployee(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void UpdateEmployee(Employee employee) {
        try {
            employeeService.UpdateEmployee(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void DeleteEmployee(Employee employee) {
        try {
            employeeService.DeleteEmployee(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
