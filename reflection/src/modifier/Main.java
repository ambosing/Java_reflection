package modifier;

import modifier.auction.Auction;
import modifier.auction.Bid;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        printFieldModifiers(Auction.class.getDeclaredFields());
    }

    public static void printFieldModifiers(Field[] fields) {
        for (Field field : fields) {
            int modifiers = field.getModifiers();

            System.out.printf("%s 접근제어는 %s%n",
                    field.getName(),
                    getAccessModifierName(modifiers));

            if (Modifier.isVolatile(modifiers)) {
                System.out.println("이 필드는 volatile");
            }
            if (Modifier.isFinal(modifiers)) {
                System.out.println("이 필드는 final");
            }
            if (Modifier.isTransient(modifiers)) {
                System.out.println("이 필드는 transient이고 직렬화되지 않습니다.");
            }
            System.out.println();
        }
    }

    public static void printMethodModifiers(Method[] methods) {
        for (Method method : methods) {
            int modifiers = method.getModifiers();

            System.out.printf("%s 접근제어는 %s%n",
                    method.getName(),
                    getAccessModifierName(modifiers));
            if (Modifier.isSynchronized(modifiers)) {
                System.out.println("이 메서드는 synchronized");
            } else {
                System.out.println("이 메서드는 not synchronized");
            }
            System.out.println();
        }
    }

    public static void printClassModifiers(Class<?> clazz) {
        int modifiers = clazz.getModifiers();

        System.out.printf("클래스 %s 접근 제어자는 %s%n",
                clazz.getSimpleName(),
                getAccessModifierName(modifiers));

        if (Modifier.isAbstract(modifiers)) {
            System.out.println("이 클래스는 abstract");
        }

        if (Modifier.isInterface(modifiers)) {
            System.out.println("이 클래스는 인터페이스");
        }

        if (Modifier.isStatic(modifiers)) {
            System.out.println("The class is static");
        }
    }

    private static String getAccessModifierName(int modifier) {
        if (Modifier.isPublic(modifier)) {
            return "public";
        } else if (Modifier.isPrivate(modifier)) {
            return "private";
        } else if (Modifier.isProtected(modifier)) {
            return "protected";
        } else {
            return "package-private";
        }
    }

    public static void runAuction() {
        Auction auction = new Auction();
        auction.startAuction();

        Bid bid1 = Bid.builder()
                .setBidderName("Company1")
                .setPrice(10)
                .build();
        auction.addBid(bid1);

        Bid bid2 = Bid.builder()
                .setBidderName("Company2")
                .setPrice(12)
                .build();
        auction.addBid(bid2);

        auction.stopAuction();

        System.out.println(auction.getAllBids());
        System.out.println("Highest bid :" + auction.getHighestBid().get());
    }
}
