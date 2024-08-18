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

@WebServlet(urlPatterns = {""})
public class HomeServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    RentalCompanyDAO companyDAO = new RentalCompanyDAOImpl();

    protected void processRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("HOMESERVLET ACESSED");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/company?action=list");
        dispatcher.forward(request, response);

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