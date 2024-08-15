package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.RentalCompanyDAO;
import br.ufscar.dc.dsw.dao.RentalCompanyDAOImpl;
import br.ufscar.dc.dsw.model.RentalCompany;

@WebServlet(urlPatterns = {"/aaaaa"})
public class HomeServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
        
    protected void processRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException
    {
        response.setContentType("text/html;charset=UTF-8");
    }
            
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                    processRequest(request, response);
    }
            
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                    processRequest(request, response);
    }
}