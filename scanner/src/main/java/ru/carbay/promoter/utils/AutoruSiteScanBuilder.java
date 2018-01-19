package ru.carbay.promoter.utils;

import ru.carbay.promoter.model.ds.SiteScan;

import java.util.ArrayList;
import java.util.List;

public class AutoruSiteScanBuilder {

    private static final String autoruTemplateUrl =
            "https://auto.ru/%city%/cars/%brand%/%model%/new/?beaten=1&customs_state=1&geo_radius=100&image=true&in_stock=true&sort_offers=fresh_relevance_1-DESC&top_days=off&currency=RUR&output_type=list&official_dealer=true&page_num_offers=%page_number%";

    public static SiteScan build(String city, String brand, String model) {
        List<String> pageUrls = new ArrayList<>();
        String url = autoruTemplateUrl
                .replace("%city%", urlCity(city))
                .replace("%brand%", urlBrand(brand))
                .replace("%model%", urlModel(model));
        pageUrls.add(url.replace("%page_number%", "1"));

        SiteScan siteScan = new SiteScan();
        siteScan.setSite("Auto.ru");
        siteScan.setCity(city);
        siteScan.setBrand(brand);
        siteScan.setModel(model);
        siteScan.setPageUrls(pageUrls);

        return siteScan;
    }

    public static String pageUrl_next(String url) {
        Integer pageNum = Integer.valueOf(url.substring(url.indexOf("page_num_offers=") + 16));
        return url.substring(0, url.indexOf("page_num_offers=") + 16) + (pageNum + 1);
    }

    public static String pageUrl_byPage(String url, int page) {
        return url.substring(0, url.indexOf("page_num_offers=") + 16) + page;
    }

    private static String urlCity(String city) {
        return transliterate(city.toLowerCase());
    }

    private static String urlBrand(String brand) {
        if (brand.equalsIgnoreCase("lada")) {
            return "vaz";
        }

        if (brand.equalsIgnoreCase("газ")) {
            return "gaz";
        }

        if (brand.equalsIgnoreCase("уаз")) {
            return "uaz";
        }

        if (brand.equalsIgnoreCase("mercedes-benz")) {
            return "mercedes";
        }

        return brand.toLowerCase().replaceAll(" ", "_");
    }

    private static String urlModel(String model) {
        return model.toLowerCase().replaceAll(" ", "_").replaceAll("-", "_");
    }

    private static String transliterate(String message){
        char[] abcCyr =   {' ','а','б','в','г','д','е','ё', 'ж','з','и','й','к','л','м','н','о','п','р','с','т','у','ф','х', 'ц','ч', 'ш','щ','ъ','ы','ь','э', 'ю','я','А','Б','В','Г','Д','Е','Ё', 'Ж','З','И','Й','К','Л','М','Н','О','П','Р','С','Т','У','Ф','Х', 'Ц', 'Ч','Ш', 'Щ','Ъ','Ы','Ь','Э','Ю','Я','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        String[] abcLat = {"_","a","b","v","g","d","e","e","zh","z","i","y","k","l","m","n","o","p","r","s","t","u","f","h","ts","ch","sh","sch", "","i", "","e","ju","ja","A","B","V","G","D","E","E","Zh","Z","I","Y","K","L","M","N","O","P","R","S","T","U","F","H","Ts","Ch","Sh","Sch", "","I", "","E","Ju","Ja","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            for(int x = 0; x < abcCyr.length; x++ )
                if (message.charAt(i) == abcCyr[x]) {
                    builder.append(abcLat[x]);
                }
        }
        return builder.toString();

    }
}
