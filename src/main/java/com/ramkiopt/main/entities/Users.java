package com.ramkiopt.main.entities;

import com.ramkiopt.main.services.app.base.RowStatus;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Users {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String passwordEncrypted;
    private RowStatus status;
    private Collection<Carts> cartsById;
    private Collection<Orders> ordersById;
    private Collection<PhotoFrames> photoFramesById;
    private Collection<UsersOnRoles> usersOnRolesById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "password_encrypted")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(id, users.id) &&
                Objects.equals(firstName, users.firstName) &&
                Objects.equals(lastName, users.lastName) &&
                Objects.equals(email, users.email) &&
                Objects.equals(phoneNumber, users.phoneNumber) &&
                Objects.equals(passwordEncrypted, users.passwordEncrypted) &&
                Objects.equals(status, users.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, phoneNumber, passwordEncrypted, status);
    }

    @OneToMany(mappedBy = "usersByClientId")
    public Collection<Carts> getCartsById() {
        return cartsById;
    }

    public void setCartsById(Collection<Carts> cartsById) {
        this.cartsById = cartsById;
    }

    @OneToMany(mappedBy = "usersByClientId")
    public Collection<Orders> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<Orders> ordersById) {
        this.ordersById = ordersById;
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<PhotoFrames> getPhotoFramesById() {
        return photoFramesById;
    }

    public void setPhotoFramesById(Collection<PhotoFrames> photoFramesById) {
        this.photoFramesById = photoFramesById;
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<UsersOnRoles> getUsersOnRolesById() {
        return usersOnRolesById;
    }

    public void setUsersOnRolesById(Collection<UsersOnRoles> usersOnRolesById) {
        this.usersOnRolesById = usersOnRolesById;
    }
}
