package com.example.memevz;

import org.junit.Test;

import static org.junit.Assert.*;


public class UserUnitTest {
    @Test
    public void user_isCorrect() {
        User user = new User();
        user.seteMail("info@memevz.de");
        user.setUsername("memelord");
        user.setPassword("12345");
        user.setUserId(1);
        assertEquals("info@memevz.de", user.geteMail());
        assertEquals("memelord", user.getUsername());
        assertEquals("12345", user.getPassword());
        assertEquals(1, user.getUserId());
        assertTrue(user.isPasswordCorrect("12345"));
        assertFalse(user.isPasswordCorrect("123456"));
    }
}