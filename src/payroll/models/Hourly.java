package models;

import java.util.ArrayList;

import models.services.TimeCard;
import models.services.payment.PaymentData;

public class Hourly extends Employee{

    private ArrayList<TimeCard> timeCards;

    public Hourly(){
        
    }

    public Hourly(String name, String address, Double salary, PaymentData paymentData){
        super(name, address, salary, paymentData);
        this.timeCards = new ArrayList<TimeCard>();
    }

    public ArrayList<TimeCard> getTimeCards() {
        return timeCards;
    }

    public void setTimeCards(ArrayList<TimeCard> timeCards) {
        this.timeCards = timeCards;
    }

    @Override
    public String toString(){
        String data = "\n\n{\n\tUser id: " + getId();
        data += "\n\tName: " + getName();
        data += "\n\tAddress: " + getAddress();
        data += "\n\tSalary: " + getSalary();
        data += "\n\tTime Cards: " + getTimeCards();
        data += "\n\tPayment Data: {" + getPaymentData();

        if(this.getEmployeeSyndicate().getIsAffiliated()){
            data += "\n\tSyndicate: { ";
            data += this.getEmployeeSyndicate().toString();
            data += "\n\t}";
        }

        data += "\n}";
        return data;
    }
}
