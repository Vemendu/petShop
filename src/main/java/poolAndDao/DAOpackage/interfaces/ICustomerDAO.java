package poolAndDao.DAOpackage.interfaces;

import entities.Customer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ICustomerDAO extends DAO {
    void createCustomer(Customer customer) throws SQLException;
    Customer getCustomerById(long id) throws SQLException, IOException;
    Customer getCustomerByUsername(String username) throws SQLException, IOException;
    Customer getCustomerByLogin(String username, String password) throws SQLException, IOException;
    List<Customer> getAllCustomers() throws SQLException, IOException;
    void changePassword(String password, long id) throws SQLException;
    void changePhoneNumber(String phoneNumber, long id) throws SQLException;
    void changeAddress(String address, long id) throws SQLException;
    void changeLanguage(String language, long id) throws SQLException;
    void changeCountry(String country, long id) throws SQLException;
    void changeRole(int role_id, long id) throws SQLException;
    void deleteCustomer(long id) throws SQLException;
}
