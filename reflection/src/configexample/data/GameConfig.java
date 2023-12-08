package configexample.data;

import java.util.Random;

public class GameConfig {
    // 요구 사항. releaseYear 값이 없으면 2000, 있으면 구성 파일 값으로 하고 싶음
//    private final int releaseYear = 2000; // 이렇게 설정하면 구성파일에서 값을 불러와도 값이 변경되지 않음
    private final int releaseYear;
    private String gameName;
    private double price;
    

    // 아래와 같이 하면 final인 키워드가 있어도 문제 없이 값이 초기화 되지만, 가장 좋은 해결 방법은 아님
    // 나중에 final 필드를 변경하는 법 배운다고 함.
    public GameConfig() {
        Random random = new Random();
        this.releaseYear = random.nextInt(2000);
    }

    public int getReleaseYear() {
        return this.releaseYear;
    }

    public String getGameName() {
        return this.gameName;
    }

    public double getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return "GameConfig{" +
                "releaseYear=" + releaseYear +
                ", gameName='" + gameName + '\'' +
                ", price=" + price +
                '}';
    }
}

