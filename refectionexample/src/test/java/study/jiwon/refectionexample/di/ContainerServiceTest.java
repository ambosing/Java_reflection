package study.jiwon.refectionexample.di;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerServiceTest {

    @Test
    @DisplayName("getObject")
    void getObject() {
        BookRepository bookRepository = ContainerService.getObjcet(BookRepository.class);

        assertNotNull(bookRepository);
    }

    @Test
    @DisplayName("getObject BookService")
    void getObject_BookService() {
        BookService bookService = ContainerService.getObjcet(BookService.class);
        assertNotNull(bookService);
        assertNotNull(bookService.bookRepository);
    }

}