package payroll.models;

import java.util.UUID;

public class Employee {
    private UUID id;
    private String name;
    private String address;
    private Double salary;
    
    public Employee(){

    }

    public Employee(UUID id, String name, String address, Double salary){
        this.id = id;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String addres) {
        this.address = addres;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString(){
        String data = "\n\nUser id: " + getId();
        data += "\nName: " + getName();
        data += "\nAddress: " + getAddress();
        data += "\nSalary: " + getSalary();
        return data;
    }
}
