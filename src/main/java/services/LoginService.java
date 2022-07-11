package services;

import controller.ServletController;
import entities.Basket;
import entities.Customer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import poolAndDao.DAOpackage.classes.BasketDAO;
import poolAndDao.DAOpackage.classes.CustomerDAO;
import poolAndDao.DAOpackage.factory.FactoryDAO;
import poolAndDao.DAOpackage.interfaces.IBasketDAO;
import poolAndDao.DAOpackage.interfaces.ICustomerDAO;
import services.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;

import static poolAndDao.DAOpackage.factory.EnumDAO.BASKET_DAO;
import static poolAndDao.DAOpackage.factory.EnumDAO.CUSTOMER_DAO;
import static services.path.ServicePaths.MAIN_PAGE_FORWARD_SERVICE;

public class LoginService implements IService{
    private FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private ICustomerDAO customerDAO = (CustomerDAO) factoryDAO.getDAO(CUSTOMER_DAO);
    private IBasketDAO basketDAO = (BasketDAO) factoryDAO.getDAO(BASKET_DAO);
    private static final Logger logger = LogManager.getLogger(ServletController.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        try {
            Customer customer;
            customer = customerDAO.getCustomerByLogin(request.getParameter("username"), request.getParameter("password"));
            if(customer==null) {
                logger.error("Customer was not found!");
            }
            else {
                URL u = getClass().getClassLoader().getResource("log4j.xml");
                DOMConfigurator.configure(u);
                logger.debug("Configured!");
                Cookie id = new Cookie("id", customer.getId() + "");
                Cookie firstName = new Cookie("firstName", request.getParameter("firstName"));
                id.setMaxAge(60*60*24);
                firstName.setMaxAge(60*60*24);
                response.addCookie(id);
                response.addCookie(firstName);
                Basket basket;
                basket = basketDAO.getBasketByCustomerId(customer.getId());
                if(basket!=null)
                {
                    basketDAO.deleteProductsInBasket(basket.getId());
                    basketDAO.deleteBasket(customer.getId());
                }
                serviceFactory.getService(MAIN_PAGE_FORWARD_SERVICE).execute(request, response);

            }
        }
        catch (Exception e)
        {
            logger.error(e);
        }
    }
}
