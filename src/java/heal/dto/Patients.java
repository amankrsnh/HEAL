/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heal.dto;

import java.sql.Blob;

/**
 *
 * @author Aman Kumar Singh
 */
public class Patients {
    private String pId;
    private String pName;
    private String pEmail;
    private String pPassword;
    private String pDob;
    private String pBg;
    private String pPhone;
    private String pGender;
    private String pAddress;
    private Blob pImage;

    public Patients() {
    }

    public Patients(String pId, String pName, String pEmail, String pPassword, String pDob, String pBg, String pPhone, String pGender, String pAddress, Blob pImage) {
        this.pId = pId;
        this.pName = pName;
        this.pEmail = pEmail;
        this.pPassword = pPassword;
        this.pDob = pDob;
        this.pBg = pBg;
        this.pPhone = pPhone;
        this.pGender = pGender;
        this.pAddress = pAddress;
        this.pImage = pImage;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpEmail() {
        return pEmail;
    }

    public void setpEmail(String pEmail) {
        this.pEmail = pEmail;
    }

    public String getpPassword() {
        return pPassword;
    }

    public void setpPassword(String pPassword) {
        this.pPassword = pPassword;
    }

    public String getpDob() {
        return pDob;
    }

    public void setpDob(String pDob) {
        this.pDob = pDob;
    }

    public String getpBg() {
        return pBg;
    }

    public void setpBg(String pBg) {
        this.pBg = pBg;
    }

    public String getpPhone() {
        return pPhone;
    }

    public void setpPhone(String pPhone) {
        this.pPhone = pPhone;
    }

    public String getpGender() {
        return pGender;
    }

    public void setpGender(String pGender) {
        this.pGender = pGender;
    }

    public String getpAddress() {
        return pAddress;
    }

    public void setpAddress(String pAddress) {
        this.pAddress = pAddress;
    }

    public Blob getpImage() {
        return pImage;
    }

    public void setpImage(Blob pImage) {
        this.pImage = pImage;
    }
    
    
    
}
