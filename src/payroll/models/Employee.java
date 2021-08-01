package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;
import java.util.function.Predicate;

import models.services.payment.PaymentData;
import models.services.payment.PayCheck;
import models.services.SaleResult;
import models.services.ServiceTax;
import models.services.TimeCard;

import java.time.Duration;
import java.time.LocalTime;

import static java.util.stream.Collectors.toCollection;

import java.io.Serializable;

public class Employee implements Serializable{
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

        if(this instanceof Comissioned){
            Comissioned auxEmp = (Comissioned) this;

            ArrayList<SaleResult> sales = auxEmp.getSales();

            if(!sales.isEmpty()){
                for(int i = 0; i < sales.size(); i++){
                    if(sales.get(i).getDate().compareTo(date) <= 0){
                        paymentValue += calculateComission(auxEmp);
    
                        sales.remove(i);
                    }
                }   
            }

            auxEmp.setSales(sales);
        }

        if(this instanceof Hourly){
            Hourly auxEmp = (Hourly) this;

            ArrayList<TimeCard> timeCards = auxEmp.getTimeCards();

            paymentValue = 0.0;

            boolean found = false;

            if(!timeCards.isEmpty()){
                for(int i = 0; i < timeCards.size(); i++){
                    if(timeCards.get(i).getDate().compareTo(date) <= 0){
                        paymentValue = getPayment(auxEmp, timeCards.get(i));
    
                        timeCards.remove(i);

                        found = true;
                    }
                }
                
                if(!found) paymentValue = 0.0;
            }

            auxEmp.setTimeCards(timeCards);
        }

        paymentValue -= taxes;
        
        payCheck = new PayCheck(this, paymentValue, taxes, haveTax, date);
        this.getPaymentData().getPayChecks().add(payCheck);
        return payCheck;
    }

    private Double getPayment(Hourly employee, TimeCard timeCard){
        double payment = 0.0, hours = 0.0, extraHours = 0.0;

        LocalTime timeEntry = timeCard.getTimeEntry();
        LocalTime timeOut = timeCard.getTimeOut();

        Duration time = Duration.between(timeEntry, timeOut);

        hours = (double) time.getSeconds()/3600;

        System.out.println("Hours:" + hours);

        if(hours <= 0 ) return payment;

        if(hours > 8.0){
            extraHours = hours - 8.0;
            payment += 8.0 * employee.getSalary();

            payment += extraHours * 1.5 * employee.getSalary();
        }else{
            payment = hours * employee.getSalary();
        }

        return payment;
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

            for(ServiceTax tax : serviceTaxes){
                taxes += tax.getValue();
            }
        }
        
        taxes += this.getEmployeeSyndicate().getTax();

        return taxes;
    }

    public double calculateComission(Comissioned employee){
        double totalComission = 0.0;

        totalComission += employee.getComission();

        return totalComission;
    }
}
