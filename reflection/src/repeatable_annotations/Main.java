package repeatable_annotations;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static repeatable_annotations.annotations.Annotations.*;

@ScanPackages({"loaders"})
public class Main {

    public static void main(String[] args) throws URISyntaxException, IOException, ClassNotFoundException {
        schedule();
    }

    public static void schedule() throws URISyntaxException, IOException, ClassNotFoundException {
        ScanPackages scanPackages = Main.class.getAnnotation(ScanPackages.class);
        if (scanPackages == null || scanPackages.value().length == 0) {
            return;
        }

        List<Class<?>> allClasses = getAllClasses(scanPackages.value());
        List<Method> scheduledExecutorMethods = getScheduledExecutorMethods(allClasses);

        for (Method method : scheduledExecutorMethods) {
            scheduleMethodExecution(method);
        }
    }

    private static void scheduleMethodExecution(Method method) {
        ExecuteOnSchedule[] schedules = method.getAnnotationsByType(ExecuteOnSchedule.class);

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        for (ExecuteOnSchedule schedule : schedules) {
            scheduledExecutorService.scheduleAtFixedRate(
                    () -> runWhenScheduled(method),
                    schedule.delaySeconds(),
                    schedule.periodSeconds(),
                    TimeUnit.SECONDS
            );
        }
    }

    private static void runWhenScheduled(Method method) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        System.out.printf("Executing at %s%n", dateFormat.format(currentDate));

        try {
            method.invoke(null);
        } catch (IllegalAccessException | InvocationTargetException exception) {
            exception.printStackTrace();
        }
    }

    private static List<Method> getScheduledExecutorMethods(List<Class<?>> allClasses) {
        List<Method> scheduledMethods = new ArrayList<>();

        for (Class<?> clazz : allClasses) {
            if (!clazz.isAnnotationPresent(ScheduledExecutorClass.class)) {
                continue;
            }
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.getAnnotationsByType(ExecuteOnSchedule.class).length != 0) {
                    scheduledMethods.add(method);
                }
            }
        }
        return scheduledMethods;
    }

    public static List<Class<?>> getAllClasses(String... packageNames) throws URISyntaxException, IOException, ClassNotFoundException {
        List<Class<?>> allClasses = new ArrayList<>();

        for (String packageName : packageNames) {
            String packageRelativePath = packageName.replace('.', '/');

            URI packageUri = Main.class.getResource(packageRelativePath).toURI();

            if (packageUri.getScheme().equals("file")) {
                Path packageFullPath = Paths.get(packageUri);
                allClasses.addAll(getAllPackageClasses(packageFullPath, packageName));
            } else if (packageUri.getScheme().equals("jar")) {
                FileSystem fileSystem = FileSystems.newFileSystem(packageUri, Collections.emptyMap());

                Path packageFullPathInJar = fileSystem.getPath(packageRelativePath);
                allClasses.addAll(getAllPackageClasses(packageFullPathInJar, packageName));

                fileSystem.close();
            }
        }
        return allClasses;
    }

    private static List<Class<?>> getAllPackageClasses(Path packagePath, String packageName) throws IOException, ClassNotFoundException {

        if (!Files.exists(packagePath)) {
            return Collections.emptyList();
        }

        List<Path> files = Files.list(packagePath)
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());

        List<Class<?>> classes = new ArrayList<>();

        for (Path filePath : files) {
            String fileName = filePath.getFileName().toString();

            if (fileName.endsWith(".class")) {
                String classFullName = packageName.isBlank()
                        ? fileName.replaceFirst("\\.class$", "")
                        : "repeatable_annotations." + packageName + "." + fileName.replaceFirst("\\.class$", "");

                Class<?> clazz = Class.forName(classFullName);
                classes.add(clazz);
            }
        }
        return classes;
    }
}
