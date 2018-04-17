package servlet;

import dao.SelectItemType;
import dao.request.SelectDefaultItemDao;
import entity.comparator.ItemComparator;
import entity.event.AttributeToCompare;
import entity.event.Item;


import javax.inject.Inject;
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

    @Inject
    SelectDefaultItemDao selectDefaultItemDao;

    @Inject
    SelectItemType selectItemType;

    private void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("sortBy", AttributeToCompare.values());
        ArrayList<Item> item;
        if (request.getParameter("itemType").equals("ALL"))
            item = selectDefaultItemDao.readListItem();
        else
            item = selectDefaultItemDao.readListItem(Integer.valueOf(request.getParameter("itemType")));
        request.setAttribute("itemType", selectItemType.readItemTypes());
        if (request.getParameter("sortingBy") != null) {
            ItemComparator.compare(item, AttributeToCompare.valueOf(request.getParameter("sortingBy")));
        }
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
