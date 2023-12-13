package annotation_retry.app.configs;


import annotation_retry.annotations.InitializerClass;
import annotation_retry.annotations.InitializerMethod;

@InitializerClass
public class ConfigsLoader {

    @InitializerMethod
    public void loadAllConfigs() {
        System.out.println("Loading all configuration files");
    }
}
