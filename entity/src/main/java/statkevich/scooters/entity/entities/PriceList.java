package statkevich.scooters.entity.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "price_list")
public class PriceList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "scooter_number",nullable = false)
    private Scooters scooter;

    @ManyToOne
    @JoinColumn(name = "term_id",nullable = false)
    private TermOfRent termOfRent;

    @OneToMany(mappedBy = "price", fetch = FetchType.LAZY)
    private List<Rent> rent;

    public PriceList() {
    }

    public PriceList(Scooters scooter, TermOfRent termOfRent) {
        this.scooter = scooter;
        this.termOfRent = termOfRent;
        rent = new ArrayList<>();
    }

    public PriceList(BigDecimal price, Scooters scooter, TermOfRent termOfRent) {
        this.price = price;
        this.scooter = scooter;
        this.termOfRent = termOfRent;
        rent = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Scooters getScooter() {
        return scooter;
    }

    public void setScooter(Scooters scooter) {
        this.scooter = scooter;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public TermOfRent getTermOfRent() {
        return termOfRent;
    }

    public void setTermOfRent(TermOfRent termOfRent) {
        this.termOfRent = termOfRent;
    }

    public List<Rent> getRent() {
        return rent;
    }

    public void setRent(List<Rent> rent) {
        this.rent = rent;
    }

    @Override
    public String toString() {
        return "Price: " +
                "id= " + id +
                ", scooter= " + scooter.getModel() +
                ", term of rent= " + termOfRent.getTitle() +
                " price= " + price;
    }
}
