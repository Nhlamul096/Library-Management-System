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
import za.co.material.service.ReserveService;
import za.co.material.service.impl.ReserveServiceImpl;
import za.co.process.ProcessInt;

/**
 *
 * @author TRAIN 76
 */
public class ReserveProcess implements ProcessInt {

    private ReserveService reservedao;
    BasicDataSource dbmanager;

    @Override
    public void processTheRequest(HttpServletRequest request, HttpServletResponse response) {
        ServletContext sc = request.getServletContext();
        if (sc != null) {
            dbmanager = (BasicDataSource) sc.getAttribute("dbman");
        }
        if (dbmanager == null) {
            return;
        }
        reservedao = new ReserveServiceImpl(dbmanager);
        String action = request.getParameter("action");
        String title = null;
        String isbno = null;
        String memberid = null;

        if (action != null) {
            switch (action.toLowerCase()) {
                case "reserve":
                    title = request.getParameter("title");
                    isbno = request.getParameter("isbno");
                    memberid = request.getParameter("memberid");

                    if (reservedao.reservebook(title, isbno, memberid)) {
                        request.setAttribute("msg", "Book has been successfully reserved");
                    } else {
                        request.setAttribute("msg", "Book has NOT been successfully reserved");
                    }
                    processTheResponse(request, response);
                    break;
                case "check":
                    title = request.getParameter("title");
                    isbno = request.getParameter("isbno");
                    memberid = request.getParameter("memberid");
                    String worked = reservedao.checkreserve(title, isbno, memberid);

                    if (worked != null && !worked.isEmpty()) {
                        request.setAttribute("msg", worked);
                    } else {
                        request.setAttribute("msg", "Reservation checking process not successful");
                    }
                    processTheResponse(request, response);
                    break;
                default:
                    

            }
        }
    }

    @Override
    public void processTheResponse(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher view = request.getRequestDispatcher("ReservePage.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException | IOException ex) {
            System.out.println("Error : "+ex.getMessage());
        }
    }

}
