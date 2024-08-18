package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import java.util.List;

import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.BikeRentalDAO;
import br.ufscar.dc.dsw.dao.BikeRentalDAOImpl;
import br.ufscar.dc.dsw.model.BikeRental;

@WebServlet(urlPatterns = {"/rental"})
public class BikeRentalServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    BikeRentalDAO rentalDAO = new BikeRentalDAOImpl();

    protected void processRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        if(action == null)
        {
            action = "";
        }

        switch (action) {
            case "register":
                registerBikeRental(request, response);
                break;
            
            case "clientRentals":
                listClientRental(request, response);
                break;

            case "listCompanyRental":
                listCompanyRental(request, response);
                break;      

            case "add":
                addBikeRental(request, response);
                break;
        

            default:
                RequestDispatcher dispatcher = request.getRequestDispatcher("indexPage.jsp");
                dispatcher.forward(request, response); 
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

    private void registerBikeRental(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    private void listClientRental(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("ENTROU NA LISTA");
        String clientId = request.getParameter("clientId");
        System.out.println("MEU ID: " + clientId);

        List<BikeRental> rentals = rentalDAO.getClientRentalList(clientId);

        System.out.println("TAMANHO DA LISTA: " + rentals.size());

        request.setAttribute("rentalsList", rentals);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    private void listCompanyRental(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String companyId = request.getParameter("companyId");

        List<BikeRental> rentals = rentalDAO.getClientRentalList(companyId);

        request.setAttribute("rentalsList", rentals);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    private void addBikeRental(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/");
    }
}