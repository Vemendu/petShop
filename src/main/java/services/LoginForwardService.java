package services;

import controller.ServletController;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static services.path.PagePaths.LOGIN_JSP;

public class LoginForwardService implements IService{
    private static final Logger logger = LogManager.getLogger(ServletController.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        try {
            RequestDispatcher dispatcher;
            dispatcher = request.getRequestDispatcher(LOGIN_JSP);
            dispatcher.forward(request, response);
        }
        catch (Exception e)
        {
            logger.error(e);
        }
    }
}
