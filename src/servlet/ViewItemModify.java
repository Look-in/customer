package servlet;

import dao.SelectDao;
import dao.request.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        Map<Integer, SelectDao> dao = new HashMap<>();
        dao.put(1, SelectClothesDao.getInstance());
        dao.put(2, SelectBicycleDao.getInstance());
        if (request.getParameter("itemId") != null) {
            request.setAttribute("item", dao.get(Integer.valueOf(request.getParameter("typeId")))
                    .readItem(Integer.valueOf(request.getParameter("itemId"))));
        }
        request.setAttribute("statuses", SelectItemStatusDao.readItemStatus());
        request.getRequestDispatcher(String.format("jsp/%smodify.jsp",request.getParameter("type").toLowerCase())).forward(request, response);
    }

}
