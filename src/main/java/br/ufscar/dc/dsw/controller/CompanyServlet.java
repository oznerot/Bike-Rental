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
import br.ufscar.dc.dsw.dao.UserDAO;
import br.ufscar.dc.dsw.model.RentalCompany;
import br.ufscar.dc.dsw.model.User;

import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = {"/companies/*"})
public class CompanyServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    private RentalCompanyDAO dao;
    private UserDAO userDao;

    @Override
    public void init() throws ServletException
    {
        super.init();

        this.dao = new RentalCompanyDAO();
        this.userDao = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        User user = (User) request.getSession().getAttribute("userLogged");
        Erro erros = new Erro();
        String action = request.getPathInfo();

        if(action == null)
        {
            action = "";
        }

        if(action.equals("/list") || action.equals(""))
        {
            if(user != null && user.getRole().equals("ADMIN"))
            {
                adminList(request, response);
            }
            String city = request.getParameter("city");
            
            if(city == null || city.trim().isEmpty())
            {
                list(request, response);
            }
            else
            {
                listByCity(request, response);
            }

            return;
        }

        if(user == null)
        {
            response.sendRedirect(request.getContextPath());
            return;
        }
        else if(!user.getRole().equals("ADMIN"))
        {
            erros.add("Acesso não autorizado!");
            erros.add("Apenas Administradores têm acesso à essa página");
            request.setAttribute("mensagens", erros);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/noAuth.jsp");
            dispatcher.forward(request, response);

            return;
        }

        try
        {
            switch(action)
            {
                case "/register":
                    showFormRegister(request, response);
                    break;
                
                case "/add":
                    insert(request, response);
                    break;
                
                case "/delete":
                    remove(request, response);
                    break;
                
                case "/edit":
                    showFormEdit(request, response);
                    break;
                
                case "/update":
                    update(request, response);
                    break;
                
                default:
                    break;
                
            }
        }
        catch(RuntimeException | IOException | ServletException e)
        {
            e.printStackTrace();
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

    private void adminList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<RentalCompany> companies = dao.getAll();
        request.setAttribute("companies", companies);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/company/list.jsp");
        dispatcher.forward(request, response);
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<RentalCompany> companies = dao.getAll();
        request.setAttribute("companies", companies);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/company/list.jsp");
        dispatcher.forward(request, response);
    }

    private void listByCity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String city = request.getParameter("city");
        List<RentalCompany> companies = dao.getByCity(city);
        request.setAttribute("companies", companies);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/company/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showFormRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/company/forms.jsp");
        dispatcher.forward(request, response);
    }

    private void showFormEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int id = Integer.parseInt(request.getParameter("id"));

        RentalCompany company = dao.getById(id);
        request.setAttribute("company", company);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/company/forms.jsp");
        dispatcher.forward(request, response);
    }

    private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String cnpj = request.getParameter("cnpj");
        String city = request.getParameter("city");

        RentalCompany company = new RentalCompany(name, email, password, cnpj, city);
        Erro erro = new Erro();

        if(userDao.getByEmail(company.getEmail()) != null)
        {
            erro.add("O Email inserido já está em uso");
            request.setAttribute("mensagens", erro.getErros());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/company/forms.jsp");
            dispatcher.forward(request, response);
            return;
        }

        dao.insert(company);
        response.sendRedirect("list");
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String cnpj = request.getParameter("cnpj");
        String city = request.getParameter("city");

        RentalCompany company = new RentalCompany(id, name, email, password, cnpj, city);

        dao.update(company);
        response.sendRedirect("list");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int id = Integer.parseInt(request.getParameter("id"));

        RentalCompany company = dao.getById(id);

        dao.delete(company);

        response.sendRedirect("list");
    }
}