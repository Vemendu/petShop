package services;

import controller.ServletController;
import entities.Customer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import poolAndDao.DAOpackage.classes.CustomerDAO;
import poolAndDao.DAOpackage.factory.FactoryDAO;
import poolAndDao.DAOpackage.interfaces.ICustomerDAO;
import services.factory.CustomerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

import static poolAndDao.DAOpackage.factory.EnumDAO.CUSTOMER_DAO;
import static services.path.PagePaths.LOGIN_JSP;

public class RegisterService implements IService{
    private FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private CustomerFactory customerFactory = CustomerFactory.getInstance();
    private ICustomerDAO customerDAO = (CustomerDAO) factoryDAO.getDAO(CUSTOMER_DAO);
    private static final Logger logger = LogManager.getLogger(ServletController.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        try {
            RequestDispatcher dispatcher;
            Customer customer = customerFactory.createCustomer(request);
            customerDAO.createCustomer(customer);
            dispatcher = request.getRequestDispatcher(LOGIN_JSP);
            dispatcher.forward(request, response);
        }
        catch (Exception e)
        {
            logger.error(e);
        }
    }
}
