<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String usertype=(String)request.getAttribute("usertype");
    String userid=(String)request.getAttribute("id");
    if(userid!=null && usertype!=null)
    {
        HttpSession sess=request.getSession();
        sess.setAttribute("id",userid);
        sess.setAttribute("usertype",usertype);
        if(usertype.equalsIgnoreCase("admin"))
        {
            String url="AdminControllerServlet;jsessionid="+session.getId();
            out.println(url);
        }
        else if(usertype.equalsIgnoreCase("doctor"))
        {
            String url="DoctorControllerServlet;jsessionid="+session.getId();
            out.println(url);
        }
        else if(usertype.equalsIgnoreCase("patient"))
        {
            String url="PatientControllerServlet;jsessionid="+session.getId();
            out.println(url);
        }
        else
            out.println("error");
    }
%>