package ru.carbay.promoter.ds;


import ru.carbay.promoter.model.Proxy;

import java.util.ArrayList;
import java.util.List;

public class ProxyDS {

    public static void save(Proxy proxy) {
        MongoDS.getInstance().ds().save(proxy);
    }

    public static List<Proxy> getAllProxies() {
        List<Proxy> proxies = MongoDS.getInstance().ds().createQuery(Proxy.class).asList();
        return proxies == null ? new ArrayList<>() : proxies;
    }


}
