package statkevich.scooters.entity.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private BigDecimal sum;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Users user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rent_id", referencedColumnName = "id")
    private Rent rent;

    public Payment() {
    }

    public Payment(BigDecimal sum, Users user) {
        this.sum = sum;
        this.user = user;
    }

    public Payment(BigDecimal sum, Users user, Rent rent) {
        this.sum = sum;
        this.user = user;
        this.rent = rent;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Rent getRent() {
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    @Override
    public String toString() {
        return "Payment: " +
                ", id= " + id +
                ", sum= " + sum +
                ", from user= " + user +
                ", rent's id= " + rent;
    }
}
