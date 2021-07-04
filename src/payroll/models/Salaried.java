package payroll.models;

public class Salaried extends Employee{
    
    public Salaried(){

    }

    public Salaried(String name, String address, Double salary){
        super(name, address, salary);
    }
}
