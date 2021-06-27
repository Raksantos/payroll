package payroll.models;

import java.util.UUID;

public class Comissioned extends Employee{

    private Double comission;

    public Comissioned(){

    }

    public Comissioned(UUID id, String name, String address, Double salary, Double comission){
        super(id, name, address, salary);
        this.comission = comission;
    }

    public Double getComission() {
        return comission;
    }

    public void setComission(Double comission) {
        this.comission = comission;
    }
}
