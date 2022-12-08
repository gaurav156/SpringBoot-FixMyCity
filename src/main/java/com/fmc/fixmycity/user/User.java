package com.fmc.fixmycity.user;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class User {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String userType;
    private String city;
    private String postcode;
    private String contactNo;
    private String profileImage;
    private List<String> assignedPostcode;

    public List<String> getAssignedPostcode() {
        return assignedPostcode;
    }

    public void setAssignedPostcode(List<String> assignedPostcode) {
        this.assignedPostcode = assignedPostcode;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
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

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getEmail().equals(user.getEmail()) && getFirstName().equals(user.getFirstName()) && Objects.equals(getLastName(), user.getLastName()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getUserType(), user.getUserType()) && Objects.equals(getCity(), user.getCity()) && Objects.equals(getPostcode(), user.getPostcode()) && Objects.equals(getContactNo(), user.getContactNo()) && Objects.equals(getProfileImage(), user.getProfileImage()) && Objects.equals(getAssignedPostcode(), user.getAssignedPostcode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getFirstName(), getLastName(), getPassword(), getUserType(), getCity(), getPostcode(), getContactNo(), getProfileImage(), getAssignedPostcode());
    }

    @Override
    public String toString() {
        return "email : " + email +
                "firstName : " + firstName +
                "lastName : " + lastName +
                "password : " + password +
                "userType : " + userType +
                "city : " + city +
                "postcode : " + postcode +
                "contactNo : " + contactNo +
                "profileImage : " + profileImage +
                "assignedPostcode : " + assignedPostcode;
    }
}
