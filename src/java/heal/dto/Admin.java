package heal.dto;

public class Admin {
    private String aId;
    private String aName;
    private String aEmail;
    private String aPassword;

    public Admin(String aId, String aName, String aEmail, String aPassword) {
        this.aId = aId;
        this.aName = aName;
        this.aEmail = aEmail;
        this.aPassword = aPassword;
    }

    public String getaId() {
        return aId;
    }

    public void setaId(String aId) {
        this.aId = aId;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public String getaEmail() {
        return aEmail;
    }

    public void setaEmail(String aEmail) {
        this.aEmail = aEmail;
    }

    public String getaPassword() {
        return aPassword;
    }

    public void setaPassword(String aPassword) {
        this.aPassword = aPassword;
    }
    
    
}
