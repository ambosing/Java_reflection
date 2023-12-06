package restricted.practice.internal;

public class Main {
    public static void main(String[] args) throws Throwable {
        Object argument = new byte[700];
        ImageBuffer object = ReflectiveFactory.createObject(ImageBuffer.class, argument);
        System.out.println(object);
        System.out.println(object.getBuffer().length);
    }
}
