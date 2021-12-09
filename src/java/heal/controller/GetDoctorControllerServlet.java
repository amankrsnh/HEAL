/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heal.controller;

import heal.dao.AdminDao;
import heal.dto.Doctors;
import heal.dto.Doctors_Schedule;
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
public class GetDoctorControllerServlet extends HttpServlet {

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
            Doctors d=AdminDao.getDoctorsProfile(userid);
            Doctors_Schedule ds=AdminDao.getDoctorsSchedule(userid);
            System.out.println("userid: "+userid);
            sess.setAttribute("ds",ds);
            StringBuffer displayBlock=null;
            if(ds!=null)
            {
                displayBlock=new StringBuffer("<form class=\"profiles mx-5\" action=\"javascript:void(0)\" onsubmit=\"updateDoctor()\">\n" +
"                    <h1>Your Profile</h1>\n" +
"                    <div class=\"form-row\">\n" +
"                        <div class=\"col\">\n" +
"                        <label for=\"name\">Name</label>\n" +
"                        <input type=\"text\" id=\"name\" name=\"name\" class=\"form-control\" placeholder=\"Enter Name\" value=\""+d.getdName()+"\" disabled>\n" +
"                        </div>\n" +
"                        <div class=\"col\">\n" +
"                            <label for=\"email\">Email</label>\n" +
"                            <input type=\"email\" id=\"email\" name=\"email\" class=\"form-control\" placeholder=\"Enter email\" value=\""+d.getdEmail()+"\" disabled>\n" +
"                        </div>\n" +
"                    </div>\n" +
"                    <div class=\"form-group\">\n" +
"                        <label for=\"password\">Password</label>\n" +
"                        <input type=\"password\" id=\"password\" name=\"password\" class=\"form-control\" placeholder=\"Password\" value=\""+d.getdPassword()+"\">\n" +
"                      </div>\n" +
"                    <div class=\"form-group\">\n" +
"                      <label for=\"address\">Hospital</label>\n" +
"                      <input type=\"text\" id=\"address\" name=\"address\" class=\"form-control\"  placeholder=\"Enter your Instiute\" value=\""+d.getdHospital()+"\">\n" +
"                    </div>\n" +
"                    <div class=\"form-group\">\n" +
"                      <label for=\"spe\">Specialization</label>\n" +
"                      <input type=\"text\" id=\"spe\" name=\"spe\" class=\"form-control\"  placeholder=\"Enter your specialization\" value=\""+d.getdSpecialisation()+"\">\n" +
"                    </div>\n" +
"                    <div class=\"form-group\">\n" +
"                      <label for=\"phone\">Phone number</label>\n" +
"                      <input type=\"tel\" id=\"phone\" name=\"phone\" class=\"form-control\"  placeholder=\"Enter Phone number\" value=\""+d.getdPhone()+"\">\n" +
"                    </div>\n" +
"                    <button type=\"submit\" class=\"btn btn-primary\">Update</button>\n" +
"                  </form>\n" +
"                 \n" +
"                 \n" +
"                 <form class=\"Schedules mx-5\" action=\"javascript:void(0)\" onsubmit=\"updateDoctorSchedule()\">\n" +
"                        <br>\n" +
"                        <br>\n" +
"                         <br>\n" +
"                        <br>\n" +
"                        <h1>Your Schedule</h1>\n" +
"                        <br>\n" +
"                    <div class=\"form-group\">\n" +
"                            <input type=\"checkbox\" id=\"available\" name=\"available\" value=\"Bike\">\n" +
"                            <label for=\"available\">Are you available</label>\n" +
"                    </div>\n" +
"                    <div class=\"form-group\">\n" +
"                        <label for=\"weekdays\">Working Week Days</label>\n" +
"                        <select id=\"weekdays\" data-placeholder=\"chose your working days\" multiple class=\"chosen-select\" name=\"weekdays\">\n" +
"                            <option value=\"\"></option>\n" +
"                            <option value=\"s\">Sunday</option>\n" +
"                            <option value=\"m\">Monday</option>\n" +
"                            <option value=\"t\">Tuesday</option>\n" +
"                            <option value=\"w\">Wednesday</option>\n" +
"                            <option value=\"r\">Thursday</option>\n" +
"                            <option value=\"f\">Friday</option>\n" +
"                            <option value=\"a\">Saturday</option>\n" +
"                        </select>\n" +
"                      </div>\n" +
"                    <div class=\"form-group\">\n" +
"                      <label for=\"stime\">Start time</label>\n" +
"                      <input type=\"text\" id=\"stime\" name=\"stime\" value=\""+ds.getStartTime().toString()+"\">\n" +
"                    </div>\n" +
"                    <div class=\"form-group\">\n" +
"                      <label for=\"etime\">End time</label>\n" +
"                      <input type=\"text\" id=\"etime\" name=\"etime\" value=\""+ds.getEndTime().toString()+"\">\n" +
"                    </div>\n" +
"                    <div class=\"form-group\">\n" +
"                      <label>Your Fees</label>\n" +
"                      <input type=\"number\" class=\"form-control\"  placeholder=\"Enter your fees\" value=\""+ds.getFees()+"\">\n" +
"                    </div>\n" +
"                    <div class=\"form-group\">\n" +
"                      <label>Seats</label>\n" +
"                      <input type=\"number\" class=\"form-control\"  placeholder=\"Enter Seats per day\" value=\""+ds.getSeats()+"\">\n" +
"                    </div>\n" +
"                    <button type=\"submit\" class=\"btn btn-primary\">Update</button>\n" +
"                    <br>\n" +
"                    <br>\n" +
"                    <br>\n" +
"                  </form>");
            }
            else
            {
                displayBlock=new StringBuffer("<form class=\"profiles mx-5\" action=\"javascript:void(0)\" onsubmit=\"updateDoctor()\">\n" +
"                    <h2>Your Profile</h2>\n" +
"                    <div class=\"form-row\">\n" +
"                        <div class=\"col\">\n" +
"                        <label for=\"name\">Name</label>\n" +
"                        <input type=\"text\" id=\"name\" name=\"name\" class=\"form-control\" placeholder=\"Enter Name\" value=\""+d.getdName()+"\" disabled>\n" +
"                        </div>\n" +
"                        <div class=\"col\">\n" +
"                            <label for=\"email\">Email</label>\n" +
"                            <input type=\"email\" id=\"email\" name=\"email\" class=\"form-control\" placeholder=\"Enter email\" value=\""+d.getdEmail()+"\" disabled>\n" +
"                        </div>\n" +
"                    </div>\n" +
"                    <div class=\"form-group\">\n" +
"                        <label for=\"password\">Password</label>\n" +
"                        <input type=\"password\" id=\"password\" name=\"password\" class=\"form-control\" placeholder=\"Password\" value=\""+d.getdPassword()+"\">\n" +
"                      </div>\n" +
"                    <div class=\"form-group\">\n" +
"                      <label for=\"address\">Hospital</label>\n" +
"                      <input type=\"text\" id=\"address\" name=\"address\" class=\"form-control\"  placeholder=\"Enter your Instiute\" value=\""+d.getdHospital()+"\">\n" +
"                    </div>\n" +
"                    <div class=\"form-group\">\n" +
"                      <label for=\"spe\">Specialization</label>\n" +
"                      <input type=\"text\" id=\"spe\" name=\"spe\" class=\"form-control\"  placeholder=\"Enter your specialization\" value=\""+d.getdSpecialisation()+"\">\n" +
"                    </div>\n" +
"                    <div class=\"form-group\">\n" +
"                      <label for=\"phone\">Phone number</label>\n" +
"                      <input type=\"tel\" id=\"phone\" name=\"phone\" class=\"form-control\"  placeholder=\"Enter Phone number\" value=\""+d.getdPhone()+"\">\n" +
"                    </div>\n" +
"                    <button type=\"submit\" class=\"btn btn-primary\">Update</button>\n" +
"                  </form>\n" +
"                 \n" +
"                 \n" +
"                 <form class=\"Schedules mx-5\" action=\"javascript:void(0)\" onsubmit=\"updateDoctorSchedule()\">\n" +
"                        <br>\n" +
"                        <br>\n" +
"                        <h2>Your Schedule</h2>\n" +
"                        <br>\n" +
"                    <div class=\"form-group\">\n" +
"                            <input type=\"checkbox\" id=\"available\" name=\"available\" value=\"Bike\">\n" +
"                            <label for=\"available\">Are you available</label>\n" +
"                    </div>\n" +
"                    <div class=\"form-group\">\n" +
"                        <label for=\"weekdays\">Working Week Days</label>\n" +
"                        <select id=\"weekdays\" data-placeholder=\"chose your working days\" multiple class=\"chosen-select\" name=\"weekdays\">\n" +
"                            <option value=\"\"></option>\n" +
"                            <option value=\"s\">Sunday</option>\n" +
"                            <option value=\"m\">Monday</option>\n" +
"                            <option value=\"t\">Tuesday</option>\n" +
"                            <option value=\"w\">Wednesday</option>\n" +
"                            <option value=\"r\">Thursday</option>\n" +
"                            <option value=\"f\">Friday</option>\n" +
"                            <option value=\"a\">Saturday</option>\n" +
"                        </select>\n" +
"                      </div>\n" +
"                    <div class=\"form-group\">\n" +
"                      <label for=\"stime\">Start time</label>\n" +
"                      <input type=\"text\" id=\"stime\" name=\"stime\">\n" +
"                    </div>\n" +
"                    <div class=\"form-group\">\n" +
"                      <label for=\"etime\">End time</label>\n" +
"                      <input type=\"text\" id=\"etime\" name=\"etime\">\n" +
"                    </div>\n" +
"                    <div class=\"form-group\">\n" +
"                      <label>Your Fees</label>\n" +
"                      <input type=\"number\" id=\"fees\" class=\"form-control\"  placeholder=\"Enter your fees\">\n" +
"                    </div>\n" +
"                    <div class=\"form-group\">\n" +
"                      <label>Seats</label>\n" +
"                      <input type=\"number\" id=\"seats\" class=\"form-control\"  placeholder=\"Enter Seats per day\">\n" +
"                    </div>\n" +
"                    <button type=\"submit\" class=\"btn btn-primary\">Update</button>\n" +
"                    <br>\n" +
"                    <br>\n" +
"                    <br>\n" +
"                  </form>");
            }
            System.out.println("n vsfv");
            PrintWriter out=response.getWriter();
            out.println(displayBlock);
            System.out.println(displayBlock);
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
