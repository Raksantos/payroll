package payroll.models;

import java.util.UUID;

public class Hourly extends Employee{
    public Hourly(UUID id, String name, String address){
        super(id, name, address);
    }
}
