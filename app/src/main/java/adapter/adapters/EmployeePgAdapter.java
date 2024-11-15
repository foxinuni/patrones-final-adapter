package adapter.adapters;

import java.util.List;

import adapter.EmployeeServiceAdapter;
import adapter.services.PgEmployeeService;
import adapter.types.Employee;

public class EmployeePgAdapter implements EmployeeServiceAdapter {
    private final PgEmployeeService employeeService;

    public EmployeePgAdapter(PgEmployeeService employeeService) {
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
            employeeService.UpsertEmployee(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void UpdateEmployee(Employee employee) {
        try {
            employeeService.UpsertEmployee(employee);
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
