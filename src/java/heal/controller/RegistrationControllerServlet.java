/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heal.controller;

import heal.dao.RegistrationDao;
import heal.dto.Patients;
import java.io.IOException;
import javax.servlet.Registration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aman Kumar Singh
 */
public class RegistrationControllerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd=null;
        Patients p=new Patients();
        p.setpName(request.getParameter("username"));
        p.setpEmail(request.getParameter("email"));
        p.setpPassword(request.getParameter("password"));
        p.setpPhone(request.getParameter("mobile"));
        
        try
        {
            boolean userFound=false,result=false;
            if(RegistrationDao.isUserAvailable(p.getpEmail()))
            {
                userFound=true;
            }
            else
            {
                String id=RegistrationDao.getLastPid();
                p.setpId("p"+(Integer.parseInt(id)+1));
                RegistrationDao.addPatient(p);
                result=true;
            }
            request.setAttribute("result", result);
            request.setAttribute("userFound", userFound);
            rd=request.getRequestDispatcher("registrationResponse.jsp");
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            ex.printStackTrace();
            rd=request.getRequestDispatcher("ShowException.jsp");
        }
        finally
        {
            rd.forward(request, response);
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
