package servlet;

import dao.request.SelectClothesDao;
import dao.request.SelectItemStatusDao;
import entity.Clothes;
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
        switch (request.getParameter("action")) {
            case "ADD":
                Clothes clothes=new Clothes();
                request.setAttribute("item", clothes);
                break;
            case "EDIT":
                request.setAttribute("item", SelectClothesDao.readItem(Integer.valueOf(request.getParameter("id"))));
                break;
            case "DELETE":
                break;
        }
        request.setAttribute("statuses", SelectItemStatusDao.readItemStatus());
        request.getRequestDispatcher("jsp/clothesmodify.jsp").forward(request, response);
    }

}
