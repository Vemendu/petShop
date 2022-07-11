package services;

import controller.ServletController;
import entities.Product;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import poolAndDao.DAOpackage.classes.ProductDAO;
import poolAndDao.DAOpackage.factory.FactoryDAO;
import poolAndDao.DAOpackage.interfaces.IProductDAO;
import services.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static poolAndDao.DAOpackage.factory.EnumDAO.PRODUCT_DAO;
import static services.path.PagePaths.CATEGORY_PAGE_JSP;
import static services.path.PagePaths.MAIN_PAGE_JSP;

public class CategoryShowService implements IService{
    private FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private IProductDAO productDAO = (ProductDAO) factoryDAO.getDAO(PRODUCT_DAO);
    private static final Logger logger = LogManager.getLogger(ServletController.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        try {
            RequestDispatcher dispatcher;
            List<Product> list;
            list = productDAO.getProductsByCategory(Long.parseLong(request.getParameter("categoryId")));
            request.setAttribute("productList", list);
            dispatcher = request.getRequestDispatcher(CATEGORY_PAGE_JSP);
            dispatcher.forward(request, response);
        }
        catch (Exception e)
        {
            logger.error(e);
        }
    }
}
