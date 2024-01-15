package study.jiwon.refectionexample;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Modifier;
import java.util.Arrays;

@SpringBootTest
class RefectionexampleApplicationTests {


    @Test
    @DisplayName("클래스 가져오기")
    void 클래스_가져오기() throws ClassNotFoundException {
        Class<Book> bookClass = Book.class;

        Book book = new Book();
        Class<? extends Book> aClass = book.getClass();

        Class<?> aClass1 = Class.forName("study.jiwon.refectionexample.Book");
    }

    @Test
    @DisplayName("필드 가져오기")
    void 필드_가져오기() {
        Class<Book> bookClass = Book.class;
        Book book = new Book();

        Arrays.stream(bookClass.getFields()).forEach(System.out::println); // public 한것만 가져옴


        Arrays.stream(bookClass.getDeclaredFields()).forEach(System.out::println); // 모든 필드를 다 가져옴

        Arrays.stream(bookClass.getDeclaredFields()).forEach(field -> {
            try {
                field.setAccessible(true); // private, protected 접근 제어자들도 가져옴, 없을 때 private, protected 가져올 때 에러 발생
                System.out.printf("%s %s\n", field, field.get(book));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    @DisplayName("필드의 접근제어자 확인")
    void 필드의_접근제어자_확인() {
        Class<Book> bookClass = Book.class;

        Arrays.stream(bookClass.getDeclaredFields()).forEach(field -> {
            int modifiers = field.getModifiers();
            System.out.println(field);
            System.out.println(Modifier.isPrivate(modifiers));
            System.out.println(Modifier.isProtected(modifiers));
            System.out.println(Modifier.isStatic(modifiers));
        });
    }

    @Test
    @DisplayName("메서드 가져오기")
    void 메서드_가져오기() {
        Class<Book> bookClass = Book.class;

        Arrays.stream(bookClass.getMethods()).forEach(System.out::println);
    }

    @Test
    @DisplayName("메서드의 반환값과 접근제어자 파라미터 리플렉션으로 알아보기")
    void 메서드의_반환값과_접근제어자_파라미터_리플렉션으로_알아보기() {
        Class<Book> bookClass = Book.class;

        Arrays.stream(bookClass.getMethods()).forEach(method -> {
            int modifiers = method.getModifiers();
            Class<?> returnType = method.getReturnType();
            Class<?>[] parameterTypes = method.getParameterTypes();
            System.out.println(Modifier.isStatic(modifiers));
            System.out.println(Modifier.isProtected(modifiers));
            System.out.println(Modifier.isPrivate(modifiers));
            System.out.println(returnType);
            Arrays.stream(parameterTypes).forEach(System.out::println);
        });
    }

    @Test
    @DisplayName("생성자 가져오기")
    void 생성자_가져오기() {
        Class<Book> bookClass = Book.class;

        Arrays.stream(bookClass.getConstructors()).forEach(System.out::println); // public 만
        Arrays.stream(bookClass.getDeclaredConstructors()).forEach(System.out::println); // private 등도 모두 다
        Arrays.stream(MyBook.class.getDeclaredConstructors()).forEach(System.out::println); // 상위클래스만
    }

    @Test
    @DisplayName("인터페이스 가져오기")
    void 인터페이스_가져오기() {
        Class<Book> bookClass = Book.class;

        Arrays.stream(bookClass.getInterfaces()).forEach(System.out::println);
    }


    @Test
    @DisplayName("어노테이션 가져오기")
    void 어노테이션_가져오기() {
        Class<Book> bookClass = Book.class;

        Arrays.stream(bookClass.getAnnotations()).forEach(System.out::println);
    }
}
