package za.co.process.impl;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import za.co.material.Category;
import za.co.material.service.CategoryService;
import za.co.material.service.impl.CategoryServiceImpl;
import za.co.process.ProcessInt;

public class CategoryProcess implements ProcessInt {

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
        int cid = 0;
        boolean isActive = false;
        
        
        CategoryService categoryService = new CategoryServiceImpl(dbmanager);
        String action = request.getParameter("action");
        if (action != null) {
            switch (action.toLowerCase()) {
                case "add":
                    if (categoryService.add(request.getParameter("desc"))) {
                        request.setAttribute("msg", "the category has been added");
                        view = request.getRequestDispatcher("Category.jsp");
                        break;
                    }
                    request.setAttribute("msg", "the category has NOT been added");
                    // view = request.getRequestDispatcher("ERRORPAGE.jsp");
                    view = request.getRequestDispatcher("Category.jsp");
                    break;
                // ***********************************************************************************        
                case "get":
                    try {
                       cid  = Integer.parseInt(request.getParameter("cid"));
                    } catch (NumberFormatException ex) {
                        System.out.println("Error : "+ ex.getMessage());
                    }
                    Category cat = categoryService.get(cid);
                    if (cat != null) {
                        request.setAttribute("msg", "Here's the category of id " + cid);
                        view = request.getRequestDispatcher("Category.jsp");
                    break;
                    }
                    request.setAttribute("msg", "No category found");
                    // view = request.getRequestDispatcher("ERRORPAGE.jsp");
                    view = request.getRequestDispatcher("Category.jsp");
                break;
                // ***********************************************************************************        
                case "getall":
                    
                    isActive  = Boolean.valueOf(request.getParameter("isactive"));
                    
                    List<Category> categorylist = categoryService.getAll(isActive);
                    if (categorylist != null && categorylist.size()>=0) {
                        request.setAttribute("msg", "Here's a list of categories ");
                        request.setAttribute("category", categorylist);
                        view = request.getRequestDispatcher("Category.jsp");
                    break;
                    }
                    request.setAttribute("msg", "No categories found");
                    request.setAttribute("category", categorylist);
                    // view = request.getRequestDispatcher("ERRORPAGE.jsp");
                    view = request.getRequestDispatcher("Category.jsp");
                    break;

                // ***********************************************************************************                   
                case "delete":
                    try {
                       cid  = Integer.parseInt(request.getParameter("cid"));
                       isActive = Boolean.valueOf(request.getParameter("isactive"));
                    } catch (NumberFormatException ex) {
                        System.out.println("Error : "+ ex.getMessage());
                    }
                    Category category = new Category(cid,request.getParameter("desc"), isActive );
                    
                    if (categoryService.delete(category)) {
                        request.setAttribute("msg", "the category has been deleted");
                        view = request.getRequestDispatcher("Category.jsp");
                        break;
                    }
                    request.setAttribute("msg", "the category has NOT been found");
                    // view = request.getRequestDispatcher("ERRORPAGE.jsp");
                    view = request.getRequestDispatcher("Category.jsp");
                    break;
                  

                // ***********************************************************************************         
                case "update":
                    cid = 0;
                    try {
                       cid  = Integer.parseInt(request.getParameter("cid"));
                       
                    } catch (NumberFormatException ex) {
                        System.out.println("Error : "+ ex.getMessage());
                    }
                    category = new Category(cid,request.getParameter("desc"),
                    Boolean.valueOf(request.getParameter("isactive")));
                    
                    if (categoryService.update(category)) {
                        request.setAttribute("msg", "the category has been updated");
                        view = request.getRequestDispatcher("Category.jsp");
                        break;
                    }
                    request.setAttribute("msg", "the category has NOT been found");
                    // view = request.getRequestDispatcher("ERRORPAGE.jsp");
                    view = request.getRequestDispatcher("Category.jsp");
                    break;
                // ***********************************************************************************  
                case "setActive":
                    cid = 0;
                    try {
                       cid  = Integer.parseInt(request.getParameter("cid"));
                       isActive = Boolean.valueOf(request.getParameter("isactive"));
                    } catch (NumberFormatException ex) {
                        System.out.println("Error : "+ ex.getMessage());
                    }
                    
                    if (categoryService.setCategoryActivity(cid, isActive)) {
                        request.setAttribute("msg", "the category availabilty has been updated to "+ isActive);
                        view = request.getRequestDispatcher("Category.jsp");
                        break;
                    }
                    request.setAttribute("msg", "the category has NOT been found");
                    // view = request.getRequestDispatcher("ERRORPAGE.jsp");
                    view = request.getRequestDispatcher("Category.jsp");
                    break;
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
