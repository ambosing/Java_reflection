package study.jiwon.refectionexample;

@MyAnnotation
public class Book implements MyInterface{
    private String a;

    private static String B = "Book";

    private static final String C = "Book";

    public String d = "d";

    protected String e = "e";

    public Book() {
    }

    private Book(String a) {
        this.a = a;
    }

    public Book(String a, String d, String e) {
        this.a = a;
        this.d = d;
        this.e = e;
    }

    private void f() {
        System.out.println("F");
    }

    public void g() {
        System.out.println("g");
    }

    public Integer h() {
        return 100;
    }
}
