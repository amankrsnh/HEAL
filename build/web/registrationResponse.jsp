<%-- 
    Document   : registrationResponse
    Created on : 11 Nov, 2021, 11:36:24 PM
    Author     : Aman Kumar Singh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    boolean result=(boolean)request.getAttribute("result");
    boolean userfound=(boolean)request.getAttribute("userFound");
    if(userfound)
        out.println("uap");
    else if(result)
        out.println("success");
    else
        out.println("error");
%>