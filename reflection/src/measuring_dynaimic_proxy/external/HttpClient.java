package measuring_dynaimic_proxy.external;

public interface HttpClient {

    void initialize();

    String sendRequest(String request);
}
