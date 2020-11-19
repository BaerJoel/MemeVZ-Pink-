package com.example.memevz;


import android.media.Image;

public class Meme {
    private int imgId, likes, dislikes;
    private String imgBlob, decription;
    private User user;
    private Image img;

    public Meme(int imgId) {
        this.imgId = imgId;
    }

    public Meme(Image img, String description, User user) {
        this.img = img;
        this.decription = description;
        this.user = user;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public Image getImg() {
        return this.img;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getImgBlob() {
        return imgBlob;
    }

    public void setImgBlob(String imgBlob) {
        this.imgBlob = imgBlob;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean uploadImg(String imgBlob, String description, User user) {
        return true;
    }


}
