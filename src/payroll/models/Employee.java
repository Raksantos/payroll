package payroll.models;

import java.util.UUID;

public class Employee {
    private UUID id;
    private String name;
    private String address;
    private Double salary;
    private Syndicate employeeSyndicate;
    
    public Employee(){

    }

    public Employee(String name, String address, Double salary){
        this.id = UUID.randomUUID();
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.employeeSyndicate = null;
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

    public Syndicate getEmployeeSyndicate() {
        return employeeSyndicate;
    }

    public void setEmployeeSyndicate(Syndicate employeeSyndicate) {
        this.employeeSyndicate = employeeSyndicate;
    }

    @Override
    public String toString(){
        String data = "\n\nUser id: " + getId();
        data += "\nName: " + getName();
        data += "\nAddress: " + getAddress();
        data += "\nSalary: " + getSalary();

        if(this.employeeSyndicate.getIsAffiliated()){
            data += this.employeeSyndicate.toString();
        }

        return data;
    }
}
