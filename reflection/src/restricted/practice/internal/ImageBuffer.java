package restricted.practice.internal;

public class ImageBuffer {
    private static final int MAX_SIZE = 500;
    private final byte[] buffer;

    public ImageBuffer(Integer size) {
        System.out.println("매개변수 하나 있는 생성자");
        this.buffer = new byte[size];
    }

    ImageBuffer(byte[] buffer) {
        this.buffer = buffer;
    }

    private ImageBuffer() {
        System.out.println("매개변수 없는 생성자");
        this.buffer = new byte[MAX_SIZE];
    }

    public byte[] getBuffer() {
        return buffer;
    }
}