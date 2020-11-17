package com.example.memevz;

public class Image {
    private int imgId, likes, dislikes;
    private String imgBlob, decription;
    private User user;

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

    public int getImg() {
        return R.drawable.dislike;
    }

}
