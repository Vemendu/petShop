package services;

import controller.ServletController;
import entities.Basket;
import entities.Product;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import poolAndDao.DAOpackage.classes.BasketDAO;
import poolAndDao.DAOpackage.classes.ProductDAO;
import poolAndDao.DAOpackage.factory.FactoryDAO;
import poolAndDao.DAOpackage.interfaces.IBasketDAO;
import poolAndDao.DAOpackage.interfaces.IProductDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

import static poolAndDao.DAOpackage.factory.EnumDAO.BASKET_DAO;
import static poolAndDao.DAOpackage.factory.EnumDAO.PRODUCT_DAO;
import static services.path.PagePaths.BASKET_PAGE_JSP;

public class BasketPageForwardService implements IService{
    private FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private IBasketDAO basketDAO = (BasketDAO) factoryDAO.getDAO(BASKET_DAO);
    private IProductDAO productDAO = (ProductDAO) factoryDAO.getDAO(PRODUCT_DAO);
    private static final Logger logger = LogManager.getLogger(ServletController.class);
    public void execute(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            RequestDispatcher dispatcher;
            List<Long> idsList = null;
            List<Product> productList = new LinkedList<>();
            Cookie[] cookies = request.getCookies();
            Cookie cookieId = null;
            if(cookies!=null) {
                for (Cookie cookie : cookies) {
                    if(cookie.getName().equals("id")) {
                        cookieId = cookie;
                    }
                }
            }
            Basket basket;
            basket = basketDAO.getBasketByCustomerId(Long.parseLong(cookieId.getValue()));
            try{
                idsList = basketDAO.getProductsIdByBasketId(basket.getId());
            }
            catch (Exception e)
            {
                logger.debug(e + " get by basketId error");
            }
            for(Long id : idsList)
            {
                Product product;
                product = productDAO.getProductById(id);
                productList.add(product);
            }
            for (Product product : productList) {
                logger.info(product.getName());
                logger.info(product.getDescription());
                logger.info(product.getId());
                logger.info(product.getCost());
                logger.info(product.getInStock());
                logger.info(product.getSupplierId());
            }
            request.setAttribute("productsList", productList);
            dispatcher = request.getRequestDispatcher(BASKET_PAGE_JSP);
            dispatcher.forward(request, response);
        }
        catch (Exception e)
        {
            logger.error(e + " caught an error");
        }
    }
}
