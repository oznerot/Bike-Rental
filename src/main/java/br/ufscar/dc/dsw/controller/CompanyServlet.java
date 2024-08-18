package br.ufscar.dc.dsw.controller;

import java.io.IOException;

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

@WebServlet(urlPatterns = {"/company"})
public class CompanyServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    RentalCompanyDAO companyDAO = new RentalCompanyDAOImpl();

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
                registerCompany(request, response);
                break;

            case "list":
                listCompanies(request, response);
                break;

            case "listByCity":
                String cityName = request.getParameter("city");
                if(cityName != null && !cityName.trim().isEmpty())
                {
                    listCompaniesByCity(request, response);
                }
                else
                {
                    listCompanies(request, response);
                }
                break;
            
            case "delete":
                deleteCompany(request, response);
                break;

            case "edit":
                editCompany(request, response);
                break;
               

            case "add":
                addCompany(request, response);
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

    private void registerCompany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String companyId = request.getParameter("id");
        RentalCompany company = new RentalCompany();
        boolean edit = false;

        // If a client ID is present, it means we're editing an existing client
        if (companyId != null && !companyId.isEmpty()) {
            company = companyDAO.get(companyId);
            edit = true;
        }
        // No client ID means we're adding a new client
        request.setAttribute("company", company);
        request.setAttribute("edit", edit);

        // Forward to the register page
        RequestDispatcher dispatcher = request.getRequestDispatcher("registerPage.jsp");
        dispatcher.forward(request, response);
    }

    private void listCompanies(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<RentalCompany> companies = companyDAO.getAll();

        request.setAttribute("companiesList", companies);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    private void listCompaniesByCity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String cityName = request.getParameter("city");

        List<RentalCompany> companies = companyDAO.getByCity(cityName);

        request.setAttribute("companiesList", companies);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteCompany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {   
        String uuid = request.getParameter("id");
        RentalCompany company = companyDAO.get(uuid);

        int result = companyDAO.delete(company);
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/");
    }

    private void editCompany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String uuid = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String cnpj = request.getParameter("cnpj");
        String city = request.getParameter("city");

        RentalCompany company = companyDAO.get(uuid);

        System.out.println("UUID: " + uuid);
        System.out.println("Nome:" + name);
        System.out.println("Email:" + email);
        System.out.println("Senha:" + password);
        System.out.println("CNPJ: " + cnpj);
        System.out.println("Cidade: " + city);

        company.setName(name);
        company.setEmail(email);
        company.setPassword(password);
        company.setCnpj(cnpj);
        company.setCity(city);

        int result = companyDAO.update(company);

        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/");
    }

    private void addCompany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String cnpj = request.getParameter("cnpj");
        String city = request.getParameter("city");

        RentalCompany company = new RentalCompany(name, email, password, cnpj, city);

        int result = companyDAO.insert(company);

        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/");
    }
}