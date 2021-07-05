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
        String data = "\n\n{\n\tUser id: " + getId();
        data += "\n\tName: " + getName();
        data += "\n\tAddress: " + getAddress();
        data += "\n\tSalary: " + getSalary();
        data += "\n\tComission: " + getComission();

        if(this.getEmployeeSyndicate().getIsAffiliated()){
            data += "\n\tSyndicate: { ";
            data += this.getEmployeeSyndicate().toString();
            data += "\n\t}";
        }

        data += "\n}";
        return data;
    }
}
