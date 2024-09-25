package za.co.process.impl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import za.co.material.Member;
import za.co.material.service.MemberService;
import za.co.material.service.impl.MemberServiceImpl;
import za.co.process.ProcessInt;

/**
 *
 * @author TRAIN 76
 */
public class MemberProcess implements ProcessInt {

    //***********************************************************************************
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
        MemberService memberService = new MemberServiceImpl(dbmanager);
        String action = request.getParameter("action");
        Member mmbr = null;
        String idno = "";
        if (action != null) {
            switch (action.toLowerCase()) {
                case "add":
                    String idNum = request.getParameter("idno");
                    if (idNum != null && idNum.length() != 13) {
                        request.setAttribute("msg", "ERROR!!ID number must be 13 digits.");
                        //  view = request.getRequestDispatcher("addmemberpage.jsp");
                        view = request.getRequestDispatcher("Member.jsp");
                        break;
                    }
                    mmbr = new Member(request.getParameter("idno"), request.getParameter("name"), request.getParameter("surname"),
                            request.getParameter("title"), request.getParameter("address"),
                            request.getParameter("telnumber"), request.getParameter("cellnumber"),
                            request.getParameter("email"), 2);
                    if (memberService.addMember(mmbr)) {
                        request.setAttribute("msg", "the member has been added");
                        request.setAttribute("member", mmbr);
                        view = request.getRequestDispatcher("Member.jsp");
                        break;
                    }
                    request.setAttribute("msg", "ERROR!! could not add a member");
                    //  view = request.getRequestDispatcher("ErrorPage.jsp");
                    view = request.getRequestDispatcher("Member.jsp");
                    break;
                // ***********************************************************************************        
                case "get":
                    idno = request.getParameter("idno");
                    mmbr = memberService.getMember(idno);
                    if (mmbr != null) {
                        request.setAttribute("msg", "here's the member");
                        request.setAttribute("member", mmbr);
                        // view = request.getRequestDispatcher("Member.jsp");
                        view = request.getRequestDispatcher("Member.jsp");
                        break;
                    }
                    request.setAttribute("msg", "ERROR!! could not find member with id " + idno);
                    //  view = request.getRequestDispatcher("ErrorPage.jsp");
                    view = request.getRequestDispatcher("Member.jsp");
                    break;

                // ***********************************************************************************                   
                case "delete":
                    idno = request.getParameter("idno");
                    if (memberService.deleteMember(idno)) {
                        request.setAttribute("msg", "Member deleted successfully: " + idno);
                        // view = request.getRequestDispatcher("Dashboard.jsp");
                        view = request.getRequestDispatcher("Member.jsp");
                        break;
                    }
                    request.setAttribute("msg", "ERROR!! could not delete member with id " + idno);
                    //  view = request.getRequestDispatcher("ErrorPage.jsp");
                    view = request.getRequestDispatcher("Member.jsp");
                    break;

                // ***********************************************************************************         
                case "update":
                    mmbr = new Member(request.getParameter("idno"), request.getParameter("name"), request.getParameter("surname"),
                            request.getParameter("title"), request.getParameter("address"),
                            request.getParameter("telnumber"), request.getParameter("cellnumber"),
                            request.getParameter("email"), 2);
                    if (memberService.updateMember(mmbr)) {
                        request.setAttribute("msg", "Member updated successfully: " + idno);
                        request.setAttribute("member", mmbr);
                        // view = request.getRequestDispatcher("Dashboard.jsp");
                        view = request.getRequestDispatcher("Member.jsp");
                        break;
                    }
                    request.setAttribute("msg", "ERROR!! could not update member with id " + idno);
                    //  view = request.getRequestDispatcher("ErrorPage.jsp");
                    view = request.getRequestDispatcher("Member.jsp");
                    break;
                // ***********************************************************************************  
                case "setrole":
                    int role = 2;
                    try {
                        role = Integer.parseInt(request.getParameter("role"));
                    } catch (NumberFormatException ne) {
                        System.out.println("ERROR " + ne.getMessage());
                    }
                    String idNumber = request.getParameter("idno");
                    if (memberService.setRole(idNumber, role)) {
                        request.setAttribute("msg", "Member role updated successfully: " + role);

                        // view = request.getRequestDispatcher("Dashboard.jsp");
                        view = request.getRequestDispatcher("Member.jsp");
                        break;
                    }
                    request.setAttribute("msg", "ERROR!! could not update member role of IDno " + idno);
                    //  view = request.getRequestDispatcher("ErrorPage.jsp");
                    view = request.getRequestDispatcher("Member.jsp");
                    break;

                // ***********************************************************************************     
                case "login":
                    idNumber = request.getParameter("idno");
                    String passwd = request.getParameter("p");
                    Member mem = memberService.login(idNumber, passwd);
                    if (mem == null) {
                        request.setAttribute("errMsg", "Invalid id number or Password");
                        view = request.getRequestDispatcher("index.jsp");
                        break;
                    }
                    HttpSession session = request.getSession();
                    session.setAttribute("member", mem);
                    view = request.getRequestDispatcher("dashboard.jsp");
                    break;
                // ** 
                case "changepwd":

                    if (!request.getParameter("pwd").equals(request.getParameter("pwd2"))) {
                        request.setAttribute("errMsg", "Passwords do not match");
                        view = request.getRequestDispatcher("index.jsp");
                        break;
                    }
                    idNumber = request.getParameter("id");
                    passwd = request.getParameter("pwd");
                    mem = memberService.changePassword(idNumber, passwd);
                    if (mem == null) {
                        request.setAttribute("errMsg", "Invalid id number");
                        view = request.getRequestDispatcher("index.jsp");
                        break;
                    } else {
                        view = request.getRequestDispatcher("dashboard.jsp");
                    }
                    session = request.getSession();
                    session.setAttribute("member", mem);

                    break;

                // **
                // ***********************************************************************************   
                default:
                    //  view = request.getRequestDispatcher("ErrorPage.jsp");
                    break;
                // ***********************************************************************************   
            }
            processTheResponse(request, response);
        } else {
            //send an error page or something   
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
