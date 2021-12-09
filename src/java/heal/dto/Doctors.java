/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heal.dto;

import java.sql.Blob;


public class Doctors {
    private String dId;
    private String dName;
    private String dEmail;
    private String dPassword;
    private String dDob;
    private String dPhone;
    private String dGender;
    private String dHospital;
    private String dSpecialisation;
    private int dRating;
    private Blob dImage;

    public Doctors() {
    }
    
    public Doctors(String dId, String dName, String dEmail, String dPassword, String dDob, String dPhone, String dGender, String dHospital, String dSpecialisation, int dRating, Blob dImage) {
        this.dId = dId;
        this.dName = dName;
        this.dEmail = dEmail;
        this.dPassword = dPassword;
        this.dDob = dDob;
        this.dPhone = dPhone;
        this.dGender = dGender;
        this.dHospital = dHospital;
        this.dSpecialisation = dSpecialisation;
        this.dRating = dRating;
        this.dImage = dImage;
    }

    public String getdId() {
        return dId;
    }

    public void setdId(String dId) {
        this.dId = dId;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getdEmail() {
        return dEmail;
    }

    public void setdEmail(String dEmail) {
        this.dEmail = dEmail;
    }

    public String getdPassword() {
        return dPassword;
    }

    public void setdPassword(String dPassword) {
        this.dPassword = dPassword;
    }

    public String getdDob() {
        return dDob;
    }

    public void setdDob(String dDob) {
        this.dDob = dDob;
    }

    public String getdPhone() {
        return dPhone;
    }

    public void setdPhone(String dPhone) {
        this.dPhone = dPhone;
    }

    public String getdGender() {
        return dGender;
    }

    public void setdGender(String dGender) {
        this.dGender = dGender;
    }

    public String getdHospital() {
        return dHospital;
    }

    public void setdHospital(String dHospital) {
        this.dHospital = dHospital;
    }

    public String getdSpecialisation() {
        return dSpecialisation;
    }

    public void setdSpecialisation(String dSpecialisation) {
        this.dSpecialisation = dSpecialisation;
    }

    public int getdRating() {
        return dRating;
    }

    public void setdRating(int dRating) {
        this.dRating = dRating;
    }

    public Blob getdImage() {
        return dImage;
    }

    public void setdImage(Blob dImage) {
        this.dImage = dImage;
    }
    
    
    
}
