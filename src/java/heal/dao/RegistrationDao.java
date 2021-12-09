/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heal.dao;

import heal.dbutil.DBConnection;
import heal.dto.Doctors;
import heal.dto.Patients;
import java.sql.Blob;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aman Kumar Singh
 */
public class RegistrationDao {
    private static PreparedStatement ps1,ps2,ps3,ps4,ps5,ps6,ps7,ps8,ps9,ps10,ps11,ps12,ps13,ps14,ps15,ps16,ps17,ps18,ps19,ps20;
    static{
        try
        {
            ps1=DBConnection.getConnection().prepareStatement("Select usertype from login where username=? and password=?");
            ps2=DBConnection.getConnection().prepareStatement("Select * from login where username=?");
            ps3=DBConnection.getConnection().prepareStatement("Insert into patients(pid,pname,pemail,ppassword,pphone) values(?,?,?,?,?)");
            ps4=DBConnection.getConnection().prepareStatement("Insert into doctors(did,dname,demail,dpassword) values(?,?,?,?)");
            ps5=DBConnection.getConnection().prepareStatement("Update doctors set ddob=?,dphone=?,dgender=?,dhospital=?,dspecification=?,drating=?,dimage=? where did=?");
            ps6=DBConnection.getConnection().prepareStatement("Update patients set pdob=?,pblood_group=?,pphone=?,pgender=?,paddress=?,pimage=? where pid=?");
            ps7=DBConnection.getConnection().prepareStatement("Delete from doctors where did=?");
            ps8=DBConnection.getConnection().prepareStatement("Delete from patients where pid=?");
            ps9=DBConnection.getConnection().prepareStatement("Select pid from patients where pemail=?");
            ps10=DBConnection.getConnection().prepareStatement("Select did from doctors where demail=?");
            ps13=DBConnection.getConnection().prepareStatement("Select aid from admin where aemail=?");
            ps11=DBConnection.getConnection().prepareStatement("select CAST(REPLACE(pid,'p','') AS UNSIGNED) as num from patients p order by num desc limit 1");
            ps12=DBConnection.getConnection().prepareStatement("select CAST(REPLACE(did,'d','') AS UNSIGNED) as num from doctors d order by num desc limit 1");
            ps14=DBConnection.getConnection().prepareStatement("select dname,dgender,dhospital,dspecialisation,drating,dimage,demail from doctors");
            ps16=DBConnection.getConnection().prepareStatement("select * from patients");
            ps15=DBConnection.getConnection().prepareStatement("select p.pname,p.pphone,p.pblood_group,p.pgender,p.pdob,p.paddress,p.pimage from patients p join appointment a on a.pid=p.pid where a.did=?");
            ps17=DBConnection.getConnection().prepareStatement("select * from patients where pid=?");
            ps18=DBConnection.getConnection().prepareStatement("Update patients set ppassword=?,pphone=?,paddress=?,pblood_group=?,pdob=?,pgender=? where pid=?");
            ps19=DBConnection.getConnection().prepareStatement("Select dimage from doctors where did=?");
            ps20=DBConnection.getConnection().prepareStatement("Select pimage from patients where pid=?");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            System.out.println(ex);
        }
    }
    
    public static Blob getPatientsImage(String pid) throws SQLException
    {
        ps20.setString(1, pid);
        ResultSet rs=ps20.executeQuery();
        if(rs.next())
            return rs.getBlob(1);
        return null;
    }
    
    public static Blob getDoctorsImage(String did) throws SQLException
    {
        ps19.setString(1, did);
        ResultSet rs=ps19.executeQuery();
        if(rs.next())
            return rs.getBlob(1);
        return null;
    }
    
    public static boolean updatePatients(Patients p) throws SQLException
    {
        ps18.setString(1,p.getpPassword());
        ps18.setString(2,p.getpPhone());
        ps18.setString(3,p.getpAddress());
        ps18.setString(4,p.getpBg());
        ps18.setDate(5,Date.valueOf(p.getpDob()));
        ps18.setString(6,p.getpGender());
        ps18.setString(7,p.getpId());
        return ps18.executeUpdate()!=0;
    }
    
    public static Patients getPatientsProfile(String pid) throws SQLException
    {
        ps17.setString(1,pid);
        ResultSet rs=ps17.executeQuery();
        rs.next();
        Patients p=new Patients();
        p.setpEmail(rs.getString("pemail"));
        p.setpName(rs.getString("pname"));
        p.setpImage(rs.getBlob("pimage"));
        p.setpPassword(rs.getString("ppassword"));
        p.setpAddress(rs.getString("paddress"));
        p.setpPhone(rs.getString("pphone"));
        p.setpId(rs.getString("pid"));
        p.setpGender(rs.getString("pgender"));
        p.setpDob(rs.getDate("pdob").toString());
        p.setpBg(rs.getString("pblood_group"));
        
        return p;
        
    }
    
    public static List<Patients> getPatients() throws SQLException
    {
        ResultSet rs=ps16.executeQuery();
        ArrayList<Patients> pArr=new ArrayList<>();
        while(rs.next())
        {
            Patients p=new Patients();
            p.setpId(rs.getString(1));
            p.setpName(rs.getString(2));
            p.setpEmail(rs.getString(3));
            p.setpPhone(rs.getString(7));
            p.setpBg(rs.getString(6));
            p.setpGender(rs.getString(8));
            p.setpDob(rs.getString(5));
            p.setpAddress(rs.getString(9));
            p.setpImage(rs.getBlob(10));
            pArr.add(p);
        }
        return pArr;
    }
    
    public static List<Patients> getPatientsOfDid(String did) throws SQLException
    {
        ps15.setString(1,did);
        ResultSet rs=ps15.executeQuery();
        ArrayList<Patients> pArr=new ArrayList<>();
        while(rs.next())
        {
            Patients p=new Patients();
            p.setpName(rs.getString(1));
            p.setpPhone(rs.getString(2));
            p.setpBg(rs.getString(3));
            p.setpGender(rs.getString(4));
            p.setpDob(rs.getString(5));
            p.setpAddress(rs.getString(6));
            p.setpImage(rs.getBlob(7));
            pArr.add(p);
        }
        return pArr;
    }
    
    public static List<Doctors> getDoctors() throws SQLException
    {
        ResultSet rs=ps14.executeQuery();
        ArrayList<Doctors> dArr=new ArrayList<>();
        while(rs.next())
        {
            Doctors d=new Doctors();
            d.setdName(rs.getString(1));
            d.setdEmail(rs.getString(7));
            d.setdGender(rs.getString(2));
            d.setdHospital(rs.getString(3));
            d.setdSpecialisation(rs.getString(4));
            d.setdRating(rs.getInt(5));
            d.setdImage(rs.getBlob(6));
            dArr.add(d);
        }
        return dArr;
    }
    
    public static String getLastPid() throws SQLException
    {
        ResultSet rs=ps11.executeQuery();
        rs.next();
        return rs.getString(1);
    }
    
    public static String getLastDid() throws SQLException
    {
        ResultSet rs=ps12.executeQuery();
        rs.next();
        return rs.getString(1);
    }
    
    public static String getUsertype(String username,String password) throws SQLException
    {
        ps1.setString(1, username);
        ps1.setString(2, password);
        ResultSet rs=ps1.executeQuery();
        if(rs.next())
            return rs.getString(1);
        System.out.println("bhkvhkbkb");
        return null;
    }
    
    public static boolean isUserAvailable(String username) throws SQLException
    {
        ps2.setString(1, username);
        ResultSet rs=ps2.executeQuery();
        return rs.next();
    }
    
    public static boolean addPatient(Patients p) throws SQLException
    {
        ps3.setString(1, p.getpId());
        ps3.setString(2, p.getpName());
        ps3.setString(3, p.getpEmail());
        ps3.setString(4, p.getpPassword());
        ps3.setString(5, p.getpPhone());
        return ps3.executeUpdate()!=0;
    }
    
    public static boolean updateDoctorsProfile(Doctors d) throws SQLException
    {
        ps5.setString(1, d.getdDob());
        ps5.setString(2, d.getdPhone());
        ps5.setString(3, d.getdGender());
        ps5.setString(4, d.getdHospital());
        ps5.setString(5, d.getdSpecialisation());
        ps5.setInt(6, d.getdRating());
        ps5.setBlob(7, d.getdImage());
        ps5.setString(8, d.getdId());
        return ps5.executeUpdate()!=0;
    }
    
    public static boolean updatePatientsProfile(Patients p) throws SQLException
    {
        ps6.setString(1, p.getpDob());
        ps6.setString(2, p.getpBg());
        ps6.setString(3, p.getpPhone());
        ps6.setString(4, p.getpGender());
        ps6.setString(5, p.getpAddress());
        ps6.setBlob(6, p.getpImage());
        ps6.setString(7, p.getpId());
        return ps6.executeUpdate()!=0;
    }
    
    public static boolean deleteDoctor(Doctors d) throws SQLException
    {
        ps7.setString(1, d.getdId());
        return ps7.executeUpdate()!=0;
    }
    
    public static boolean deletePatient(Patients p) throws SQLException
    {
        ps8.setString(1, p.getpId());
        return ps8.executeUpdate()!=0;
    }
    
    public static String getPid(String username) throws SQLException
    {
        ps9.setString(1, username);
        ResultSet rs=ps9.executeQuery();
        if(rs.next())
            return rs.getString(1);
        return null;
    }
    
    public static String getDid(String username) throws SQLException
    {
        ps10.setString(1, username);
        ResultSet rs=ps10.executeQuery();
        if(rs.next())
            return rs.getString(1);
        return null;
    }
    
    public static String getAid(String username) throws SQLException
    {
        ps13.setString(1, username);
        ResultSet rs=ps13.executeQuery();
        if(rs.next())
            return rs.getString(1);
        return null;
    }
}
