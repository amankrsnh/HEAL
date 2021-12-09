/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heal.dto;

import java.sql.Time;

/**
 *
 * @author Aman Kumar Singh
 */
public class Doctors_Schedule {
    private String dId;
    private String dAvailable;
    private String dWeekDays;
    private Time startTime;
    private Time endTime;
    private int fees;
    private int seats;

    public Doctors_Schedule(String dId, String dAvailable, String dWeekDays, Time startTime, Time endTime,int fees,int seats) {
        this.dId = dId;
        this.dAvailable = dAvailable;
        this.dWeekDays = dWeekDays;
        this.startTime = startTime;
        this.endTime = endTime;
        this.fees=fees;
        this.seats=seats;
    }

    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getdId() {
        return dId;
    }

    public void setdId(String dId) {
        this.dId = dId;
    }

    public String getdAvailable() {
        return dAvailable;
    }

    public void setdAvailable(String dAvailable) {
        this.dAvailable = dAvailable;
    }

    public String getdWeekDays() {
        return dWeekDays;
    }

    public void setdWeekDays(String dWeekDays) {
        this.dWeekDays = dWeekDays;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
    
    
    
}
