package gettermethod.test;

import gettermethod.api.Product;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

public class ProductTest {
    public static void main(String[] args) {
        testGetters(Product.class);
    }

    public static void testGetters(Class<?> dataClass) {
        Field[] fields = dataClass.getDeclaredFields();

        Map<String, Method> methodNameToMethod = mapMethodNameToMethod(dataClass);

        for (Field field : fields) {
            String getterName = "get" + capitalizeFirstLetter(field.getName());

            if (!methodNameToMethod.containsKey(getterName)) {
                throw new IllegalStateException(format("필드 : %s는 getter 메서드를 가지고 있지 않습니다.", field.getName()));
            }

            Method getter = methodNameToMethod.get(getterName);

            if (!getter.getReturnType().equals(field.getType())) {
                throw new IllegalStateException(
                        format("Getter 메서드 : %s()는 리턴 타입 %s 인데 %s 반환했습니다.",
                                getter.getName(),
                                getter.getReturnType().getTypeName(),
                                field.getType().getTypeName()));
            }

            if (getter.getParameterCount() > 0) {
                throw new IllegalStateException(format("Getter : %s는 %d 인자를 가지고 있습니다.", getterName, getter.getParameterCount()));
            }

        }
    }

    private static String capitalizeFirstLetter(String fieldName) {
        return fieldName.substring(0, 1).toUpperCase().concat(fieldName.substring(1));
    }

    private static Map<String, Method> mapMethodNameToMethod(Class<?> dataClass) {
        Method[] allMethods = dataClass.getMethods();

        Map<String, Method> nameToMethod = new HashMap<>();

        for (Method method : allMethods) {
            nameToMethod.put(method.getName(), method);
        }

        return nameToMethod;
    }
}
