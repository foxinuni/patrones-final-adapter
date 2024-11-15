package adapter.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import adapter.types.Employee;

public class PgEmployeeService {
    private final Connection connection;

    public PgEmployeeService(Connection connection) {
        this.connection = connection;
    }

    public List<Employee> GetAllEmployees() throws SQLException {
        final PreparedStatement statement = connection.prepareStatement(
            "SELECT id, first_name, last_name, position, salary FROM employees"
        );

        final ResultSet results = statement.executeQuery();

        final List<Employee> employees = new java.util.ArrayList<Employee>();
        while (results.next()) {
            final Employee employee = new Employee(
                results.getInt("id"),
                results.getString("first_name"),
                results.getString("last_name"),
                results.getString("position"),
                results.getFloat("salary")
            );

            employees.add(employee);
        }

        return employees;
    }

    public void UpsertEmployee(Employee employee) throws SQLException {
        // Using postgres upsert
        final PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO employees (first_name, last_name, position, salary) VALUES (?, ?, ?, ?) ON CONFLICT (id) DO UPDATE SET first_name = ?, last_name = ?, position = ?, salary = ?"
        );

        statement.setString(1, employee.firstName);
        statement.setString(2, employee.lastName);
        statement.setString(3, employee.position);
        statement.setFloat(4, employee.salary);

        statement.setString(5, employee.firstName);
        statement.setString(6, employee.lastName);
        statement.setString(7, employee.position);
        statement.setFloat(8, employee.salary);

        // Execute the statement
        statement.executeUpdate();
    }

    public void DeleteEmployee(Employee employee) throws SQLException {
        final PreparedStatement statement = connection.prepareStatement(
            "DELETE FROM employee WHERE id = ?"
        );

        statement.setInt(1, employee.id);

        // Execute the statement
        statement.executeUpdate();
    }
}
