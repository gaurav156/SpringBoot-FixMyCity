package com.fmc.fixmycity.user.report;

import java.util.Date;
import java.util.Objects;

public class Report {
    private String reportID;
    private String email;
    private String firstName;
    private String lastName;
    private String profileImage;
    private String report;
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

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getReportID() {
        return reportID;
    }

    public void setReportID(String reportID) {
        this.reportID = reportID;
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
        Report report1 = (Report) o;
        return getDay() == report1.getDay() && getMonth() == report1.getMonth() && getYear() == report1.getYear() && getHour() == report1.getHour() && getMinute() == report1.getMinute() && getSecond() == report1.getSecond() && Objects.equals(getReportID(), report1.getReportID()) && getEmail().equals(report1.getEmail()) && Objects.equals(getFirstName(), report1.getFirstName()) && Objects.equals(getLastName(), report1.getLastName()) && Objects.equals(getProfileImage(), report1.getProfileImage()) && getReport().equals(report1.getReport()) && Objects.equals(getDate(), report1.getDate()) && Objects.equals(getTime(), report1.getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReportID(), getEmail(), getFirstName(), getLastName(), getProfileImage(),
                getReport(), getDay(), getMonth(), getYear(), getHour(), getMinute(), getSecond(), getDate(), getTime());
    }

    @Override
    public String toString() {
        return "reportID : " + reportID +
                "email : " + email +
                "firstName : " + firstName +
                "lastName : " + lastName +
                "profileImage : " + profileImage +
                "report : " + report +
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
