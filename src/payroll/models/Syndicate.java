package payroll.models;

import java.util.UUID;

public class Syndicate {
    private UUID id;

    private UUID employeeId;

    private boolean isAffiliated;

    private Double tax;

    public Syndicate(){

    }

    public Syndicate(UUID employeeId, boolean isAffiliated, Double tax){
        this.id = UUID.randomUUID();
        this.employeeId = employeeId;
        this.isAffiliated = isAffiliated;
        this.tax = tax;
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

    @Override
    public String toString(){
        String data = "\n\nSyndicate Employee id: " + getId();
        data += "\nEmployee id: " + getEmployeeId();
        data += "\nAffiliated: " + getIsAffiliated();
        data += "\nTax: " + getTax();
        
        return data;
    }
}
