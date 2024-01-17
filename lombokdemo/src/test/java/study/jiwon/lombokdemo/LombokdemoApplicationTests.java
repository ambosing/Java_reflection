package study.jiwon.lombokdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LombokdemoApplicationTests {

    @Test
    void contextLoads() {
        Moja moja = new MojaFactory();
        System.out.println(moja.pullOut());
    }

}
