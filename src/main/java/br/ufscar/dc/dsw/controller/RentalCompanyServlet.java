package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.RentalCompanyDAO;
import br.ufscar.dc.dsw.dao.RentalCompanyDAOImpl;
import br.ufscar.dc.dsw.model.RentalCompany;

import java.io.FileWriter;

@WebServlet(urlPatterns = {"/home"})
public class RentalCompanyServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
        
    protected void processRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        listCompanies(request, response);
    }
            
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                    processRequest(request, response);
    }
            
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                    processRequest(request, response);
    }

    private void listCompanies(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RentalCompanyDAO companyDAO = new RentalCompanyDAOImpl();
        List<RentalCompany> rentalCompanies = companyDAO.getAll();
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AloMundo</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Alô Mundo</h1>");
            out.println(rentalCompanies.size());
            for (int i = 0; i < rentalCompanies.size(); i++) {
                out.println("<h1>Alô Mundo</h1>");
            }
            out.println("</body>");
            out.println("</html>");
        }
        request.setAttribute("companiesList", rentalCompanies);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/rentalCompanies.jsp");
        dispatcher.forward(request, response);
    }

}