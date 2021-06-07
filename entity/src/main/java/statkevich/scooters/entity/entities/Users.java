package statkevich.scooters.entity.entities;

import statkevich.scooters.entity.abstractEntities.EntityWithName;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class Users extends EntityWithName {

    @Column(name = "phone_number",nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String pass;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Roles role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Rent> rent;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Payment> payment;

    public Users() {
    }

    public Users(String name) {
        super(name);
    }

    public Users(String name, String phoneNumber, String pass) {
        super(name);
        this.phoneNumber = phoneNumber;
        this.pass = pass;
        rent = new ArrayList<>();
        payment = new ArrayList<>();
    }

    public Users(String name, String phoneNumber, String pass,Roles role) {
        super(name);
        this.phoneNumber = phoneNumber;
        this.pass = pass;
        this.role=role;
        rent = new ArrayList<>();
        payment = new ArrayList<>();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public List<Rent> getRent() {
        return rent;
    }

    public void setRent(List<Rent> rent) {
        this.rent = rent;
    }

    public List<Payment> getPayment() {
        return payment;
    }

    public void setPayment(List<Payment> payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "User: " +
                super.toString() +
                ", phone number= " + phoneNumber +
                ", pass= " + pass;
    }
}
