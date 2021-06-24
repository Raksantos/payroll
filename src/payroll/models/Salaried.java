package payroll.models;

import java.util.UUID;

public class Salaried extends Employee{
    public Salaried(UUID id, String name, String address){
        super(id, name, address);
    }
}
