package com.denis.http.servlet;

import com.denis.http.model.User;
import com.denis.http.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = { "/api/v1/users" })
public class UserServlet extends HttpServlet
{
    UserService userService = new UserService();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        String name = request.getParameter("Name");

        User user = new User();
        user.setName(name);

        User result = userService.save(user);
        writer.print(result.toString());
    }

    public void doRemove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        String parameter = request.getParameter("Id");
        int id = Integer.parseInt(parameter);

        User user = new User();
        user.setId(id);

        userService.delete(user);
        writer.print("The user deleted successfully!");
    }

    public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        String parameter = request.getParameter("Id");
        int id = Integer.parseInt(parameter);
        String name = request.getParameter("Name");

        User user = new User();
        user.setId(id);
        user.setName(name);

        User result = userService.update(user);
        writer.print(result.toString());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        String parameter = request.getParameter("Id");
        int id = Integer.parseInt(parameter);

        User user = userService.findById(id);

        writer.print(user.toString());
    }

    public void doShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        List<User> users = userService.findAll();
        for(int i = 0; i < users.size(); i++)
        {
            User user = users.get(i);
            writer.print(user.toString() + "<br/>");
        }
    }
}