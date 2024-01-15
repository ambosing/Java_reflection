package study.jiwon.springdi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {
    @Autowired BookService bookService;

    @Test
    @DisplayName("di")
    void di() {
        // 어떻게 Null이 아닌걸까
        assertNotNull(bookService);
        assertNotNull(bookService.bookRepository);
    }
}