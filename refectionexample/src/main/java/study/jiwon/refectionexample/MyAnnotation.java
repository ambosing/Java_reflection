package study.jiwon.refectionexample;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Inherited // 상속이 되는 어노테이션
public @interface MyAnnotation {

    String value() default "value"; // 하나만 쓸 때는 @MyAnnotation("value2") 이렇게 가능
    // 그렇지 않고 여러개 쓰면 @MyAnnotation(value = "value3", number = 10)

//    String name() default "jiwon";

//    int number() default 100;
}
