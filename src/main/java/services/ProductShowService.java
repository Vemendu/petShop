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

import static poolAndDao.DAOpackage.factory.EnumDAO.PRODUCT_DAO;
import static services.path.PagePaths.*;

public class ProductShowService implements IService{
    private FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private IProductDAO productDAO = (ProductDAO) factoryDAO.getDAO(PRODUCT_DAO);
    private static final Logger logger = LogManager.getLogger(ServletController.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        try {
            RequestDispatcher dispatcher;
            Product product;
            product = productDAO.getProductById(Long.parseLong(request.getParameter("productId")));
            request.setAttribute("product", product);
            dispatcher = request.getRequestDispatcher(PRODUCT_PAGE_JSP);
            dispatcher.forward(request, response);
        }
        catch (Exception e)
        {
            logger.error(e);
        }
    }
}
