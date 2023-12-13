package annotation_retry.app;

import annotation_retry.annotations.InitializerClass;
import annotation_retry.annotations.InitializerMethod;

@InitializerClass
public class AutoSaver {

    @InitializerMethod
    public void startAutoSavingThreads() {
        System.out.println("Start automatic data save to disk");
    }
}
