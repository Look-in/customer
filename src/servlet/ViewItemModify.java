package servlet;

import dao.request.BicycleDao;
import dao.request.ClothesDao;
import dao.request.SelectClothesDao;
import dao.request.SelectItemStatusDao;
import entity.Bicycle;
import entity.Clothes;
import entity.event.Item;

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
        String jsp="";
            switch (Integer.valueOf(request.getParameter("entityid"))) {
                case 1:
                    Clothes item= new Clothes();
                    jsp="jsp/clothesmodify.jsp";
                    request.setAttribute("item", SelectClothesDao
                            .getInstance()
                            .readItem(Integer.valueOf(request.getParameter("id"))));
                    request.setAttribute("item", item);
                    break;

                case 2:
                    Bicycle bike= new Bicycle();
                    jsp="jsp/bicyclemodify.jsp";
                    request.setAttribute("item", bike);
                    break;
            }

   /*     switch (request.getParameter("action")) {
            case "ADD":
                request.setAttribute("item", item);
                break;

            case "EDIT":
               // System.err.println(request.getParameter("id"));
                request.setAttribute("item", SelectClothesDao
                        .getInstance()
                        .readItem(Integer.valueOf(request.getParameter("id"))));
                break;
            case "DELETE":
                break;
        }*/
        request.setAttribute("statuses", SelectItemStatusDao.readItemStatus());
        request.getRequestDispatcher(jsp).forward(request, response);
    }

}
