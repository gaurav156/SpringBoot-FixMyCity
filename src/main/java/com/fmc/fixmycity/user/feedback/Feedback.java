package com.fmc.fixmycity.user.feedback;

import java.util.Date;
import java.util.Objects;

public class Feedback {
    private String feedbackID;
    private String email;
    private String firstName;
    private String lastName;
    private String profileImage;
    private String feedback;
    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;
    private int second;
    private Date date;
    private String time;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(String feedbackID) {
        this.feedbackID = feedbackID;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback1 = (Feedback) o;
        return getDay() == feedback1.getDay() && getMonth() == feedback1.getMonth() && getYear() == feedback1.getYear() && getHour() == feedback1.getHour() && getMinute() == feedback1.getMinute() && getSecond() == feedback1.getSecond() && Objects.equals(getFeedbackID(), feedback1.getFeedbackID()) && getEmail().equals(feedback1.getEmail()) && Objects.equals(getFirstName(), feedback1.getFirstName()) && Objects.equals(getLastName(), feedback1.getLastName()) && Objects.equals(getProfileImage(), feedback1.getProfileImage()) && getFeedback().equals(feedback1.getFeedback()) && Objects.equals(getDate(), feedback1.getDate()) && Objects.equals(getTime(), feedback1.getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFeedbackID(), getEmail(), getFirstName(), getLastName(), getProfileImage(),
                getFeedback(), getDay(), getMonth(), getYear(), getHour(), getMinute(), getSecond(), getDate(), getTime());
    }

    @Override
    public String toString() {
        return "feedbackID : " + feedbackID +
                "email : " + email +
                "firstName : " + firstName +
                "lastName : " + lastName +
                "profileImage : " + profileImage +
                "feedback : " + feedback +
                "day : " + day +
                "month : " + month +
                "year : " + year +
                "hour : " + hour +
                "minute : " + minute +
                "second : " + second +
                "time : " + time +
                "date : " + date ;
    }
}
