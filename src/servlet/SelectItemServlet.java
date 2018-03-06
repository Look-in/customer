package servlet;

import dao.SelectDefaultItemDao;
import dao.request.SelectClothesDao;
import dao.request.SelectTypeDao;
import entity.event.ItemType;
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
        ArrayList<ItemType> type=SelectTypeDao.readType();
        request.setAttribute("typeitem", type);
        if (request.getParameter("entity").equals("ALL")) {
                request.setAttribute("item", SelectDefaultItemDao
                        .getInstance().readAllListItem());
        }
        else {
            request.setAttribute("item", SelectDefaultItemDao
                    .getInstance()
                    .readFilteredListItem(Integer.valueOf(request.getParameter("entityid"))));
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
