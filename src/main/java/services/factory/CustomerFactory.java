package services.factory;

import controller.ServletController;
import entities.Customer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.DateFormatter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

public class CustomerFactory {
    private static CustomerFactory instance = new CustomerFactory();
    private static final Logger logger = LogManager.getLogger(ServletController.class);
    private CustomerFactory() {

    }

    public Customer createCustomer(HttpServletRequest request) throws ParseException {
        Customer customer = new Customer();
        customer.setFirstName(request.getParameter("firstName"));

        customer.setLastName(request.getParameter("lastName"));

        LocalDate localDate = LocalDate.parse(request.getParameter("birthdayDate"));
        Date date = Date.valueOf(localDate);
        customer.setBirthdayDate(date);

        customer.setPhoneNumber(request.getParameter("phoneNumber"));

        customer.setAddress(request.getParameter("address"));

        customer.setLanguage(request.getParameter("language"));

        customer.setCountry(request.getParameter("country"));

        try {
            customer.setRoleId(Integer.parseInt(request.getParameter("roleId")));

        }
        catch (Exception e)
        {
            System.out.println(e + " CUSTOMER FACTORY ROLE CATCH");
            logger.error(e);
        }
        customer.setUsername(request.getParameter("username"));

        customer.setPassword(request.getParameter("password"));

        return customer;
    }

    public static CustomerFactory getInstance() {
        if (instance == null) {
            instance = new CustomerFactory();
        }
        return instance;
    }
}
