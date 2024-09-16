package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.BikeRentalDAO;
import br.ufscar.dc.dsw.dao.ClientDAO;
import br.ufscar.dc.dsw.dao.RentalCompanyDAO;
import br.ufscar.dc.dsw.dao.UserDAO;
import br.ufscar.dc.dsw.model.BikeRental;
import br.ufscar.dc.dsw.model.Client;
import br.ufscar.dc.dsw.model.RentalCompany;
import br.ufscar.dc.dsw.model.User;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = {"/rentals/*"})
public class RentalServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    private BikeRentalDAO dao;
    private ClientDAO clientDao;
    private RentalCompanyDAO companyDao;

    @Override
    public void init()
    {
        dao = new BikeRentalDAO();
        clientDao = new ClientDAO();
        companyDao = new RentalCompanyDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        User user = (User) request.getSession().getAttribute("userLogged");
        Erro erros = new Erro();

        if(user == null)
        {
            response.sendRedirect(request.getContextPath());
            return;
        }
        else if(!user.getRole().equals("CLIENT") && !user.getRole().equals("COMPANY"))
        {
            erros.add("Acesso não autorizado!");
            erros.add("Apenas Usuários cadastrados têm acesso à essa página");
            request.setAttribute("mensagens", erros);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/noAuth.jsp");
            dispatcher.forward(request, response);
        }


        String action = request.getPathInfo();

        if(action == null)
        {
            action = "";
        }

        try
        {
            switch(action)
            {
                case "/register":
                    System.out.println("Opaaaa acabei de cair no cadastro galerinha");
                    showFormRegister(request, response);
                    break;
                
                case "/add":
                    System.out.println("Aeee depois de sair do forms é pra eu cair aqui mesmo");
                    insert(request, response);
                    break;

                default:
                    list(request, response);
                    break;
            }
        }
        catch(RuntimeException | IOException | ServletException e)
        {
            throw new ServletException(e);
        }

    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        User user = (User) request.getSession().getAttribute("userLogged");
        List<BikeRental> rentals = null;

        if(user == null)
        {
            response.sendRedirect(request.getContextPath());
            return;
        }

        if((user.getRole().equals("CLIENT")))
        {
            System.out.print("TO CONECTADO COMO CLIENTEEE");
            Client client = clientDao.getById(user.getId());
            rentals = dao.getAllByClient(client);
        }
        else if((user.getRole().equals("COMPANY")))
        {
            RentalCompany company = companyDao.getById(user.getId());
            rentals = dao.getAllByCompany(company);
        }

        request.setAttribute("rentals", rentals);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rental/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showFormRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("Opaaaa to indo pro forms.jsp");
        List<RentalCompany> companies = companyDao.getAll();
        request.setAttribute("companies", companies);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rental/forms.jsp");
        dispatcher.forward(request, response);
    }

    private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        Erro erro = new Erro();

        User user = (User) request.getSession().getAttribute("userLogged");
        Client client = clientDao.getById(user.getId());

        int companyId = Integer.parseInt(request.getParameter("companyId"));
        RentalCompany company = companyDao.getById(companyId);

        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAA");
        LocalDateTime dateHour = LocalDateTime.parse(request.getParameter("dateHour"));
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBB");
        System.out.println(dateHour);
        BikeRental rental = new BikeRental(client, company, dateHour);
        System.out.println("SE EU CHEGUEI AQUI É PQ OS DAO TAO FUNCIONANDO");
        if(!dao.verifyRental(rental))
        {
            System.out.println("Xiiiii... nao era pra cair aqui então");
            erro.add("Horário já reservado.");
            request.setAttribute("mensagens", erro.getErros());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rental/forms.jsp");
            dispatcher.forward(request, response);
        }
        else
        {
            dao.insert(rental);
            response.sendRedirect("list");
        }
        
    }
}