package servlet;

import dao.request.SelectClothesDao;
import entity.event.TypeItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(
        name = "SelectItemServlet",
        description = "Сервлет для передачи списка товаров",
        urlPatterns = "/selectitemservlet")



public class SelectItemServlet extends javax.servlet.http.HttpServlet {

    private void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("typeitem", TypeItem.values());
        switch (request.getParameter("entity")) {
            case "ALL":
                request.setAttribute("item", SelectClothesDao.readListItem());
                break;
            case "CLOTHES":
                request.setAttribute("item", SelectClothesDao.readListItem());
                break;
            case "BICYCLE":
                break;
        }
        request.getRequestDispatcher("jsp/item_list.jsp").forward(request, response);
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doRequest(request,response);
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doRequest(request,response);
    }

}
