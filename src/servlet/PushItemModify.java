package servlet;

import dao.ChangeInstance;
import dao.request.ClothesDao;
import dao.request.SelectClothesDao;
import entity.Clothes;
import entity.event.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(
        name = "PushItemModify",
        description = "Сервлет для передачи списка товаров",
        urlPatterns = "/pushitemmodify")

public class PushItemModify extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       Clothes item=new Clothes();
  /*     switch (request.getParameter("entity")) {
        case "Clothes":               // Clothes clothes=new Clothes();*/
       if (request.getParameter("itemid")!=null) {
           item.setItemId(Integer.valueOf(request.getParameter("itemid")));
       }
        item.setName(request.getParameter("name"));
        item.setDescription(request.getParameter("description"));
        if (request.getParameter("price")!=null) {
            float price=Float.valueOf(request.getParameter("price"));
            if (price >= 0) item.setPrice(price); else throw new RuntimeException("Price <0");
        }
        else {
            throw new RuntimeException("Price not declared");
        }
        item.setItemStatusId(Integer.valueOf(request.getParameter("selectstatus")));
        item.setSeason(request.getParameter("season"));
              //  item=clothes;
                /*break;
            case "NewItem":
                break;
        }*/
        System.err.println("id="+item.getItemId());
        ChangeInstance dao;
        switch (request.getParameter("action")) {
            case "ADD":
               dao=new ClothesDao();
               //раскоментировать после отладки
           //    dao.create(item);
                break;
            case "EDIT":
                dao=new ClothesDao();
                dao.update(item);
                break;
            case "DELETE":
                break;
        }
       // request.setAttribute("item", SelectClothesDao.readListItem());
        //request.setAttribute("entity", request.getParameter("entity"));
        request.getRequestDispatcher("selectitemservlet").forward(request, response);
     }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
