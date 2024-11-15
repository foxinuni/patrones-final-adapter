package adapter.types;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {
    @JsonProperty("id")
    public int id;

    @JsonProperty("first_name")
    public String firstName;

    @JsonProperty("last_name")
    public String lastName;

    @JsonProperty("position")
    public String position;

    @JsonProperty("salary")
    public float salary;

    public Employee() {}

    public Employee(int id, String firstName, String lastName, String position, float salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.salary = salary;
    }

    public String toString() {
        return "Employee (id: " + id + ", first_name: " + firstName + ", last_name: " + lastName + ", position: " + position + ", salary: " + salary + ")";
    }
}