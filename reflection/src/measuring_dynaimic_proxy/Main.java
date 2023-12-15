package measuring_dynaimic_proxy;

import measuring_dynaimic_proxy.external.DatabaseReader;
import measuring_dynaimic_proxy.external.HttpClient;
import measuring_dynaimic_proxy.external.impl.DatabaseReaderImpl;
import measuring_dynaimic_proxy.external.impl.HttpClientImpl;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        HttpClient httpClient = createProxy(new HttpClientImpl());
        DatabaseReader databaseReader = createProxy(new DatabaseReaderImpl());

//        useHttpClient(httpClient);
        useDatabaseReader(databaseReader);
    }

    public static void useHttpClient(HttpClient httpClient) {
        httpClient.initialize();
        String response = httpClient.sendRequest("some request");

        System.out.println(String.format("Http response is : %s", response));
    }

    public static void useDatabaseReader(DatabaseReader databaseReader) throws InterruptedException {
        int rowsInGamesTable = 0;
        try {
            rowsInGamesTable = databaseReader.countRowsInTable("GamesTable");
        } catch (IOException e) {
            System.out.println("Catching exception " + e);
            return;
        }

        System.out.printf("There are %s rows in GamesTable%n", rowsInGamesTable);

        String[] data = databaseReader.readRow("SELECT * from GamesTable");

        System.out.printf("Received %s%n", String.join(" , ", data));
    }

    @SuppressWarnings("unchecked")
    public static <T> T createProxy(Object originalObject) {
        Class<?>[] interfaces = originalObject.getClass().getInterfaces();

        TimeMeasuringProxyHandler timeMeasuringProxyHandler = new TimeMeasuringProxyHandler(originalObject);

        return (T) Proxy.newProxyInstance(originalObject.getClass().getClassLoader(), interfaces, timeMeasuringProxyHandler);
    }

    public static class TimeMeasuringProxyHandler implements InvocationHandler {
        private final Object originalObject;

        public TimeMeasuringProxyHandler(Object originalObject) {
            this.originalObject = originalObject;
        }


        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object result;

            System.out.printf("측정 프록시 - 메서드 실행 전 : %s()%n", method.getName());

            long startTime = System.currentTimeMillis();
            try {
                result = method.invoke(originalObject, args);
            } catch (InvocationTargetException e) {
                throw e.getTargetException();
            }
            long endTime = System.currentTimeMillis();

            System.out.println();
            System.out.printf("측정 프록시 - 메서드 %s() %dms 만큼 걸렸음!%n", method.getName(), endTime - startTime);

            return result;
        }
    }
}
