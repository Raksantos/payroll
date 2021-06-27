package payroll.models;

import java.util.UUID;

public class Salaried extends Employee{
    
    public Salaried(){

    }

    public Salaried(UUID id, String name, String address, Double salary){
        super(id, name, address, salary);
    }
}
