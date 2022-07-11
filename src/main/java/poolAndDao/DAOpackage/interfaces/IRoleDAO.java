package poolAndDao.DAOpackage.interfaces;

import entities.Role;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IRoleDAO extends DAO {
    void createRole(Role role) throws SQLException;
    Role getRoleById(long id) throws SQLException, IOException;
    List<Role> getAllRoles() throws SQLException, IOException;
    void updateRole(String role_name, long id) throws SQLException;
    void deleteRole(long id) throws SQLException;
}
