package poolAndDao.DAOpackage.factory;

import poolAndDao.DAOpackage.classes.*;
import poolAndDao.DAOpackage.interfaces.DAO;
import static poolAndDao.DAOpackage.factory.EnumDAO.*;

import java.util.HashMap;
import java.util.Map;

public class FactoryDAO {
    private static final Map<Enum, DAO> MAP = new HashMap<>();
    private static final FactoryDAO FACTORY_DAO = new FactoryDAO();

    static {
        MAP.put(BASKET_DAO, new BasketDAO());
        MAP.put(CATEGORY_DAO, new CategoryDAO());
        MAP.put(CUSTOMER_DAO, new CustomerDAO());
        MAP.put(ORDER_DAO, new OrderDAO());
        MAP.put(PRODUCT_DAO, new ProductDAO());
        MAP.put(ROLE_DAO, new RoleDAO());
    }

    public static FactoryDAO getInstance() {
        return FACTORY_DAO;
    }

    public DAO getDAO(Enum EnumDAO) {
        return MAP.get(EnumDAO);
    }
}
