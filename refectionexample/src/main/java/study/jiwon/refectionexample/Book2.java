package study.jiwon.refectionexample;


public class Book2 {
    public static String A = "A";

    private String B = "B";

    public Book2() {
    }

    public Book2(String b) {
        B = b;
    }

    public void c() {
        System.out.println("c");
    }

    public int sum(int a, int b) {
        return a + b;
    }

    @Override
    public String toString() {
        return "Book2{" +
                "B='" + B + '\'' +
                '}';
    }
}
