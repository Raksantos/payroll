package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;
import java.util.function.Predicate;

import models.services.payment.PaymentData;
import models.services.payment.PayCheck;
import models.services.SaleResult;
import models.services.ServiceTax;

import static java.util.stream.Collectors.toCollection;

public class Employee {
    private UUID id;
    private String name;
    private String address;
    private Double salary;
    private PaymentData paymentData;
    private Syndicate employeeSyndicate;
    
    public Employee(){

    }

    public Employee(String name, String address, Double salary, PaymentData paymentData){
        this.id = UUID.randomUUID();
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.paymentData = paymentData;
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

    public void setAddress(String address) {
        this.address = address;
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

    public PaymentData getPaymentData() {
        return paymentData;
    }

    public void setPaymentData(PaymentData paymentData) {
        this.paymentData = paymentData;
    }

    @Override
    public String toString(){
        String data = "\n{\n\tUser id: " + getId();
        data += "\n\tName: " + getName();
        data += "\n\tAddress: " + getAddress();
        data += "\n\tSalary: " + getSalary();
        data += "\n\tPayment Data: {" + getPaymentData();

        if(this.employeeSyndicate.getIsAffiliated()){
            data += "\n\tSyndicate: { ";
            data += this.employeeSyndicate.toString();
            data += "\n\t}";
        }

        data += "\n}\n";
        return data;
    }

    public PayCheck makePayment(LocalDate date){
        PayCheck payCheck;
        Double paymentValue = this.getSalary();
        Double taxes = calculateServiceTaxes(); 
        boolean haveTax = false;

        paymentValue -= taxes;

        if(this instanceof Comissioned){
            paymentValue += calculateComission((Comissioned) this);
        }
        
        payCheck = new PayCheck(this, paymentValue, taxes, haveTax, date);
        this.getPaymentData().getPayChecks().add(payCheck);
        return payCheck;
    }

    public Double calculateServiceTaxes(){
        double taxes = 0.0;

        ArrayList<ServiceTax> serviceTaxes;
        ArrayList<PayCheck> payChecks = this.getPaymentData().getPayChecks();

        if(this.getEmployeeSyndicate().getIsAffiliated() == true){
            if(!payChecks.isEmpty()){
                LocalDate lastDate = payChecks.get(payChecks.size() - 1).getDate();
                Predicate<ServiceTax> dateFilter = tax -> tax.getDate().isAfter(lastDate);

                serviceTaxes = this.getEmployeeSyndicate().getServiceTax().stream().filter(dateFilter).collect(toCollection(ArrayList::new));
            } else {
                serviceTaxes = this.getEmployeeSyndicate().getServiceTax();
            }

            for(ServiceTax stax : serviceTaxes){
                taxes += stax.getValue();
            }
        }
        
        taxes += this.getEmployeeSyndicate().getTax();

        return taxes;
    }

    public double calculateComission(Comissioned employee){
        double totalComission = 0.0;

        for(int i = 0; i < employee.getSales().size(); i++){
            totalComission += employee.getComission();
        }

        return totalComission;
    }
}
