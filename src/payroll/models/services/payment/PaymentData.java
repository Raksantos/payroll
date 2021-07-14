package models.services.payment;

public class PaymentData{
    private String bank;

    private String agency;

    private String account;

    private int paymentMethod;

    private String schedule;

    PaymentData(){

    }

    public PaymentData(String bank, String agency, String account, int paymentMethod, String schedule){
        this.bank = bank;
        this.agency = agency;
        this.account = account;
        this.paymentMethod = paymentMethod;
        this.schedule = schedule;
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

    public int getPaymentMethod() {
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

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
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