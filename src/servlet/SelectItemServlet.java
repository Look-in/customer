package servlet;

import dao.request.SelectClothesDao;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(
        name = "SelectItemServlet",
        description = "Сервлет для передачи списка товаров",
        urlPatterns = "/selectitemservlet")

public class SelectItemServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
         request.setAttribute("item", SelectClothesDao.readListItem());
        request.getRequestDispatcher("jsp/item_list.jsp").forward(request, response);
    }

}
