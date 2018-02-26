package servlet;

import dao.ItemDao;
import entity.Item;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        ArrayList<Item> items = new ArrayList<>();
        ItemDao itemDao = new ItemDao();
        try {
            items = itemDao.readItem();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (items.size() > 0) {
            request.setAttribute("item", items);
            request.getRequestDispatcher("jsp/item_list.jsp").forward(request, response);
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
