package com.invest.khumbu.Model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
    
    private String fullname;
    private String emailMobile;
    private String password;
    private String phoneNumber;
    private String watsappNumber;
    private String idBackImageUrl;
    private String idFrontImageUrl;
    private String idNumber;
    @Lob
    @Column(name = "proof_of_address", columnDefinition = "LONGBLOB")
    private byte[] proofOfAddress;

    @Enumerated(EnumType.STRING)
    private Language preferredLanguage;

    @Enumerated(EnumType.STRING)
    private Tier tier;
    

    @Temporal(TemporalType.DATE)
    private Date date;

 
    public User() {
    }
    

    public User(String emailMobile, String fullname, String idBackImageUrl, String idFrontImageUrl, String idNumber, String password, String phoneNumber, Language preferredLanguage, byte[] proofOfAddress, Tier tier, String watsappNumber) {
        this.emailMobile = emailMobile;
        this.fullname = fullname;
        this.idBackImageUrl = idBackImageUrl;
        this.idFrontImageUrl = idFrontImageUrl;
        this.idNumber = idNumber;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.preferredLanguage = preferredLanguage;
        this.proofOfAddress = proofOfAddress;
        this.tier = tier;
        this.watsappNumber = watsappNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWatsappNumber() {
        return watsappNumber;
    }

    public void setWatsappNumber(String watsappNumber) {
        this.watsappNumber = watsappNumber;
    }

    public String getIdBackImageUrl() {
        return idBackImageUrl;
    }

    public void setIdBackImageUrl(String idBackImageUrl) {
        this.idBackImageUrl = idBackImageUrl;
    }

    public String getIdFrontImageUrl() {
        return idFrontImageUrl;
    }

    public void setIdFrontImageUrl(String idFrontImageUrl) {
        this.idFrontImageUrl = idFrontImageUrl;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public byte[] getProofOfAddress() {
        return proofOfAddress;
    }

    public void setProofOfAddress(byte[] proofOfAddress) {
        this.proofOfAddress = proofOfAddress;
    }

    public Language getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(Language preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public Tier getTier() {
        return tier;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }
       public enum Tier {
        SAVING, WEALTH, INVESTMENT
    }
    public enum Language {
        EN, ZU, XH, TS, TN, ST, NS, VE, AF
    }
    public enum KycStatus {
        PENDING, VERIFIED, REJECTED
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return fullname;
    }

    public void setName(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return emailMobile;
    }

    public void setEmail(String emailMobile) {
        this.emailMobile = emailMobile;
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