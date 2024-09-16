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
import br.ufscar.dc.dsw.dao.UserDAO;
import br.ufscar.dc.dsw.model.Client;
import br.ufscar.dc.dsw.model.User;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = {"/clients/*"})
public class ClientServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    ClientDAO dao;
    UserDAO userDao;

    @Override
    public void init() throws ServletException
    {
        super.init();

        this.dao = new ClientDAO();
        this.userDao = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        else if(!user.getRole().equals("ADMIN"))
        {
            erros.add("Acesso não autorizado!");
            erros.add("Apenas Administradores têm acesso a essa página");
            request.setAttribute("mensagens", erros);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/noAuth.jsp");
            dispatcher.forward(request, response);
            return;
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
                    System.out.println("CAIU AQUI NA LISTAGEM");
                    list(request, response);
                    break;
                
            }
        }
        catch(RuntimeException | IOException | ServletException e)
        {
            e.printStackTrace();
        }
    }
            
    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<Client> clients = dao.getAll();
        request.setAttribute("clients", clients);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/client/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showFormRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/client/forms.jsp");
        dispatcher.forward(request, response);
    }

    private void showFormEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int id = Integer.parseInt(request.getParameter("id"));

        Client client = dao.getById(id);
        request.setAttribute("client", client);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/client/forms.jsp");
        dispatcher.forward(request, response);
    }

    private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String cpf = request.getParameter("cpf");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        LocalDate dateOfBirth = LocalDate.parse(request.getParameter("birthday"));

        Client client = new Client(name, email, password, cpf, phone, gender, dateOfBirth);
        Erro erro = new Erro();

        if(userDao.getByEmail(client.getEmail()) != null)
        {
             erro.add("O Email inserido já está em uso");
             request.setAttribute("mensagens", erro.getErros());
             RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/client/forms.jsp");
             dispatcher.forward(request, response);
             return;
        }

        dao.insert(client);
        response.sendRedirect("list");
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String cpf = request.getParameter("cpf");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        LocalDate dateOfBirth = LocalDate.parse(request.getParameter("birthday"));

        Client client = new Client(id, name, email, password, cpf, phone, gender, dateOfBirth);

        dao.update(client);
        response.sendRedirect("list");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int id = Integer.parseInt(request.getParameter("id"));

        Client client = dao.getById(id);

        dao.delete(client);

        response.sendRedirect("list");
    }
}
