package controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import services.IService;
import services.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class ServletController extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(ServletController.class);

    public ServletController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        performTask(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        performTask(request, response);
    }

    private void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestString = request.getRequestURI();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        IService service = serviceFactory.getService(requestString);
        try {
            service.execute(request, response);
        } catch (ParseException | SQLException e) {
            System.out.println(e + " SERVLET CONTROLLER CATCH");
        }
    }
}
