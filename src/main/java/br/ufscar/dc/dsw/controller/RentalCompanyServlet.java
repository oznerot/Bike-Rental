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

    RentalCompanyDAO companyDAO = new RentalCompanyDAOImpl();

    protected void processRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");

        String cityName = request.getParameter("city");
        if(cityName != null && !cityName.trim().isEmpty())
        {
            listCompaniesByCity(request, response);
        }
        else
        {
            listCompanies(request, response);
        }
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
        List<RentalCompany> rentalCompanies = companyDAO.getAll();

        request.setAttribute("companiesList", rentalCompanies);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/homePage.jsp");
        dispatcher.forward(request, response);
    }

    private void listCompaniesByCity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String cityName = request.getParameter("city");

        List<RentalCompany> rentalCompanies = companyDAO.getByCity(cityName);

        request.setAttribute("companiesList", rentalCompanies);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/homePage.jsp");
        dispatcher.forward(request, response);
    }
}