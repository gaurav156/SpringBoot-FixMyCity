package com.fmc.fixmycity.post.comment;

import java.util.List;
import java.util.Objects;

public class Comment {
    private String commentID;
    private String postID;
    private String email;
    private String comment;
    private List<String> likedBy;
    private String imageURL;
    private int day;
    private int month;
    private int year;
    private String time;
    private int hour;
    private int minute;
    private int second;

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(List<String> likedBy) {
        this.likedBy = likedBy;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment1 = (Comment) o;
        return getDay() == comment1.getDay() && getMonth() == comment1.getMonth() && getYear() == comment1.getYear() && getHour() == comment1.getHour() && getMinute() == comment1.getMinute() && getSecond() == comment1.getSecond() && getCommentID().equals(comment1.getCommentID()) && getPostID().equals(comment1.getPostID()) && getEmail().equals(comment1.getEmail()) && getComment().equals(comment1.getComment()) && Objects.equals(getLikedBy(), comment1.getLikedBy()) && Objects.equals(getImageURL(), comment1.getImageURL()) && Objects.equals(getTime(), comment1.getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCommentID(), getPostID(), getEmail(), getComment(), getLikedBy(), getImageURL(), getDay(), getMonth(), getYear(), getTime(), getHour(), getMinute(), getSecond());
    }

    @Override
    public String toString() {
        return "commentID='" + commentID +
                "postID : " + postID +
                "email : " + email +
                "comment : " + comment +
                "likedBy : " + likedBy +
                "imageURL : " + imageURL +
                "day : " + day +
                "month : " + month +
                "year : " + year +
                "hour : " + hour +
                "minute : " + minute +
                "second : " + second +
                "time : " + time ;
    }
}
