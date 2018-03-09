package servlet;

import entity.event.ItemFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "ViewItemModify",
        description = "Сервлет для отображения страницы модификации товара",
        urlPatterns = "/viewitemmodify"
)
public class ViewItemModify extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("itemservlet").forward(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("itemId") != null) {
            request.setAttribute("item", ItemFactory.getItemDao(Integer.valueOf(request.getParameter("typeId")))
                    .readItem(Integer.valueOf(request.getParameter("itemId"))));
        }
        request.setAttribute("statuses", ItemFactory.getItemStatusDao().readItemStatuses());
        request.getRequestDispatcher(String.format("jsp/%smodify.jsp", request.getParameter("type").toLowerCase())).forward(request, response);
    }

}
