package models.services.payment;

import java.util.ArrayList;

public class PaymentData{
    private String bank;

    private String agency;

    private String account;

    private String paymentMethod;

    private String schedule;

    private ArrayList<PayCheck> payChecks;

    PaymentData(){

    }

    
    public PaymentData(String bank, String agency, String account, String paymentMethod, String schedule){
        this.bank = bank;
        this.agency = agency;
        this.account = account;
        this.paymentMethod = paymentMethod;
        this.schedule = schedule;
        this.payChecks = new ArrayList<PayCheck>();
    }

    public String getAccount() {
        return account;
    }

    public String getAgency() {
        return agency;
    }

    public String getBank() {
        return bank;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public ArrayList<PayCheck> getPayChecks() {
        return payChecks;
    }

    public void setPayChecks(ArrayList<PayCheck> payChecks) {
        this.payChecks = payChecks;
    }

    @Override
    public String toString(){
        String data =  "\n\t\tBank: " + getBank();
        data += "\n\t\tAgency: " + getAgency();
        data += "\n\t\tAccount: " + getAccount();
        data +=  "\n\t\tPayment Method: " + getPaymentMethod();
        data += "\n\t\tSchedule: " + getSchedule();
        data += "\n\t}";

        return data;
    }
}