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
import services.factory.ServiceFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.util.List;

import static poolAndDao.DAOpackage.factory.EnumDAO.*;
import static services.path.ServicePaths.BASKET_PAGE_FORWARD_SERVICE;

public class BasketAddService implements IService{
    private FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private IProductDAO productDAO = (ProductDAO) factoryDAO.getDAO(PRODUCT_DAO);
    private IBasketDAO basketDAO = (BasketDAO) factoryDAO.getDAO(BASKET_DAO);
    private static final Logger logger = LogManager.getLogger(ServletController.class);
    public void execute(HttpServletRequest request, HttpServletResponse response)
    {
        try {

            Product product = null;
            try{
                logger.info(request.getParameter("productId") + " - THIS IS THE ID?");
                product = productDAO.getProductById(Long.parseLong(request.getParameter("productId")));
            }
            catch (NumberFormatException e)
            {
                logger.error(e + " FIRST CATCH");
            }
            Cookie[] cookies = request.getCookies();
            Cookie cookieId = null;
            if(cookies!=null) {
                for (Cookie cookie : cookies) {
                    if(cookie.getName().equals("id")) {
                        cookieId = cookie;
                    }
                }
            }
            try{
                Basket basket = basketDAO.getBasketByCustomerId(Long.parseLong(cookieId.getValue()));
                logger.info("Checking for basket object");
                if(basket==null)
                {
                    basketDAO.createBasket(Long.parseLong(cookieId.getValue()));
                    logger.info("Basket created!");
                    basket = basketDAO.getBasketByCustomerId(Long.parseLong(cookieId.getValue()));
                    logger.info("Basket returned!");
                }
                List<Long> productList = basketDAO.getProductsIdByBasketId(basket.getId());
                if(productList.contains(product.getId()))
                    ;
                else
                {
                    basketDAO.addProductToBasket(basket.getId(), product.getId());
                    logger.info("Product added to Basket!");
                }
            }
            catch (NullPointerException | NumberFormatException e)
            {
                logger.error(e + "\n" +
                        "Basket or cookie is invalid, or SECOND NUMBER FORMAT CATCH");
            }
            serviceFactory.getService(BASKET_PAGE_FORWARD_SERVICE).execute(request, response);
        }
        catch (Exception e)
        {
            logger.error(e + " BASKET ADD SERVICE CATCH");
        }
    }
}
