package ru.carbay.promoter.services.proxy;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import ru.carbay.promoter.model.ds.Proxy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProxyList {

    private static final String API_URL = "http://list.didsoft.com/get?email=andrey@carbay.ru&pass=3mrn94&pid=httppremium&https=yes";
    private static HttpClient client = HttpClientBuilder.create().build();

    public static List<Proxy> usProxyOrg() throws Exception {
        List<Proxy> proxies = new ArrayList<>();
        HttpGet request = new HttpGet(API_URL);
        HttpResponse response = client.execute(request);

        if (response.getStatusLine().getStatusCode() == 200) {
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                Proxy proxy = new Proxy();
                proxy.setIpAdress(line.substring(0, line.indexOf(":")));
                proxy.setPort(line.substring(line.indexOf(":") + 1, line.indexOf("#")));
                proxy.setCountry(line.substring(line.indexOf("#") + 1));
                proxy.setProvider("https://www.us-proxy.org/");
                proxies.add(proxy);
            }
        }

        return proxies;
    }

    public static List<Proxy> proxySellerCom() throws Exception {
        List<Proxy> proxies = new ArrayList<>();
        String port = "65233";

        BufferedReader rd = new BufferedReader(new InputStreamReader(ProxyList.class.getResourceAsStream("/proxy_seller_com")));
        String line = "";
        while ((line = rd.readLine()) != null) {
            Proxy proxy = new Proxy();
            proxy.setIpAdress(line.substring(0, line.indexOf("#")));
            proxy.setPort(port);
            proxy.setCountry(line.substring(line.indexOf("#") + 1));
            proxy.setProvider("https://proxy-seller.ru/");
            proxies.add(proxy);
        }

        return proxies;
    }

    public static void main(String[] args) throws Exception {
        List<Proxy> proxies = ProxyList.proxySellerCom();
        proxies.forEach((proxy) -> {
            System.out.println(proxy.getIpAdress() + ":" + proxy.getPort() + "#" + proxy.getCountry());
        });
    }
}
