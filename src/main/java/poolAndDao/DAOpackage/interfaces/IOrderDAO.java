package poolAndDao.DAOpackage.interfaces;

import entities.Order;


import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface IOrderDAO extends DAO{
    void createOrder(Order order) throws SQLException;
    void linkOrderAndProduct(long id, long product_id) throws SQLException;
    Order getOrderById(long id) throws SQLException, IOException;
    List<Order> getAllOrdersByCustomerId() throws SQLException, IOException;
    List<Order> getAllOrders() throws SQLException, IOException;
    void changeDateDelivered(Date date, long id) throws SQLException;
    void changeStatus(String status, long id) throws SQLException;
}
