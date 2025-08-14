package com.invest.khumbu.Model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "user_id")
    private Long userId;
    
    private String name;
    private String surname;
    private String email;
    private String mobile_number;
    private String id_number;
    private String password;

    @Temporal(TemporalType.DATE)
    private Date date;

    public User() {
    }

    public User(Date date, String email, String id_number, String mobile_number, String name, String password, String surname) {
        this.date = date;
        this.email = email;
        this.id_number = id_number;
        this.mobile_number = mobile_number;
        this.name = name;
        this.password = password;
        this.surname = surname;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobile_number;
    }

    public void setMobileNumber(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getIdNumber() {
        return id_number;
    }

    public void setIdNumber(String id_number) {
        this.id_number = id_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}