<%-- 
    Document   : showDoctorsList
    Created on : 21 Nov, 2021, 11:40:55 AM
    Author     : Aman Kumar Singh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="heal.dto.Doctors,java.util.ArrayList,java.io.IOException,java.io.InputStream,java.io.ByteArrayOutputStream,java.util.Base64"  %>
<%
    String userid=(String)session.getAttribute("id");
    ArrayList<Doctors> dArr=(ArrayList<Doctors>)request.getAttribute("doctors"); 
    StringBuffer sb=new StringBuffer();
    for(Doctors d:dArr)
    {
        sb.append("<tr class=\"gradeX\">"+
            "<td>"+d.getdName()+"</td>"+
            "<td>"+d.getdEmail()+"</td>"+
            "<td>"+d.getdGender()+"</td>"+
            "<td>"+d.getdHospital()+"</td>"+
            "<td>"+d.getdSpecialisation()+"</td>"+
            "<td>"+d.getdRating()+"</td>"+
            "</tr>");
    }
    out.println(sb);
%>