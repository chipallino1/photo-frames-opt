package com.ramkiopt.main.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Users {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String role;
    private String passwordEncrypted;
    private Collection<Cart> cartsById;
    private Collection<PhotoFrames> photoFramesById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "first_name", nullable = true, length = 300)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = true, length = 300)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 300)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone_number", nullable = true, length = 20)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "role", nullable = true, length = 300)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "password_encrypted", nullable = true, length = 400)
    public String getPasswordEncrypted() {
        return passwordEncrypted;
    }

    public void setPasswordEncrypted(String passwordEncrypted) {
        this.passwordEncrypted = passwordEncrypted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return id == users.id &&
                Objects.equals(firstName, users.firstName) &&
                Objects.equals(lastName, users.lastName) &&
                Objects.equals(email, users.email) &&
                Objects.equals(phoneNumber, users.phoneNumber) &&
                Objects.equals(role, users.role) &&
                Objects.equals(passwordEncrypted, users.passwordEncrypted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, phoneNumber, role, passwordEncrypted);
    }

    @OneToMany(mappedBy = "usersByClientId")
    public Collection<Cart> getCartsById() {
        return cartsById;
    }

    public void setCartsById(Collection<Cart> cartsById) {
        this.cartsById = cartsById;
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<PhotoFrames> getPhotoFramesById() {
        return photoFramesById;
    }

    public void setPhotoFramesById(Collection<PhotoFrames> photoFramesById) {
        this.photoFramesById = photoFramesById;
    }
}
