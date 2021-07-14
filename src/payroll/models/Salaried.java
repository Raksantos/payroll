package payroll.models;

public class Salaried extends Employee{
    
    public Salaried(){

    }

    public Salaried(String name, String address, Double salary, PaymentData paymentData){
        super(name, address, salary, paymentData);
    }
}
