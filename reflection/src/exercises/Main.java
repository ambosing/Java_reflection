package exercises;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<String> stringClass = String.class;

        Map<String, Integer> mapObject = new HashMap<>();

        Class<?> hashMapClass = mapObject.getClass();

        Class<?> squareClass = Class.forName("exercises.Main$Square");

//        printClassInfo(stringClass, hashMapClass, squareClass);

        var drawable = new Drawable() {

            @Override
            public int getNumberOfCorners() {
                return 0;
            }
        };

        printClassInfo(Collection.class, boolean.class, int[][].class, Color.class, drawable.getClass());
    }

    private static void printClassInfo(Class<?>... classes) {
        for (Class<?> clazz : classes) {
            System.out.printf("clazz.getSimpleName() = %s, clazz.getPackageName() = %s \n",
                    clazz.getSimpleName(),
                    clazz.getPackageName());

            Class<?>[] interfaces = clazz.getInterfaces();
            for (Class<?> anInterface : interfaces) {
                System.out.printf("clazz.getSimpleName() = %s, implements() = %s \n",
                        clazz.getSimpleName(),
                        anInterface.getSimpleName());
            }
            System.out.println("Is Array : " + clazz.isArray());
            System.out.println("Is Primitive : " + clazz.isPrimitive());
            System.out.println("Is enum : " + clazz.isEnum());
            System.out.println("Is Interface : " + clazz.isInterface());
            System.out.println("Is Anonymous : " + clazz.isAnonymousClass());
            System.out.println("clazz.getSuperclass() = " + clazz.getSuperclass());

            Class<?> superclass = clazz.getSuperclass();

            if (superclass != null) {
                System.out.println("superclass.getSimpleName() = " + superclass.getSimpleName());
            }

            System.out.println();
            System.out.println();
        }
    }

    private static class Square implements Drawable {

        @Override
        public int getNumberOfCorners() {
            return 4;
        }
    }

    private static interface  Drawable {
        int getNumberOfCorners();
    }

    private enum Color {
        BLUE,
        RED,
        GREEN
    }
}
