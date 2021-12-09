/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heal.controller;

import heal.dao.RegistrationDao;
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
public class UpdatePatientControllerSevlet extends HttpServlet {

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
            boolean result;
            System.out.println("bjbbjk");
            Patients p=new Patients();
            p.setpId(userid);
            p.setpPassword(request.getParameter("password"));
            p.setpAddress(request.getParameter("address"));
            p.setpBg(request.getParameter("bg"));
            p.setpPhone(request.getParameter("phone"));
            p.setpDob(request.getParameter("dob"));
            p.setpGender(request.getParameter("gender"));
            result=RegistrationDao.updatePatients(p);
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
