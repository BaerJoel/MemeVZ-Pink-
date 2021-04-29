package com.example.memevz;

import org.junit.*;

import static org.junit.Assert.*;

public class MemeUnitTest {
    @Test
    public void meme_isCorrect() {
        Meme meme = new Meme(1);
        meme.setDislikes(3);
        meme.setLikes(23);
        User user = new User(1);
        user.setPassword("1234");
        user.setUsername("helikopter");
        user.seteMail("fly@high.com");
        meme.setUser(user);
        meme.setImgBlob("HK4O0W");
        assertEquals(1, meme.getImgId());
        assertEquals(3, meme.getDislikes());
        assertEquals(23, meme.getLikes());
        assertEquals("HK4O0W", meme.getImgBlob());
        assertEquals(1, meme.getUser().getUserId());
        assertEquals("1234", meme.getUser().getPassword());
        assertEquals("helikopter", meme.getUser().getUsername());
        assertEquals("fly@high.com", meme.getUser().geteMail());


    }
}
