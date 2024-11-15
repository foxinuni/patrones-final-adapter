package adapter.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import adapter.types.Employee;

public class RestEmployeeService {
    private final URI baseUrl;
    private final HttpClient client;

    public RestEmployeeService(URI baseUrl) {
        this.baseUrl = baseUrl;
        this.client = HttpClient.newHttpClient();
    }

    public List<Employee> GetAllEmployees() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(baseUrl)
            .GET()
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new Exception("Failed to get employees");
        }

        // Parse the JSON response into a list of Employee objects
        final ObjectMapper objectMapper = new ObjectMapper();
        final List<Employee> employees = objectMapper.readValue(response.body(), new TypeReference<List<Employee>>() {});

        return employees;

    }

    public void CreateEmployee(Employee employee) throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String jsonString = objectMapper.writeValueAsString(employee);
        
        HttpRequest request = HttpRequest.newBuilder()
            .uri(baseUrl)
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(jsonString))
            .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public void UpdateEmployee(Employee employee) throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String jsonString = objectMapper.writeValueAsString(employee);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(baseUrl + "/" + employee.id))
            .header("Content-Type", "application/json")
            .PUT(HttpRequest.BodyPublishers.ofString(jsonString))
            .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public void DeleteEmployee(Employee employee) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(baseUrl + "/" + employee.id))
            .DELETE()
            .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
