package services.factory;

import services.*;

import java.util.HashMap;
import java.util.Map;

import static services.path.ServicePaths.*;

public class ServiceFactory {
    private static final Map<String, IService> SERVICE_MAP = new HashMap<>();
    private static final ServiceFactory SERVICE_FACTORY = new ServiceFactory();

    static {
        SERVICE_MAP.put(REGISTER_SERVICE, new RegisterService());
        SERVICE_MAP.put(REGISTER_FORWARD_SERVICE, new RegisterForwardService());
        SERVICE_MAP.put(LOGIN_SERVICE, new LoginService());
        SERVICE_MAP.put(LOGIN_FORWARD_SERVICE, new LoginForwardService());
        SERVICE_MAP.put(MAIN_PAGE_FORWARD_SERVICE, new MainPageForwardService());
        SERVICE_MAP.put(CATEGORY_SHOW_SERVICE, new CategoryShowService());
        SERVICE_MAP.put(PRODUCT_SHOW_SERVICE, new ProductShowService());
        SERVICE_MAP.put(BASKET_ADD_SERVICE, new BasketAddService());
        SERVICE_MAP.put(BASKET_PAGE_FORWARD_SERVICE, new BasketPageForwardService());
    }

    public static ServiceFactory getInstance() {
        return SERVICE_FACTORY;
    }

    public IService getService(String request) {
        IService service = null;
        for (Map.Entry<String, IService> pair : SERVICE_MAP.entrySet()) {
            if (request.equalsIgnoreCase(pair.getKey())) {
                service = SERVICE_MAP.get(pair.getKey());
            }
        }
        return service;
    }
}
