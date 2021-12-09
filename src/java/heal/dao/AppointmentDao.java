package heal.dao;

import heal.dbutil.DBConnection;
import heal.dto.Appointment;
import heal.dto.Payment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDao {
    private static PreparedStatement ps1,ps2,ps3,ps4,ps5,ps6;
    static{
        try
        {
            ps1=DBConnection.getConnection().prepareStatement("Insert into payment values(?,?,?,?,?)");
            ps2=DBConnection.getConnection().prepareStatement("Select * from payment where pid=?");
            ps3=DBConnection.getConnection().prepareStatement("Insert into appointment values(?,?,?,?,?,?,?)");
            ps4=DBConnection.getConnection().prepareStatement("Select a.*,d.dhospital from appointment a join doctors d on a.did=d.did where a.pid=?");
            ps5=DBConnection.getConnection().prepareStatement("Select * from appointment where did=?");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            System.out.println(ex);
        }
    }
    
    public static boolean setPayment(Payment p) throws SQLException
    {
        ps1.setString(1, p.getPaymentId());
        ps1.setInt(2, p.getPaymentAmount());
        ps1.setString(3, p.getpId());
        ps1.setString(4, p.getPaymentStatus());
        ps1.setString(5, p.getdId());
        return ps1.executeUpdate()!=0;
    }
    
    public static List<Payment> getPayment(String pid) throws SQLException
    {
        ps2.setString(1,pid);
        ResultSet rs=ps2.executeQuery();
        ArrayList<Payment> pArr=new ArrayList<>();
        while(rs.next())
        {
            Payment p=new Payment(rs.getString(1),rs.getString(3),rs.getString(5),rs.getInt(2),rs.getString(4));
            pArr.add(p);
        }
        return pArr;
    }
    
    public static boolean setAppointment(Appointment a) throws SQLException
    {
        ps3.setString(1, a.getdId());
        ps3.setString(2, a.getpId());
        ps3.setString(3, a.getAppoinmentId());
        ps3.setDate(4, a.getDate_of_appoinment());
        ps3.setTime(5, a.getTime_of_appoinment());
        ps3.setString(6, a.getPaymentId());
        ps3.setString(7, a.getStatus());
        return ps3.executeUpdate()!=0;
    }
    
    public static ArrayList<Appointment> getAppointmentByPid(String pid) throws SQLException
    {
        ps4.setString(1,pid);
        ResultSet rs=ps4.executeQuery();
        ArrayList<Appointment> aArr=new ArrayList<>();
        while(rs.next())
        {
            Appointment a=new Appointment(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(6),rs.getDate(4),rs.getTime(5),rs.getString(7),rs.getString(8));
            aArr.add(a);
        }
        return aArr;
    }
    
    public static ArrayList<Appointment> getAppointmentByDid(String did) throws SQLException
    {
        ps5.setString(1,did);
        ResultSet rs=ps5.executeQuery();
        ArrayList<Appointment> aArr=new ArrayList<>();
        while(rs.next())
        {
            Appointment a=new Appointment(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(6),rs.getDate(4),rs.getTime(5),rs.getString(7));
            aArr.add(a);
        }
        return aArr;
    }
}
