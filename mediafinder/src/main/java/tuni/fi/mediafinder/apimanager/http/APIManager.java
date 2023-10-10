package tuni.fi.mediafinder.apimanager.http;

import tuni.fi.mediafinder.apimanager.http.APIClient;
import tuni.fi.mediafinder.apimanager.mapping.APINamespace;

public class APIManager {
    public static String querySearchString(String searchString, int pageNumber, APINamespace namespace) {
        String url = namespace.getBaseUrl() + namespace.getSearchStringQueryKey()
                + "=" + searchString + "&" + namespace.getPageQuery(pageNumber);
        String response = "";
        System.out.println(url);
        try {
            response = APIClient.get(url, namespace.getAuthenticationToken());
        } catch (Exception error) {
            System.out.println(error);
        }
        return response;
    }
}
