package com.denis.http.servlet;

import com.denis.http.model.FileModel;
import com.denis.http.service.FileService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "FileServlet", urlPatterns = { "/api/v1/files" })
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class FileServlet extends HttpServlet
{
    FileService fileService = new FileService();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        Part filePart = request.getPart("file");

        String fileName = filePart.getHeader("content-disposition");
        for(int i = 0 ; i < 2 ; i++)
        {
            fileName = fileName.substring(fileName.indexOf('=') + 1).trim();
        }
        fileName = fileName.replace("\"","");

        for (Part part : request.getParts())
        {
            part.write(fileService.directoryPath + fileName);
        }

        FileModel file = new FileModel();
        file.setName(fileName);
        FileModel result = fileService.save(file);
        writer.print(result.toString());
    }

    public void doRemove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        String parameter = request.getParameter("Id");
        int id = Integer.parseInt(parameter);

        FileModel filemodel = fileService.findById(id);
        String name = fileService.directoryPath + filemodel.getName();
        File file = new File(name);
        if(file.delete())
        {
            fileService.delete(filemodel);
        }
        writer.print("The file deleted successfully!");
    }

    public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        String parameter = request.getParameter("Id");
        int id = Integer.parseInt(parameter);
        String newName = request.getParameter("Name");

        FileModel filemodel = fileService.findById(id);
        String extension = filemodel.getName().substring(filemodel.getName().lastIndexOf("."));
        String name = fileService.directoryPath + filemodel.getName();

        File original = new File(name);
        File res = new File(fileService.directoryPath + newName + extension);
        if(original.renameTo(res))
        {
            FileModel file = new FileModel();
            file.setId(id);
            file.setName(newName + extension);
            FileModel result = fileService.update(file);
            writer.print(result.toString());
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        String parameter = request.getParameter("Id");
        int id = Integer.parseInt(parameter);

        FileModel file = fileService.findById(id);

        writer.print(file.toString());
    }

    public void doShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        List<FileModel> files = fileService.findAll();
        for(int i = 0; i < files.size(); i++)
        {
            FileModel file = files.get(i);
            writer.print(file.toString() + "<br/>");
        }
    }
}