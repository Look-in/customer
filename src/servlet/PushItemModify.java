package servlet;

import dao.ChangeInstance;
import dao.request.BicycleDao;
import dao.request.ClothesDao;
import entity.Bicycle;
import entity.Clothes;
import entity.ItemAttribute;
import entity.event.Item;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(
        name = "PushItemModify",
        description = "Сервлет для передачи списка товаров",
        urlPatterns = "/pushitemmodify")

public class PushItemModify extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<Integer, Item> daoItem = new HashMap<>();
        daoItem.put(1, new Clothes());
        daoItem.put(2, new Bicycle());
        Map<Integer, ChangeInstance> daoInstance = new HashMap<>();
        daoInstance.put(1, ClothesDao.getInstance());
        daoInstance.put(2, BicycleDao.getInstance());
        Item item = daoItem.get(Integer.valueOf(request.getParameter("typeId")));
        ItemAttribute.getInstance().setItemAttributes(item, parameterMap);
        ChangeInstance dao = daoInstance.get(Integer.valueOf(request.getParameter("typeId")));
        switch (request.getParameter("action")) {
            case "ADD":
                //раскоментировать после отладки
                dao.create(item);
                break;
            case "EDIT":
                dao.update(item);
                break;
            case "DELETE":
                dao.delete(item.getItemId());
                break;
        }
        request.getRequestDispatcher("selectitemservlet").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
