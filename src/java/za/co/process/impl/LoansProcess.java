/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.process.impl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import za.co.material.Book;
import za.co.material.dao.impl.LoansDAOImpl;
import za.co.material.service.LoansService;
import za.co.material.service.impl.LoansServiceImpl;
import za.co.process.ProcessInt;

/**
 *
 * @author thiof
 */
public class LoansProcess implements ProcessInt {
    
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
        LoansService loandao = new LoansServiceImpl(dbmanager);
        String action = request.getParameter("action");
        String memberid = null;
        String title = null;
        Book b = new Book();

        if (action != null) {
            switch (action.toLowerCase()) {
                case "loanbook":
                    
                    memberid = request.getParameter("memberid");
                    title = request.getParameter("title");
                    b = loandao.loanBook(title, memberid);
                    if (b != null) {
                        request.setAttribute("msg", "The book has been successfully loaned");
                        request.setAttribute("book", b);
                    } else {
                        request.setAttribute("msg", "ERROR!! Book not found");
                        request.setAttribute("book", null);
                    }
                    processTheResponse(request, response);
                    break;
                case "returnbook":
                    memberid = request.getParameter("memberid");
                    title = request.getParameter("title");
                    b = loandao.returnBook(title, memberid);
                    if (b != null) {
                        request.setAttribute("msg", "The book has been successfully returned");
                        request.setAttribute("book", b);
                    } else {
                        request.setAttribute("msg", "ERROR occurred while returning book");
                        request.setAttribute("book", null);
                    }
                    processTheResponse(request, response);
                    break;
                case "renewloan":
                    memberid = request.getParameter("memberid");
                    title = request.getParameter("title");
                    b = loandao.renewBook(title, memberid);
                    if (b != null) {
                        request.setAttribute("msg", "The book loan has been successfully renewed");
                        request.setAttribute("book", b);
                    } else {
                        request.setAttribute("msg", "ERROR occurred while renewing book loan");
                        request.setAttribute("book", null);
                    }
                    processTheResponse(request, response);
                    break;
                default:
            }
        }
    }

    @Override
    public void processTheResponse(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher view = request.getRequestDispatcher("LoansTable.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
        }
    }
}
