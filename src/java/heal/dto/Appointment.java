
package heal.dto;

import java.sql.Time;
import java.sql.Date;

public class Appointment {
    private String dId;
    private String pId;
    private String appoinmentId;
    private String paymentId;
    private Date date_of_appoinment;
    private Time time_of_appoinment;
    private String status;
    private String hospital;

    public Appointment(String dId, String pId, String appoinmentId, String paymentId, Date date_of_appoinment, Time time_of_appoinment, String status) {
        this.dId = dId;
        this.pId = pId;
        this.appoinmentId = appoinmentId;
        this.paymentId = paymentId;
        this.date_of_appoinment = date_of_appoinment;
        this.time_of_appoinment = time_of_appoinment;
        this.status = status;
    }

    public Appointment(String dId, String pId, String appoinmentId, String paymentId, Date date_of_appoinment, Time time_of_appoinment, String status, String hospital) {
        this.dId = dId;
        this.pId = pId;
        this.appoinmentId = appoinmentId;
        this.paymentId = paymentId;
        this.date_of_appoinment = date_of_appoinment;
        this.time_of_appoinment = time_of_appoinment;
        this.status = status;
        this.hospital = hospital;
    }
    
    public String getPaymentId() {
        return paymentId;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getdId() {
        return dId;
    }

    public void setdId(String dId) {
        this.dId = dId;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getAppoinmentId() {
        return appoinmentId;
    }

    public void setAppoinmentId(String appoinmentId) {
        this.appoinmentId = appoinmentId;
    }

    public Date getDate_of_appoinment() {
        return date_of_appoinment;
    }

    public void setDate_of_appoinment(Date date_of_appoinment) {
        this.date_of_appoinment = date_of_appoinment;
    }

    public Time getTime_of_appoinment() {
        return time_of_appoinment;
    }

    public void setTime_of_appoinment(Time time_of_appoinment) {
        this.time_of_appoinment = time_of_appoinment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    
}
