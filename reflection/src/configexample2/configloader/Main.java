package configexample2.configloader;

import configexample2.data.GameConfig;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    private static final Path GAME_CONFIG_PATH = Path.of("resources/game-properties2.cfg");
    private static final Path UI_CONFIG_PATH = Path.of("resources/user-interface2.cfg");

    public static void main(String[] args) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        GameConfig config = createConfigObject(GameConfig.class, GAME_CONFIG_PATH);
//        UserInterfaceConfig config = createConfigObject(UserInterfaceConfig.class, UI_CONFIG_PATH);

        System.out.println(config);
    }

    public static <T> T createConfigObject(Class<T> clazz, Path filePath) throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Scanner scanner = new Scanner(filePath);

        Constructor<T> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);

        T configInstance = constructor.newInstance();

        while (scanner.hasNext()) {
            String configLine = scanner.nextLine();

            String[] nameValuePair = configLine.split("=");
            String propertyName = nameValuePair[0];
            String propertyValue = nameValuePair[1];

            Field field;
            try {
                field = clazz.getDeclaredField(propertyName);
            } catch (NoSuchFieldException e) {
                System.out.printf("프로퍼티 이름 : %s 는 지원하지 않습니다.%n", propertyName);
                continue;
            }

            field.setAccessible(true);

            Object parsedValue;

            if (field.getType().isArray()) {
                parsedValue = parseArray(field.getType().getComponentType(), propertyValue);
            } else {
                parsedValue = parseValue(field.getType(), propertyValue);
            }

            field.set(configInstance, parsedValue);
        }

        return configInstance;
    }

    private static Object parseArray(Class<?> arrayElementType, String value) {
        String[] elementValues = value.split(",");

        Object arrayObject = Array.newInstance(arrayElementType, elementValues.length);

        for (int i = 0; i < elementValues.length; i++) {
            Array.set(arrayObject, i, parseValue(arrayElementType, elementValues[i]));
        }

        return arrayObject;
    }

    private static Object parseValue(Class<?> type, String value) {
        if (type.equals(int.class)) {
            return Integer.parseInt(value);
        } else if (type.equals(short.class)) {
            return Short.parseShort(value);
        } else if (type.equals(long.class)) {
            return Long.parseLong(value);
        } else if (type.equals(double.class)) {
            return Double.parseDouble(value);
        } else if (type.equals(float.class)) {
            return Float.parseFloat(value);
        } else if (type.equals(String.class)) {
            return value;
        }
        throw new RuntimeException(String.format("Type : %s unsupported", type.getTypeName()));
    }

}
