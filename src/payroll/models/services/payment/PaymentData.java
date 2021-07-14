class PaymentData{
    private String bank;

    private String agency;

    private String account;

    private int paymentMethod;

    private String schedule;

    PaymentData(){

    }

    PaymentData(String bank, String agency, String account, int paymentMethod, String schedule){
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
        return "Payment data {" +
            "Bank: " + getBank() +
            "Agency: " + getAgency() +
            "Account: " + getAccount() +
            "Payment Method: " + getPaymentMethod() + '\'' +
            "Schedule: " + getSchedule() + '\'' +
            '}';
    }
}