package heal.dto;

public class Review {
    private String rId;
    private String pId;
    private String dId;
    private int rating;
    private String msg;

    public Review(String rId, String pId, String dId, int rating, String msg) {
        this.rId = rId;
        this.pId = pId;
        this.dId = dId;
        this.rating = rating;
        this.msg = msg;
    }

    public Review() {
    }

    
    
    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    
    
}
