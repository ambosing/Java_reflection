package configexample2.practice;

import java.lang.reflect.Array;

public class Main {

    public static void main(String[] args) {
        int[] result = concat(int.class, 1, 2, 3, new int[]{4, 5, 6}, 7);
        for (int i : result) {
            System.out.println("i = " + i);
        }
    }

    public static <T> T concat(Class<?> type, Object... arguments) {

        if (arguments.length == 0) {
            return null;
        }

        int count = 0;
        for (Object argument : arguments) {
            if (argument.getClass().isArray()) {
                int length = Array.getLength(argument);
                count += length;
            } else {
                count++;
            }
        }
        Object arrayObject = Array.newInstance(type, count);
        int i = 0;
        for (Object argument : arguments) {
            if (argument.getClass().isArray()) {
                int length = Array.getLength(argument);
                for (int j = 0; j < length; j++) {
                    Array.set(arrayObject, i++, Array.get(argument, j));
                }
            } else {
                Array.set(arrayObject, i++, argument);
            }
        }
        return (T) arrayObject;
    }
}
