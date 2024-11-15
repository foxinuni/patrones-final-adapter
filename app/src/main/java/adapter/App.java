package adapter;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import adapter.adapters.EmployeePgAdapter;
import adapter.adapters.EmployeeRestAdapter;
import adapter.services.PgEmployeeService;
import adapter.services.RestEmployeeService;
import adapter.types.Employee;

public class App {
    public static void main(String[] args) throws SQLException {
        // Sources
        final URI baseUrl = URI.create("https://angelic-intuition-production-4deb.up.railway.app/employees");
        final Connection connection = DriverManager.getConnection("jdbc:postgresql://junction.proxy.rlwy.net:15960/railway?user=postgres&password=DZCRQCnnHImtyDivsfCZCPXkeeVyCDnY");
        
        // Services
        final RestEmployeeService rest = new RestEmployeeService(baseUrl);
        final PgEmployeeService postgres = new PgEmployeeService(connection);

        // Adapters
        final EmployeeServiceAdapter restAdapter = new EmployeeRestAdapter(rest);
        final EmployeeServiceAdapter pgAdapter = new EmployeePgAdapter(postgres);

        // Testing rest
        System.out.println("----- REST ------");
        System.out.println("Antes: ");
        {
            final List<Employee> employees = restAdapter.GetAllEmployees();
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
        System.out.println("");

        // Se crea a la persona
        restAdapter.CreateEmployee(new Employee(0, "Sarah", "Smith", "Software Engineer", 10000));

        System.out.println("Despues: ");
        {
            final List<Employee> employees = restAdapter.GetAllEmployees();
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }

        // Testing postgres
        System.out.println("----- POSTGRES ------");
        System.out.println("Antes: ");
        {
            final List<Employee> employees = pgAdapter.GetAllEmployees();
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
        System.out.println("");

        // Se crea a la persona
        pgAdapter.CreateEmployee(new Employee(0, "Sarah", "Smith", "Software Engineer", 10000));

        System.out.println("Despues: ");
        {
            final List<Employee> employees = pgAdapter.GetAllEmployees();
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }
}
