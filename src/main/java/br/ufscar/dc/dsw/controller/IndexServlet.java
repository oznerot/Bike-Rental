package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.UserDAO;
import br.ufscar.dc.dsw.model.User;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(name = "Index", urlPatterns = {"/index.jsp", "/logout.jsp", "/login/*"})
public class IndexServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    UserDAO dao;

    @Override
    public void init() throws ServletException
    {
        super.init();
        this.dao = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Erro erros = new Erro();

        if(request.getParameter("bOK") != null)
        {
            String email = request.getParameter("username");
            String password = request.getParameter("password");

            if(email == null || email.trim().isEmpty())
            {
                erros.add("Email não informado!");
            }
            if(password == null || password.trim().isEmpty())
            {
                erros.add("Senha não informada!");
            }

            if(!erros.isExisteErros())
            {
                User user = dao.getByEmail(email);

                if(user!= null && user.getPassword().equals(password))
                {
                    request.getSession().setAttribute("userLogged", user);
                    String contextPath = request.getContextPath().replace("/", "");
                    request.getSession().setAttribute("contextPath", contextPath);

                    if(user.getRole().equals("ADMIN"))
                    {
                        response.sendRedirect("clients/");
                    }
                    else
                    {
                        response.sendRedirect("rentals/");
                    }

                    return;
                }
                else
                {
                    erros.add("Email ou senha inválidos!");
                }
            }
        }

        request.getSession().invalidate();

        request.setAttribute("mensagens", erros);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/login.jsp");
        dispatcher.forward(request, response);
    }


}