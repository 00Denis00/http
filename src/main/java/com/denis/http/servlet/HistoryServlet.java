package com.denis.http.servlet;

import com.denis.http.model.FileModel;
import com.denis.http.model.History;
import com.denis.http.model.User;
import com.denis.http.service.HistoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HistoryServlet", urlPatterns = { "/api/v1/histories" })
public class HistoryServlet extends HttpServlet
{
    HistoryService historyService = new HistoryService();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        String parameter = request.getParameter("UserId");
        int userId = Integer.parseInt(parameter);

        History history = new History();
        history.setId(userId);

        User user = new User();
        user.setId(userId);
        history.setUser(user);

        History result = historyService.save(history);
        writer.print(result.toString());
    }

    public void doRemove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        String parameter = request.getParameter("Id");
        int id = Integer.parseInt(parameter);

        History history = new History();
        history.setId(id);
        historyService.delete(history);
        writer.print("The history deleted successfully!");
    }

    public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        String parameter = request.getParameter("Id");
        int id = Integer.parseInt(parameter);

        String fileParameter = request.getParameter("FileId");
        int fileId = Integer.parseInt(fileParameter);

        FileModel file = new FileModel();
        file.setId(fileId);
        List<FileModel> files = new ArrayList<>();
        files.add(file);

        History history = new History();
        history.setId(id);
        history.setFiles(files);

        History result = historyService.update(history);
        writer.print(result.toString());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        String parameter = request.getParameter("Id");
        int id = Integer.parseInt(parameter);

        History history = historyService.findById(id);

        writer.print(history.toString());
    }

    public void doShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        List<History> histories = historyService.findAll();
        for(int i = 0; i < histories.size(); i++)
        {
            History history = histories.get(i);
            writer.print(history.toString() + "<br/>");
        }
    }
}