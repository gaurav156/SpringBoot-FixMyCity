package com.fmc.fixmycity.post.comment.reply;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Reply {
    private String replyID;
    private String commentID;
    private String email;
    private String reply;
    private List<String> likedBy;
    private String imageURL;
    private int day;
    private int month;
    private int year;
    private String time;
    private String firstName;
    private String lastName;
    private String profileImage;
    private int hour;
    private int minute;
    private int second;
    private String userType;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getReplyID() {
        return replyID;
    }

    public void setReplyID(String replyID) {
        this.replyID = replyID;
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reply reply1 = (Reply) o;
        return getDay() == reply1.getDay() && getMonth() == reply1.getMonth() && getYear() == reply1.getYear() && getHour() == reply1.getHour() && getMinute() == reply1.getMinute() && getSecond() == reply1.getSecond() && getReplyID().equals(reply1.getReplyID()) && getCommentID().equals(reply1.getCommentID()) && getEmail().equals(reply1.getEmail()) && getReply().equals(reply1.getReply()) && Objects.equals(getLikedBy(), reply1.getLikedBy()) && Objects.equals(getImageURL(), reply1.getImageURL()) && Objects.equals(getTime(), reply1.getTime()) && Objects.equals(getFirstName(), reply1.getFirstName()) && Objects.equals(getLastName(), reply1.getLastName()) && Objects.equals(getProfileImage(), reply1.getProfileImage()) && Objects.equals(getUserType(), reply1.getUserType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReplyID(), getCommentID(), getEmail(), getReply(), getLikedBy(), getImageURL(), getDay(), getMonth(), getYear(), getTime(), getFirstName(), getLastName(), getProfileImage(), getHour(), getMinute(), getSecond(), getUserType());
    }

    @Override
    public String toString() {
        return "replyID : " + replyID +
                "commentID : " + commentID +
                "email : " + email +
                "firstName : " + firstName +
                "lastName : " + lastName +
                "profileImage : " + profileImage +
                "reply : " + reply +
                "likedBy : " + likedBy +
                "imageURL : " + imageURL +
                "day : " + day +
                "month : " + month +
                "year : " + year +
                "hour : " + hour +
                "minute : " + minute +
                "second : " + second +
                "time : " + time +
                "userType : " + userType;
    }
}
