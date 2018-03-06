package servlet;

import dao.ChangeInstance;
import dao.request.BicycleDao;
import dao.request.ClothesDao;
import dao.request.SelectClothesDao;
import entity.Bicycle;
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


    private void readAttribute(HttpServletRequest request,Item item){
        if (request.getParameter("itemid")!=null) {
            item.setItemId(Integer.valueOf(request.getParameter("itemid")));
        }
        item.setName(request.getParameter("name"));
        item.setDescription(request.getParameter("description"));
        if (request.getParameter("price")!=null) {
            item.setPrice(Float.valueOf(request.getParameter("price")));
        }
        item.setItemStatusId(Integer.valueOf(request.getParameter("selectstatus")));
        item.setType(request.getParameter("entity"));
        item.setTypeId(Integer.valueOf(request.getParameter("entityid")));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Item item=null;
        ChangeInstance dao=null;

        switch (Integer.valueOf(request.getParameter("entityid"))) {
            case 1:
               Clothes clothes= new Clothes();
               readAttribute(request,clothes);
               clothes.setSeason(request.getParameter("season"));
               item=clothes;
               dao=new ClothesDao();
            break;
            case 2:
                Bicycle bike= new Bicycle();
                readAttribute(request,bike);
                bike.setBrakes(request.getParameter("brakes"));
                bike.setFork(request.getParameter("fork"));
                bike.setFrame(request.getParameter("frame"));
                item=bike;
                dao=new BicycleDao();

            break;
        }
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
       // request.setAttribute("item", SelectClothesDao.readListItem());
        //request.setAttribute("entity", request.getParameter("entity"));
        request.getRequestDispatcher("selectitemservlet").forward(request, response);
     }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
