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

import br.ufscar.dc.dsw.dao.UserDAO;
import br.ufscar.dc.dsw.dao.UserDAOImpl;
import br.ufscar.dc.dsw.model.User;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    UserDAO userDAO = new UserDAOImpl();
            
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");

        String page = "";
        String action = request.getParameter("action");


        if(action == null)
        {
            action = "";
        }

        switch(action){           
            case "register":
                page = "registerPage.jsp";
            
            default:
                page = "loginPage.jsp";
                break;     
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);

    }
            
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userDAO.getByEmail(email);
        if(user != null)
        {
            if(doPasswordMatch(user, password))
            {
                logInSession(user, request, response);
            }
        }

    }

    //Using this so in the future if i want to use salting to match password
    private boolean doPasswordMatch(User user, String password)
    {
        return user.getPassword().equals(password);
    }

    private void logInSession(User user, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.getSession().setAttribute("userLogged", user);
        String contextPath = request.getContextPath();
        request.getSession().setAttribute("contextPath", contextPath);

        System.out.println(contextPath);

        response.sendRedirect(contextPath + "/");
    }
}