package com.fmc.fixmycity.post.commentreply;

import com.fmc.fixmycity.post.comment.Comment;
import com.fmc.fixmycity.post.comment.reply.Reply;

import java.util.List;
import java.util.Objects;

public class CommentReply {
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
    private List<String> repliesID;
    private List<Reply> replies;
    private String userName;
    private String profileImage;
    private int hour;
    private int minute;
    private int second;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

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

    public List<String> getRepliesID() {
        return repliesID;
    }

    public void setRepliesID(List<String> repliesID) {
        this.repliesID = repliesID;
    }

    public List<Reply> getReplies() {
        return replies;
    }
    public void setReplies(List<Reply> replies) {
        this.replies = replies;
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
        CommentReply that = (CommentReply) o;
        return getDay() == that.getDay() && getMonth() == that.getMonth() && getYear() == that.getYear() && getHour() == that.getHour() && getMinute() == that.getMinute() && getSecond() == that.getSecond() && getCommentID().equals(that.getCommentID()) && getPostID().equals(that.getPostID()) && getEmail().equals(that.getEmail()) && getComment().equals(that.getComment()) && Objects.equals(getLikedBy(), that.getLikedBy()) && Objects.equals(getImageURL(), that.getImageURL()) && Objects.equals(getTime(), that.getTime()) && Objects.equals(getRepliesID(), that.getRepliesID()) && Objects.equals(getReplies(), that.getReplies()) && Objects.equals(getUserName(), that.getUserName()) && Objects.equals(getProfileImage(), that.getProfileImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCommentID(), getPostID(), getEmail(), getComment(), getLikedBy(), getImageURL(), getDay(), getMonth(), getYear(), getTime(), getRepliesID(), getReplies(), getUserName(), getProfileImage(), getHour(), getMinute(), getSecond());
    }

    @Override
    public String toString() {
        return "commentID : " + commentID +
                "postID : " + postID +
                "email : " + email +
                "userName : " + userName +
                "profileImage : " + profileImage +
                "comment : " + comment +
                "likedBy : " + likedBy +
                "imageURL : " + imageURL +
                "day : " + day +
                "month : " + month +
                "year : " + year +
                "hour : " + hour +
                "minute : " + minute +
                "second : " + second +
                "time : " + time +
                "repliesID : " + repliesID +
                "replies : " + replies;
    }
}
