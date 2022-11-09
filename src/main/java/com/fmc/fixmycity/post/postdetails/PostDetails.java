package com.fmc.fixmycity.post.postdetails;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PostDetails {
    private String postID;
    private String email;
    private String firstName;
    private String lastName;
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
    private List<String> commentIDs;
    private String profileImage;
    private String userType;

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

    public List<String> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(List<String> likedBy) {
        this.likedBy = likedBy;
    }

    public List<String> getCommentIDs() {
        return commentIDs;
    }

    public void setCommentIDs(List<String> commentIDs) {
        this.commentIDs = commentIDs;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
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
        PostDetails that = (PostDetails) o;
        return getDay() == that.getDay() && getMonth() == that.getMonth() && getYear() == that.getYear() && getHour() == that.getHour() && getMinute() == that.getMinute() && getSecond() == that.getSecond() && getPostID().equals(that.getPostID()) && getEmail().equals(that.getEmail()) && getFirstName().equals(that.getFirstName()) && Objects.equals(getLastName(), that.getLastName()) && Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getStreet_address(), that.getStreet_address()) && Objects.equals(getArea(), that.getArea()) && Objects.equals(getCity(), that.getCity()) && Objects.equals(getPostcode(), that.getPostcode()) && Objects.equals(getAdditionalDetails(), that.getAdditionalDetails()) && Objects.equals(getLat(), that.getLat()) && Objects.equals(getLng(), that.getLng()) && getImageURL().equals(that.getImageURL()) && getType().equals(that.getType()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getDate(), that.getDate()) && Objects.equals(getTime(), that.getTime()) && Objects.equals(getStatus(), that.getStatus()) && Objects.equals(getResolvedDate(), that.getResolvedDate()) && Objects.equals(getLikedBy(), that.getLikedBy()) && Objects.equals(getCommentIDs(), that.getCommentIDs()) && Objects.equals(getProfileImage(), that.getProfileImage()) && Objects.equals(getUserType(), that.getUserType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPostID(), getEmail(), getFirstName(), getLastName(), getAddress(), getStreet_address(), getArea(), getCity(), getPostcode(), getAdditionalDetails(), getLat(), getLng(), getImageURL(), getType(), getDescription(), getDay(), getMonth(), getYear(), getHour(), getMinute(), getSecond(), getDate(), getTime(), getStatus(), getResolvedDate(), getLikedBy(), getCommentIDs(), getProfileImage(), getUserType());
    }

    @Override
    public String toString() {
        return "postID : " + postID +
                "email : " + email +
                "firstName : " + firstName +
                "lastName : " + lastName +
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
                "likedBy : " + likedBy +
                "commentIDs : " + commentIDs +
                "profileImage : " + profileImage +
                "userType : " + userType ;
    }
}
