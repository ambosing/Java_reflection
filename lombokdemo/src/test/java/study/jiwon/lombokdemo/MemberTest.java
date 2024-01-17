package study.jiwon.lombokdemo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    @DisplayName("getterSetter")
    void getterSetter() {
        Member member = new Member();
        member.setName("jiwon");

        assertEquals(member.getName(), "jiwon");
    }
}