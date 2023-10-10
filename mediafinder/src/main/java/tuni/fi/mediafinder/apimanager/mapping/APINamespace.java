package tuni.fi.mediafinder.apimanager.mapping;

public abstract class APINamespace {

    public abstract String getBaseUrl();
    public abstract String getAuthenticationToken();
    public abstract String getSearchStringQueryKey();
    public abstract String getPageQuery(int pageNumber);
}
