package com.ramkiopt.main.dto;

import com.ramkiopt.main.services.app.base.RowStatus;

import java.io.Serializable;

public class UsersDto implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String role;
    private String password;
    private String passwordEncrypted;
    private RowStatus rowStatus;

    public UsersDto() {
    }

    public UsersDto(Long id, String firstName, String lastName, String email, String phoneNumber,
                    String role, String passwordEncrypted, RowStatus rowStatus) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.passwordEncrypted = passwordEncrypted;
        this.rowStatus = rowStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordEncrypted() {
        return passwordEncrypted;
    }

    public void setPasswordEncrypted(String passwordEncrypted) {
        this.passwordEncrypted = passwordEncrypted;
    }

    public RowStatus getRowStatus() {
        return rowStatus;
    }

    public void setRowStatus(RowStatus rowStatus) {
        this.rowStatus = rowStatus;
    }
}
