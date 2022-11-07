package com.fmc.fixmycity.post;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Post {
    private String postID;
    private String email;
    private String userName;
    private String address;
    private String street_address;
    private String area;
    private String city;
    private String postcode;
    private String additionalDetails;
    private String lat;
    private String lng;
    private String imageURL;
    private String type;
    private String description;
    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;
    private int second;
    private Date date;
    private String time;
    private String status;
    private String resolvedDate;

    private List<String> likedBy;

    public List<String> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(List<String> likedBy) {
        this.likedBy = likedBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResolvedDate() {
        return resolvedDate;
    }

    public void setResolvedDate(String resolvedDate) {
        this.resolvedDate = resolvedDate;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getAdditionalDetails() {
        return additionalDetails;
    }

    public void setAdditionalDetails(String additionalDetails) {
        this.additionalDetails = additionalDetails;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return getDay() == post.getDay() && getMonth() == post.getMonth() && getYear() == post.getYear() && getHour() == post.getHour() && getMinute() == post.getMinute() && getSecond() == post.getSecond() && getPostID().equals(post.getPostID()) && getEmail().equals(post.getEmail()) && Objects.equals(getUserName(), post.getUserName()) && Objects.equals(getAddress(), post.getAddress()) && Objects.equals(getStreet_address(), post.getStreet_address()) && Objects.equals(getArea(), post.getArea()) && Objects.equals(getCity(), post.getCity()) && Objects.equals(getPostcode(), post.getPostcode()) && Objects.equals(getAdditionalDetails(), post.getAdditionalDetails()) && Objects.equals(getLat(), post.getLat()) && Objects.equals(getLng(), post.getLng()) && Objects.equals(getImageURL(), post.getImageURL()) && getType().equals(post.getType()) && Objects.equals(getDescription(), post.getDescription()) && Objects.equals(getDate(), post.getDate()) && Objects.equals(getTime(), post.getTime()) && Objects.equals(getStatus(), post.getStatus()) && Objects.equals(getResolvedDate(), post.getResolvedDate()) && Objects.equals(getLikedBy(), post.getLikedBy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPostID(), getEmail(), getUserName(), getAddress(), getStreet_address(), getArea(), getCity(), getPostcode(), getAdditionalDetails(), getLat(), getLng(), getImageURL(), getType(), getDescription(), getDay(), getMonth(), getYear(), getHour(), getMinute(), getSecond(), getDate(), getTime(), getStatus(), getResolvedDate(), getLikedBy());
    }

    @Override
    public String toString() {
        return "postID : " + postID +
                "email : " + email +
                "userName : " + userName +
                "address : " + address +
                "street_address : " + street_address +
                "area : " + area +
                "city : " + city +
                "postcode : " + postcode +
                "additionalDetails : " + additionalDetails +
                "lat : " + lat +
                "lng : " + lng +
                "imageURL : " + imageURL +
                "type : " + type +
                "description : " + description +
                "day : " + day +
                "month : " + month +
                "year : " + year +
                "hour : " + hour +
                "minute : " + minute +
                "second : " + second +
                "time : " + time +
                "date : " + date +
                "status : " + status +
                "resolvedDate : " + resolvedDate +
                "likedBy : " + likedBy;
    }
}
