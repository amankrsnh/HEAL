package heal.dao;

import heal.dbutil.DBConnection;
import heal.dto.Doctors;
import heal.dto.Doctors_Schedule;
import heal.dto.Review;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

public class AdminDao {
    private static PreparedStatement ps1,ps2,ps3,ps4,ps5,ps6,ps7,ps8,ps9;
    static{
        try
        {
            ps1=DBConnection.getConnection().prepareStatement("Insert into review values(?,?,?,?,?)");
            ps2=DBConnection.getConnection().prepareStatement("Select * from review where did=?");
            ps3=DBConnection.getConnection().prepareStatement("Insert into doctors_schedule values(?,?,?,?,?,?,?)");
            ps4=DBConnection.getConnection().prepareStatement("Select * from doctors_schedule where did=?");
            ps5=DBConnection.getConnection().prepareStatement("Update doctors_schedule set davailable=? where did=?");
            ps6=DBConnection.getConnection().prepareStatement("Update doctors_schedule set davailable=?,dweekdays=?,start_time=?,end_time=?,fees=?,seats=? where did=?");
            ps7=DBConnection.getConnection().prepareStatement("Update doctors_schedule set seats=? where did=?");
            ps8=DBConnection.getConnection().prepareStatement("select * from doctors where did=?");
            ps9=DBConnection.getConnection().prepareStatement("Update doctors set dpassword=?,dphone=?,dhospital=?,dspecialisation=? where did=?");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            System.out.println(ex);
        }
    }
    
    public static boolean updateDoctors(Doctors d) throws SQLException
    {
        ps9.setString(1,d.getdPassword());
        ps9.setString(2,d.getdPhone());
        ps9.setString(3,d.getdHospital());
        ps9.setString(4,d.getdSpecialisation());
        ps9.setString(5, d.getdId());
        return ps9.executeUpdate()!=0;
    }
    
    public static Doctors getDoctorsProfile(String did) throws SQLException
    {
        ps8.setString(1,did);
        ResultSet rs=ps8.executeQuery();
        rs.next();
        Doctors d=new Doctors();
        d.setdEmail(rs.getString("demail"));
        d.setdName(rs.getString("dname"));
        d.setdHospital(rs.getString("dhospital"));
        d.setdImage(rs.getBlob("dimage"));
        d.setdPassword(rs.getString("dpassword"));
        d.setdRating(rs.getInt("drating"));
        d.setdPhone(rs.getString("dphone"));
        d.setdSpecialisation(rs.getString("dspecialisation"));
        d.setdId(rs.getString("did"));
        return d;
        
    }
    
    public static boolean setReview(Review r) throws SQLException
    {
        ps1.setString(1, r.getrId());
        ps1.setString(2, r.getpId());
        ps1.setString(3, r.getdId());
        ps1.setInt(4, r.getRating());
        ps1.setString(5, r.getMsg());
        return ps1.executeUpdate()!=0;
    }
    
    public static List<Review> getReview(String did) throws SQLException
    {
        ps2.setString(1, did);
        ResultSet rs=ps2.executeQuery();
        ArrayList<Review> reviewArr=new ArrayList<>();
        while(rs.next())
        {
            Review r=new Review();
            r.setrId(rs.getString(1));
            r.setpId(rs.getString(2));
            r.setdId(rs.getString(3));
            r.setRating(rs.getInt(4));
            r.setMsg(rs.getString(5));
            reviewArr.add(r);
        }
        return reviewArr;
    }
    
    public static boolean setDoctorsSchedule(Doctors_Schedule ds) throws SQLException
    {
        ps3.setString(1, ds.getdId());
        ps3.setString(2, ds.getdAvailable());
        ps3.setString(3,ds.getdWeekDays());
        ps3.setTime(4, ds.getStartTime());
        ps3.setTime(5, ds.getEndTime());
        ps3.setInt(6, ds.getFees());
        ps3.setInt(7, ds.getSeats());
        return ps3.executeUpdate()!=0;
    }
    
    public static Doctors_Schedule getDoctorsSchedule(String did) throws SQLException
    {
        ps4.setString(1, did);
        ResultSet rs=ps4.executeQuery();
        Doctors_Schedule r=null;
        if(rs.next())
            r=new Doctors_Schedule(rs.getString(1),rs.getString(2),rs.getString(3),rs.getTime(4),rs.getTime(5),rs.getInt(6),rs.getInt(7));
        return r;
    }
    
    public static boolean updateAvailabilty(String did,String availability) throws SQLException
    {
        ps5.setString(1, availability);
        ps5.setString(2, did);
        return ps5.executeUpdate()!=0;
    }
    
    public static boolean updateDoctorsSchedule(Doctors_Schedule ds) throws SQLException
    {
        ps6.setString(1, ds.getdAvailable());
        ps6.setString(2,ds.getdWeekDays());
        ps6.setTime(3, ds.getStartTime());
        ps6.setTime(4, ds.getEndTime());
        ps6.setInt(5, ds.getFees());
        ps6.setInt(6, ds.getSeats());
        ps6.setString(7, ds.getdId());
        return ps6.executeUpdate()!=0;
    }
    
    public static boolean updateSeats(int seats,String did) throws SQLException
    {
        ps7.setInt(1, seats);
        ps7.setString(2, did);
        return ps7.executeUpdate()!=0;
    }
}
