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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class UpdateDoctorControllerSevlet extends HttpServlet {

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
        System.out.println("UserId: "+userid);
        if(userid==null)
        {
            sess.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
        try
        {
            String profile=(String)request.getParameter("profile");
            boolean result;
            System.out.println("bjbk");
            if(profile.equalsIgnoreCase("true"))
            {
                Doctors d=new Doctors();
                d.setdId(userid);
                d.setdPassword(request.getParameter("password"));
                d.setdHospital(request.getParameter("hospital"));
                d.setdPhone(request.getParameter("phone"));
                d.setdSpecialisation(request.getParameter("specialisation"));
                result=AdminDao.updateDoctors(d);
                System.out.println("nkbk");
            }
            else
            {
                Doctors_Schedule ds1=(Doctors_Schedule)sess.getAttribute("ds");
                DateFormat formatter = new SimpleDateFormat("HH:mm");
                java.sql.Time stime = new java.sql.Time(formatter.parse((String)request.getParameter("stime")).getTime());
                java.sql.Time etime = new java.sql.Time(formatter.parse((String)request.getParameter("etime")).getTime());
                System.out.println(stime);
                System.out.println(etime);
                Doctors_Schedule ds=new Doctors_Schedule(userid,request.getParameter("available").toString(),
                        (String)request.getParameter("weekdays"),
                        stime,
                        etime,
                        Integer.parseInt(request.getParameter("fees")),
                        Integer.parseInt(request.getParameter("seats")));
                if(ds1==null)
                    result=AdminDao.setDoctorsSchedule(ds);
                else
                    result=AdminDao.updateDoctorsSchedule(ds);
                System.out.println("mkbj");
            }
            System.out.println("result: "+result);
            PrintWriter out=response.getWriter();
            if(result)
                out.println("success");
            else
                out.println("error"); 
            System.out.println("nnk");
        }
        catch(Exception ex)
        {
            rd=request.getRequestDispatcher("showexception.jsp");
            ex.printStackTrace();
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
