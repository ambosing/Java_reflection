package study.jiwon.refectionexample;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.*;
import java.util.Arrays;

@SpringBootTest
class RefectionexampleApplicationTests2 {


    @Test
    @DisplayName("리플렉션으로 새로운 인스턴스 생성하기")
    void 리플렉션으로_새로운_인스턴스_생성하기() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class<?> aClass = Class.forName("study.jiwon.refectionexample.Book2");

        // 기본생성자
        Constructor<?> constructor = aClass.getConstructor();
        Book2 book2 = (Book2) constructor.newInstance();

        // String 타입 매개변수를 가지고 있는 생성자
        Constructor<?> constructor1 = aClass.getConstructor(String.class);
        Book2 book21 = (Book2) constructor1.newInstance("myBook");
        System.out.println(book21);

    }


    @Test
    @DisplayName("리플렉션으로 필드 가져오기")
    void 리플렉션으로_필드_가져오기() throws Exception{
        Class<?> aClass = Class.forName("study.jiwon.refectionexample.Book2");
        Constructor<?> constructor1 = aClass.getConstructor(String.class);
        Book2 book21 = (Book2) constructor1.newInstance("myBook");

        Field a = Book2.class.getDeclaredField("A");
        System.out.println(a.get(null));
        a.set(null, "AAAAAA");
        System.out.println(a.get(null));

        Field b = Book2.class.getDeclaredField("B");
        b.setAccessible(true);//private 이라 해줘야함
        System.out.println(b.get(book21));
        b.set(book21, "BBBBBB");
        System.out.println(b.get(book21));


    }

    @Test
    @DisplayName("리플렉션으로 메서드 호출하기")
    void 리플렉션으로_메서드_호출하기() throws Exception{
        Class<?> aClass = Class.forName("study.jiwon.refectionexample.Book2");
        Constructor<?> constructor1 = aClass.getConstructor(String.class);
        Book2 book21 = (Book2) constructor1.newInstance("myBook");

        Method c = Book2.class.getDeclaredMethod("c");
        c.invoke(book21);

        Method sum = Book2.class.getDeclaredMethod("sum", int.class, int.class);
        int invoke = (int) sum.invoke(book21, 1, 2);
        System.out.println(invoke);
    }
}
