package payroll.models;

import java.time.LocalDate;

public class SaleResult extends Operations{
    public SaleResult(){

    }

    public SaleResult(Double value, LocalDate date){
        super(value, date);
    }
}
