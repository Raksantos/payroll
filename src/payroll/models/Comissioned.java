package payroll.models;


public class Comissioned extends Employee{

    private Double comission;

    public Comissioned(){

    }

    public Comissioned(String name, String address, Double salary, Double comission){
        super(name, address, salary);
        this.comission = comission;
    }

    public Double getComission() {
        return comission;
    }

    public void setComission(Double comission) {
        this.comission = comission;
    }

    @Override
    public String toString(){
        String data = "\n\nUser id: " + getId();
        data += "\nName: " + getName();
        data += "\nAddress: " + getAddress();
        data += "\nSalary: " + getSalary();
        data += "\nComission: " + getComission();
        return data;
    }
}
