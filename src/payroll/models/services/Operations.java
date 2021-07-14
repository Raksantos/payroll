package models.services;

import java.time.LocalDate;

public class Operations {
    private Double value;

    private LocalDate date;

    public Operations(){

    }

    public Operations(Double value, LocalDate date){
        this.value = value;
        this.date = date;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
