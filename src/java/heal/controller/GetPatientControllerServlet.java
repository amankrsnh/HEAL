/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heal.controller;

import heal.dao.AdminDao;
import heal.dao.RegistrationDao;
import heal.dto.Doctors_Schedule;
import heal.dto.Patients;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aman Kumar Singh
 */
public class GetPatientControllerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd=null;
        HttpSession sess=request.getSession();
        String userid=(String)sess.getAttribute("id");
        if(userid==null)
        {
            sess.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
        try
        {
            Patients p=RegistrationDao.getPatientsProfile(userid);
            System.out.println("userid: "+userid);
            sess.setAttribute("p",p);
            StringBuffer displayBlock=null;
            displayBlock=new StringBuffer("" +
"                    <h1>Your Profile</h1>\n" +
"                    <div class=\"form-row\">\n" +
"                        <div class=\"col\">\n" +
"                        <label for=\"name\">Name</label>\n" +
"                        <input type=\"text\" id=\"name\" name=\"name\" class=\"form-control\" placeholder=\"Enter Name\" value=\""+p.getpName()+"\" disabled>\n" +
"                        </div>\n" +
"                        <div class=\"col\">\n" +
"                            <label for=\"email\">Email</label>\n" +
"                            <input type=\"email\" id=\"email\" name=\"email\" class=\"form-control\" placeholder=\"Enter email\" value=\""+p.getpEmail()+"\" disabled>\n" +
"                        </div>\n" +
"                    </div>\n" +
"                    <div class=\"form-group\">\n" +
"                        <label for=\"password\">Password</label>\n" +
"                        <input type=\"password\" id=\"password\" name=\"password\" class=\"form-control\" placeholder=\"Password\" value=\""+p.getpPassword()+"\">\n" +
"                      </div>\n" +
"                    <div class=\"form-group\">\n" +
"                      <label for=\"address\">Address</label>\n" +
"                      <input type=\"text\" id=\"address\" name=\"address\" class=\"form-control\"  placeholder=\"Enter your Address\" value=\""+p.getpAddress()+"\">\n" +
"                    </div>\n" +
"                    <div class=\"form-group\">\n" +
"                      <label for=\"bg\">Blood Group</label>\n" +
"                      <input type=\"text\" id=\"bg\" name=\"bg\" class=\"form-control\"  placeholder=\"Enter your Blood Group\" value=\""+p.getpBg()+"\">\n" +
"                    </div>\n" +
"                    <div class=\"form-group\">\n" +
"                      <label for=\"dob\">Date Of Birth</label>\n" +
"                      <input type=\"text\" id=\"dob\" name=\"dob\" class=\"form-control\"  placeholder=\"Enter your Date of Birth\" value=\""+p.getpDob()+"\">\n" +
"                    </div>\n" +
"                    <div class=\"form-group\">\n" +
"                      <label for=\"phone\">Phone number</label>\n" +
"                      <input type=\"tel\" id=\"phone\" name=\"phone\" class=\"form-control\"  placeholder=\"Enter Phone number\" value=\""+p.getpPhone()+"\">\n" +
"                    </div>\n" +
"                    <div class=\"form-group\">\n" +
"                    <label for=\"gender\">Gender</label>"+
"                    <select id=\"gender\">\n"+
"                            <option value=\"male\">male</option>\n" +
"                            <option value=\"female\">female</option>\n" +
"                            <option value=\"other\">other</option>\n" +
"                    </select>\n"+
"                    </div>\n" +
"                    <input type=\"submit\" class=\"btn btn-primary\"/>\n");
            PrintWriter out=response.getWriter();
            out.println(displayBlock);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            System.out.println(ex);
            rd=request.getRequestDispatcher("showexception.jsp");
            request.setAttribute("Exception",ex);
        }
        finally
        {
            if(rd!=null) 
            {
        	rd.forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
