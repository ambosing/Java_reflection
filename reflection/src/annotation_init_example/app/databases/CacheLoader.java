package annotation_init_example.app.databases;

import annotation_init_example.annotations.InitializerClass;
import annotation_init_example.annotations.InitializerMethod;

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
