package payroll.models;

import java.util.UUID;

public class Hourly extends Employee{

    public Hourly(){

    }

    public Hourly(String name, String address, Double salary){
        super(name, address, salary);
    }
}
