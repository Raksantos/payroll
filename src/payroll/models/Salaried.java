package models;

import java.io.Serializable;

import models.services.payment.PaymentData;

public class Salaried extends Employee implements Serializable{
    
    public Salaried(){

    }

    public Salaried(String name, String address, Double salary, PaymentData paymentData){
        super(name, address, salary, paymentData);
    }
}
