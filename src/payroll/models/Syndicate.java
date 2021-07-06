package payroll.models;

import java.util.UUID;

import payroll.models.services.ServiceTax;

import java.util.ArrayList;

public class Syndicate {
    private UUID id;

    private UUID employeeId;

    private boolean isAffiliated;

    private Double tax;

    private ArrayList<ServiceTax> serviceTax;

    public Syndicate(){

    }

    public Syndicate(UUID employeeId, boolean isAffiliated, Double tax){
        this.id = UUID.randomUUID();
        this.employeeId = employeeId;
        this.isAffiliated = isAffiliated;
        this.tax = tax;
        this.serviceTax = new ArrayList<ServiceTax>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public UUID getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
    }

    public boolean getIsAffiliated() {
        return this.isAffiliated;
    }

    public ArrayList<ServiceTax> getServiceTax() {
        return serviceTax;
    }

    @Override
    public String toString(){
        String data = "\n\t\tSyndicate Employee id: " + getId();
        data += "\n\t\tEmployee id: " + getEmployeeId();
        data += "\n\t\tAffiliated: " + getIsAffiliated();
        data += "\n\t\tTax: " + getTax();
        
        return data;
    }
}
