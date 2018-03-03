package servlet;

import dao.request.SelectClothesDao;
import dao.request.SelectItemStatusDao;
import entity.Clothes;
import entity.ItemStatus;
import entity.event.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "MyOwnServlet",
        description = "Сервлет для модификации товара",
        urlPatterns = "/pushitemmodify"
)
public class PushItemModify extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("itemservlet").forward(request, response);
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (request.getParameter("action")) {
            case "add":
                Clothes clothes=new Clothes();
                request.setAttribute("item", clothes);
                break;
            case "edit":
                SelectClothesDao select = new SelectClothesDao();
                request.setAttribute("item", select.readItem(Integer.valueOf(request.getParameter("id"))));
                break;
            case "delete":
                break;
        }
        //SelectItemStatusDao selectItemDao = new SelectItemStatusDao();
        request.setAttribute("statuses", SelectItemStatusDao.readItemStatus());
        request.getRequestDispatcher("jsp/clothesmodify.jsp").forward(request, response);
    }

}
