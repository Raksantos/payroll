package payroll.models;

import java.time.LocalDate;

public class ServiceTax extends Operations{
    public ServiceTax(){

    }

    public ServiceTax(Double value, LocalDate date){
        super(value, date);
    }
}
