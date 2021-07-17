package utils;

import models.Employee;

public class ValueHolder {
    private Employee employee;
    private Double comission;

    ValueHolder(Employee employee, Double comission){
        this.comission = comission;
        this.employee = employee;
    }   

    public Double getComission() {
        return comission;
    }

    public Employee getEmployee() {
        return employee;
    }
}
