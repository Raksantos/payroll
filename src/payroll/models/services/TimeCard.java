package models.services;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class TimeCard implements Serializable{
    private LocalDate date;

    private LocalTime timeEntry;

    private LocalTime timeOut;

    public TimeCard(){
        
    }

    public TimeCard(LocalDate date, LocalTime timeEntry, LocalTime timeOut){
        this.date = date;
        this.timeEntry = timeEntry;
        this.timeOut = timeOut;
    }

    public LocalDate getDate() {
        return date;
    }
    
    public LocalTime getTimeEntry() {
        return timeEntry;
    }

    public LocalTime getTimeOut() {
        return timeOut;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTimeEntry(LocalTime timeEntry) {
        this.timeEntry = timeEntry;
    }

    public void setTimeOut(LocalTime timeOut) {
        this.timeOut = timeOut;
    }
}
