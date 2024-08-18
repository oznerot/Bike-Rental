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

import br.ufscar.dc.dsw.dao.ClientDAO;
import br.ufscar.dc.dsw.dao.ClientDAOImpl;
import br.ufscar.dc.dsw.dao.RentalCompanyDAO;
import br.ufscar.dc.dsw.dao.RentalCompanyDAOImpl;
import br.ufscar.dc.dsw.model.Client;
import br.ufscar.dc.dsw.model.RentalCompany;

@WebServlet(urlPatterns = {"/client"})
public class ClientServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    ClientDAO clientDAO = new ClientDAOImpl();

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
                registerClient(request, response);
                break;

            case "list":
                listClients(request, response);
                break;

            
            case "delete":
                deleteClient(request, response);
                break;

            case "edit":
                editClient(request, response);
                break;
               

            case "add":
                addClient(request, response);
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

    private void registerClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String clientId = request.getParameter("id");
        Client client = new Client();
        boolean edit = false;

        // If a client ID is present, it means we're editing an existing client
        if (clientId != null && !clientId.isEmpty()) {
            client = clientDAO.get(clientId);
            edit = true;
        }
        // No client ID means we're adding a new client
        request.setAttribute("client", client);
        request.setAttribute("edit", edit);

        // Forward to the register page
        RequestDispatcher dispatcher = request.getRequestDispatcher("registerPage.jsp");
        dispatcher.forward(request, response);
    }

    private void listClients(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<Client> clients = clientDAO.getAll();

        request.setAttribute("clientsList", clients);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String uuid = request.getParameter("id");
        Client client = clientDAO.get(uuid);

        int result = clientDAO.delete(client);

        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/");
    }

    private void editClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String uuid = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String cpf = request.getParameter("cpf");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String dateOfBirth = request.getParameter("birthday");

        Client client = clientDAO.get(uuid);

        client.setName(name);
        client.setEmail(email);
        client.setPassword(password);
        client.setCpf(cpf);
        client.setPhone(phone);
        client.setGender(gender);
        client.setDateOfBirth(LocalDate.parse(dateOfBirth));

        int result = clientDAO.update(client);

        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/");
    }

    private void addClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String cpf = request.getParameter("cpf");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String dateOfBirth = request.getParameter("birthday");

        Client client = new Client(name, email, password, cpf, phone, gender, LocalDate.parse(dateOfBirth));

        int result = clientDAO.insert(client);

        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/");
    }
}