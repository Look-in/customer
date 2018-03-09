package servlet;

import entity.comparator.ItemComparator;
import entity.event.Item;
import entity.event.ItemFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(
        name = "SelectItemServlet",
        description = "Сервлет для передачи списка товаров",
        urlPatterns = "/selectitemservlet")


public class SelectItemServlet extends javax.servlet.http.HttpServlet {

    private void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("sortBy", Arrays.asList("Price", "Price DESC", "Name", "Status"));
        ArrayList<Item> item;
        if (request.getParameter("typeId") == null)
            item = ItemFactory.getDefaultItemDao().readListItem();
        else
            item = ItemFactory.getDefaultItemDao().readListItem(Integer.valueOf(request.getParameter("typeId")));
        request.setAttribute("typeitem", ItemFactory.getItemTypeDao().readItemType());
        if (request.getParameter("sortingBy") != null) ItemComparator.compare(item,request.getParameter("sortingBy"));
        request.setAttribute("item", item);
        request.getRequestDispatcher("jsp/item_list.jsp").forward(request, response);
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doRequest(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doRequest(request, response);
    }

}
