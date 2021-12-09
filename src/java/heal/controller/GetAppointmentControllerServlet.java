/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heal.controller;

import heal.dao.AppointmentDao;
import heal.dto.Appointment;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class GetAppointmentControllerServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher rd=null;
        HttpSession sess=request.getSession();
        String userid=(String)sess.getAttribute("id");
        String usertype=(String)sess.getAttribute("usertype");
        if(userid==null)
        {
            sess.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
        try (PrintWriter out = response.getWriter()) {
            ArrayList<Appointment> apt=null;
            if(usertype.equalsIgnoreCase("doctor"))
                apt=AppointmentDao.getAppointmentByDid(userid);
            else if(usertype.equalsIgnoreCase("patient"))
                apt=AppointmentDao.getAppointmentByPid(userid);
            System.out.println("userid: "+userid);
            StringBuffer displayBlock=new StringBuffer("<h1>Appointments</h1><table id=\"didappointments\" class=\"appoints mx-5 table table-striped table-bordered table-sm\" cellspacing=\"0\" width=\"100%\">\n" +
            "  <thead>\n" +
            "    <tr>\n" +
            "      <th class=\"th-sm\">Pid\n" +
            "\n" +
            "      </th>\n" +
            "      <th class=\"th-sm\">AppointmentId\n" +
            "\n" +
            "      </th>\n" +
            "      <th class=\"th-sm\">Date Of Appointment\n" +
            "\n" +
            "      </th>\n" +
            "      <th class=\"th-sm\">Time Of Appointment\n" +
            "\n" +
            "      </th>\n" +
            "      <th class=\"th-sm\">PaymentId\n" +
            "\n" +
            "      </th>\n" +
            "      <th class=\"th-sm\">Status\n" +
            "\n" +
            "      </th>\n" +
            "    </tr>\n" +
            "  </thead>\n" +
            "  <tbody>\n");
           if(apt!=null)
           {
               for(Appointment a:apt)
                {
                     displayBlock.append("\n"+
                     "    <tr>\n" +
                     "      <td>"+a.getpId()+"</td>\n" +
                     "      <td>"+a.getAppoinmentId()+"</td>\n" +
                     "      <td>"+a.getDate_of_appoinment()+"</td>\n" +
                     "      <td>"+a.getTime_of_appoinment()+"</td>\n" +
                     "      <td>"+a.getPaymentId()+"</td>\n" +
                     "      <td>"+a.getStatus()+"</td>\n" +
                     "    </tr>\n");
                }
           }
           displayBlock.append(""+
            "  </tbody>\n"+
            "  </table>");
            out.println(displayBlock);
        }
        catch(Exception ex)
        {
            System.out.println(ex);
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
