package services;

import controller.ServletController;
import entities.Category;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import poolAndDao.DAOpackage.classes.CategoryDAO;
import poolAndDao.DAOpackage.factory.FactoryDAO;
import poolAndDao.DAOpackage.interfaces.ICategoryDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static poolAndDao.DAOpackage.factory.EnumDAO.CATEGORY_DAO;
import static poolAndDao.DAOpackage.factory.EnumDAO.CUSTOMER_DAO;
import static services.path.PagePaths.MAIN_PAGE_JSP;

public class MainPageForwardService implements IService{
    private static final Logger logger = LogManager.getLogger(ServletController.class);
    private FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private ICategoryDAO categoryDAO = (CategoryDAO) factoryDAO.getDAO(CATEGORY_DAO);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        try {
            RequestDispatcher dispatcher;
            List<Category> list;
            list = categoryDAO.getAllCategories();
            request.setAttribute("categoryList", list);
            dispatcher = request.getRequestDispatcher(MAIN_PAGE_JSP);
            dispatcher.forward(request, response);
        }
        catch (Exception e)
        {
            logger.error(e);
        }
    }
}
