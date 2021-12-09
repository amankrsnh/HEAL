/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heal.controller;

import heal.dao.RegistrationDao;
import heal.dto.Login;
import java.io.IOException;
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
public class LoginControllerServlet extends HttpServlet {

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
        Login l=new Login();
        l.setUsername(request.getParameter("email"));
        l.setPassword(request.getParameter("password"));
        String logout=request.getParameter("logout");
        if(logout!=null && logout.equalsIgnoreCase("logout")) 
        {
        	HttpSession sess=request.getSession(false);
        	if(sess!=null) {
        		sess.invalidate();
        		sess=null;
        	}
        	request.getRequestDispatcher("index.html").forward(request, response);;
        	return;
        }
        try
        {
            String result=RegistrationDao.getUsertype(l.getUsername(),l.getPassword());
            String id=null;
            if(result!=null)
            {
                if(result.equalsIgnoreCase("doctor"))
                    id=RegistrationDao.getDid(l.getUsername());
                else if(result.equalsIgnoreCase("patient"))
                    id=RegistrationDao.getPid(l.getUsername());
                else
                    id=RegistrationDao.getAid(l.getUsername());
            }
            request.setAttribute("usertype", result);
            request.setAttribute("id", id);
            rd=request.getRequestDispatcher("LoginResponse.jsp");
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            request.setAttribute("exception", ex);
            rd=request.getRequestDispatcher("showexception.jsp");
        }
        finally
        {
            rd.forward(request,response);
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
