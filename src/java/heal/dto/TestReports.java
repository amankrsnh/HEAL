package heal.dto;

import java.sql.Blob;
import java.sql.Timestamp;

public class TestReports {
    private String reportId;
    private String pId;
    private String dId;
    private String disease;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Blob reportFile;

    public TestReports(String reportId, String pId, String dId, String disease, Timestamp createdAt, Timestamp updatedAt, Blob reportFile) {
        this.reportId = reportId;
        this.pId = pId;
        this.dId = dId;
        this.disease = disease;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.reportFile = reportFile;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getdId() {
        return dId;
    }

    public void setdId(String dId) {
        this.dId = dId;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Blob getReportFile() {
        return reportFile;
    }

    public void setReportFile(Blob reportFile) {
        this.reportFile = reportFile;
    }
    
    
    
}
