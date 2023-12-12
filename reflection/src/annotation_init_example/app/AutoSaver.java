package annotation_init_example.app;

import annotation_init_example.annotations.InitializerClass;
import annotation_init_example.annotations.InitializerMethod;

@InitializerClass
public class AutoSaver {

    @InitializerMethod
    public void startAutoSavingThreads() {
        System.out.println("Start automatic data save to disk");
    }
}
