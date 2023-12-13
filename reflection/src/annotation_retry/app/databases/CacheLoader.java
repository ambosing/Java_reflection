package annotation_retry.app.databases;

import annotation_retry.annotations.InitializerClass;
import annotation_retry.annotations.InitializerMethod;

@InitializerClass
public class CacheLoader {

    @InitializerMethod
    public void loadCache() {
        System.out.println("Loading data from cache");
    }

    @InitializerMethod
    public void reloadCache() {
        System.out.println("Reload cache");
    }
}
