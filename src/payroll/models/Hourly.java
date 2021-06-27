package payroll.models;

import java.util.UUID;

public class Hourly extends Employee{

    public Hourly(){

    }

    public Hourly(UUID id, String name, String address, Double salary){
        super(id, name, address, salary);
    }
}
