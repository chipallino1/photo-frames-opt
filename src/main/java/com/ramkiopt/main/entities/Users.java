package com.ramkiopt.main.entities;

import com.ramkiopt.main.services.app.base.RowStatus;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Users {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String role;
    private String passwordEncrypted;
    private RowStatus status;
    private Collection<Cart> cartsById;
    private Collection<PhotoFrames> photoFramesById;
    private Collection<Orders> ordersById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "first_name", nullable = true, length = 300)
    @Size(min = 2, max = 100)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = true, length = 300)
    @Size(min = 2, max = 100)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 300, unique = true)
    @Size(min = 2, max = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone_number", nullable = true, length = 20, unique = true)
    @Size(min = 2, max = 20)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "role", nullable = true, length = 300)
    @Size(min = 2, max = 20)
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

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    public RowStatus getStatus() {
        return status;
    }

    public void setStatus(RowStatus status) {
        this.status = status;
    }

    @OneToMany(mappedBy = "usersByClientId")
    public Collection<Cart> getCartsById() {
        return cartsById;
    }

    public void setCartsById(Collection<Cart> cartsById) {
        this.cartsById = cartsById;
    }

    @OneToMany(mappedBy = "usersByUserId", cascade = CascadeType.PERSIST)
    public Collection<PhotoFrames> getPhotoFramesById() {
        return photoFramesById;
    }

    public void setPhotoFramesById(Collection<PhotoFrames> photoFramesById) {
        this.photoFramesById = photoFramesById;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Users users = (Users) object;
        return Objects.equals(id, users.id) &&
                Objects.equals(firstName, users.firstName) &&
                Objects.equals(lastName, users.lastName) &&
                Objects.equals(email, users.email) &&
                Objects.equals(phoneNumber, users.phoneNumber) &&
                Objects.equals(role, users.role) &&
                Objects.equals(passwordEncrypted, users.passwordEncrypted) &&
                Objects.equals(cartsById, users.cartsById) &&
                Objects.equals(photoFramesById, users.photoFramesById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, phoneNumber, role, passwordEncrypted, cartsById, photoFramesById);
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<Orders> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<Orders> ordersById) {
        this.ordersById = ordersById;
    }
}
