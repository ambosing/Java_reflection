package annotation_init_example.app.configs;


import annotation_init_example.annotations.InitializerClass;
import annotation_init_example.annotations.InitializerMethod;

@InitializerClass
public class ConfigsLoader {

    @InitializerMethod
    public void loadAllConfigs() {
        System.out.println("Loading all configuration files");
    }
}
