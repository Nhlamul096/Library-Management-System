/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.process.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import za.co.material.Author;
import za.co.material.service.AuthorService;
import za.co.material.service.impl.AuthorServiceImpl;
import za.co.process.ProcessInt;

/**
 *
 * @author thiof
 */
public class AuthorProcess implements ProcessInt {

    private BasicDataSource dbmanager;
    private RequestDispatcher view;

    @Override
    public void processTheRequest(HttpServletRequest request, HttpServletResponse response) {
        ServletContext sc = request.getServletContext();
        if (sc != null) {
            dbmanager = (BasicDataSource) sc.getAttribute("dbman");
        }
        if (dbmanager == null) {
            return;
        }
        int aid = 0;
        String author = null;
        Author a = new Author();
        List<Author> authors = new ArrayList<>();

        AuthorService authorService = new AuthorServiceImpl(dbmanager);
        String action = request.getParameter("action");
        if (action != null) {
            switch (action.toLowerCase()) {
                case "add":
                    try {
                    author = request.getParameter("author");
                    aid = Integer.parseInt(request.getParameter("aid"));
                    a.setAid(aid);
                    a.setAuthor(author);
                } catch (NumberFormatException ex) {
                    System.out.println("Error " + ex.getMessage());
                }

                if (authorService.add(a)) {
                    request.setAttribute("msg", "the author has been added");
                    request.setAttribute("author", a);
                    view = request.getRequestDispatcher("Author.jsp");
                    // create **Author.jsp**
                    break;
                }
                request.setAttribute("msg", "the author has NOT been added");
                // view = request.getRequestDispatcher("ERRORPAGE.jsp");
                view = request.getRequestDispatcher("Author.jsp");
                break;
                case "delete":
                    try {
                    aid = Integer.parseInt(request.getParameter("aid"));
                } catch (NumberFormatException ex) {
                    System.out.println("Error " + ex.getMessage());
                }

                if (authorService.delete(aid)) {
                    request.setAttribute("msg", "the author if ID " + aid + " has been deleted");
                    view = request.getRequestDispatcher("Author.jsp");
                    // create **Author.jsp**
                    break;
                }
                request.setAttribute("msg", "the author has NOT been deleted");
                // view = request.getRequestDispatcher("ERRORPAGE.jsp");
                view = request.getRequestDispatcher("Author.jsp");
                break;
                case "update":
                    try {
                    author = request.getParameter("author");
                    aid = Integer.parseInt(request.getParameter("aid"));
                    a.setAid(aid);
                    a.setAuthor(author);
                } catch (NumberFormatException ex) {
                    System.out.println("Error " + ex.getMessage());
                }

                if (authorService.update(a)) {
                    request.setAttribute("msg", "the author has been updated");
                    request.setAttribute("author", a);
                    view = request.getRequestDispatcher("Author.jsp");
                    // create **Author.jsp**
                    break;
                }
                request.setAttribute("msg", "the author has NOT been updated");
                // view = request.getRequestDispatcher("ERRORPAGE.jsp");
                view = request.getRequestDispatcher("Author.jsp");
                break;
                case "get":
                    try {
                    aid = Integer.parseInt(request.getParameter("aid"));
                } catch (NumberFormatException ex) {
                    System.out.println("Error " + ex.getMessage());
                }
                a = authorService.get(aid);
                if (a != null) {
                    request.setAttribute("msg", "Here's the author");
                    request.setAttribute("author", a);
                    view = request.getRequestDispatcher("Author.jsp");
                    // create **Author.jsp**
                    break;
                }
                request.setAttribute("msg", "the author has NOT been found");
                // view = request.getRequestDispatcher("ERRORPAGE.jsp");
                view = request.getRequestDispatcher("Author.jsp");
                break;
                case "getall":
                    authors = authorService.getAll();
                    if (authors.size() > 0) {
                        request.setAttribute("msg", "Here's the list of authors");
                        request.setAttribute("authors", authors);
                        view = request.getRequestDispatcher("Author.jsp");
                        // create **Author.jsp** and a scriplet for(Author a : authors){}
                        break;
                    }
                    request.setAttribute("msg", "the no author have been found");
                    // view = request.getRequestDispatcher("ERRORPAGE.jsp");
                    view = request.getRequestDispatcher("Author.jsp");
                    break;
                default:
                    break;

            }
            processTheResponse(request, response);
        }
    }

    @Override
    public void processTheResponse(HttpServletRequest request, HttpServletResponse response) {
        if (view == null) {
            // RequestDispatcher view = request.getRequestDispatcher(".jsp");
        }
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

}
