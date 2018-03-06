package ru.carbay.promoter.drivers.additional;

import ru.carbay.promoter.ds.ProxyDS;
import ru.carbay.promoter.model.Proxy;
import ru.carbay.promoter.services.proxy.ProxyList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BestProxyCycle {

    public static Proxy getBestProxy_proxySellerCom() throws Exception {
        List<Proxy> freshProxies = ProxyList.proxySellerCom();
        List<Proxy> usedProxies = ProxyDS.getAllProxies();
        Map<String, Proxy> usedProxyMap = new HashMap<>();
        usedProxies.forEach((proxy -> {
            usedProxyMap.put(proxy.getIpAdress() + ":" + proxy.getPort(), proxy);
        }));

        Proxy bestProxy = null;
        for (Proxy freshProxy : freshProxies) {
            Proxy usedProxy = usedProxyMap.get(freshProxy.getIpAdress() + ":" + freshProxy.getPort());
            if (usedProxy == null) {
                bestProxy = freshProxy;
                break;
            } else if (bestProxy == null) {
                bestProxy = usedProxy;
            } else if (bestProxy.getLastUsage().after(usedProxy.getLastUsage())) {
                bestProxy = usedProxy;
            }
        }
        bestProxy.setLastUsage(new Date());
        ProxyDS.save(bestProxy);

        return bestProxy;
    }
}
