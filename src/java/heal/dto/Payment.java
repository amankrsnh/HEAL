/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heal.dto;

/**
 *
 * @author Aman Kumar Singh
 */
public class Payment {
    private String paymentId;
    private String pId;
    private String dId;
    private int paymentAmount;
    private String paymentStatus;

    public Payment(String paymentId, String pId,String dId, int paymentAmount, String paymentStatus) {
        this.paymentId = paymentId;
        this.pId = pId;
        this.dId=dId;
        this.paymentAmount = paymentAmount;
        this.paymentStatus = paymentStatus;
    }

    public String getdId() {
        return dId;
    }

    public void setdId(String dId) {
        this.dId = dId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
    
    
}
