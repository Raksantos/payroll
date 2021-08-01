package models.services;

import java.io.Serializable;
import java.time.LocalDate;

public class Operations implements Serializable{
    private Double value;

    private LocalDate date;

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
