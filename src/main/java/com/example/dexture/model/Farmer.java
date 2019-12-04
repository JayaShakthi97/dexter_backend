package com.example.dexture.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Farmer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int farmerId;

    private String firstName;

    private String lastName;

    @NotBlank
    private String email;

    private String nic;

    private String contactNo;

    private String personalAddress;

    private String gramaNiladariDivision;

    @NotBlank
    private String password;

    private boolean accepted = false;

    @OneToMany(mappedBy = "farmer")
    @JsonIgnore
    private Set<Land> Lands;

    public Farmer() {
    }

    public int getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(int farmerId) {
        this.farmerId = farmerId;
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

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getPersonalAddress() {
        return personalAddress;
    }

    public void setPersonalAddress(String personalAddress) {
        this.personalAddress = personalAddress;
    }

    public String getGramaNiladariDivision() {
        return gramaNiladariDivision;
    }

    public void setGramaNiladariDivision(String gramaNiladariDivision) {
        this.gramaNiladariDivision = gramaNiladariDivision;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public Set<Land> getLands() {
        return Lands;
    }

    public void setLands(Set<Land> lands) {
        Lands = lands;
    }
}
