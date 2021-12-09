package heal.dao;

import heal.dbutil.DBConnection;
import heal.dto.TestReports;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportsDao {
    private static PreparedStatement ps1,ps2;
    static{
        try
        {
            ps1=DBConnection.getConnection().prepareStatement("Select * from testreports where pid=?");
            ps2=DBConnection.getConnection().prepareStatement("Insert into testreports values(?,?,?,?,?,?,?)");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            System.out.println(ex);
        }
    }
    
    public static List<TestReports> setTestReports(String pid) throws SQLException
    {
        ps1.setString(1,pid);
        ResultSet rs=ps1.executeQuery();
        ArrayList<TestReports> trArr=new ArrayList<>();
        while(rs.next())
        {
            TestReports tr=new TestReports(rs.getString(7),rs.getString(2),rs.getString(1),rs.getString(3),rs.getTimestamp(5),rs.getTimestamp(6),rs.getBlob(4));
            trArr.add(tr);
        }
        return trArr;
    }
}
