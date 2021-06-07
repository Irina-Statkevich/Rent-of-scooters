package statkevich.scooters.entity.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "scooters")
public class Scooters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private BigDecimal sum;

    @ManyToOne
    @JoinColumn(nullable = false)//(name = "seller_id")
    private Sellers seller;

    @ManyToOne
    @JoinColumn(name = "types_producers_id",nullable = false)
    private TypesProducers typeProducer;

    @OneToMany(mappedBy = "scooter", fetch = FetchType.LAZY)
    private List<PriceList> priceLists;

    @OneToMany(mappedBy = "scooter", fetch = FetchType.LAZY)
    private List<Rent> rent;

    public Scooters() {
    }

    public Scooters(String model, BigDecimal sum, Sellers seller, TypesProducers typeProducer) {
        this.model = model;
        this.sum = sum;
        this.seller = seller;
        this.typeProducer = typeProducer;
        priceLists = new ArrayList<>();
        rent = new ArrayList<>();
    }

    public Long getNumber() {
        return number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Sellers getSeller() {
        return seller;
    }

    public void setSeller(Sellers seller) {
        this.seller = seller;
    }

    public TypesProducers getTypeProducer() {
        return typeProducer;
    }

    public void setTypeProducer(TypesProducers typeProducer) {
        this.typeProducer = typeProducer;
    }

    public List<PriceList> getPriceLists() {
        return priceLists;
    }

    public void setPriceLists(List<PriceList> priceLists) {
        this.priceLists = priceLists;
    }

    public List<Rent> getRent() {
        return rent;
    }

    public void setRent(List<Rent> rent) {
        this.rent = rent;
    }

    @Override
    public String toString() {
        return "Scooter: " +
                "number= " + number +
                ", model= " + model +
                ", seller= " + seller.getName() +
                ", id type-producer= " + typeProducer.toString() +
                " ,сумма= " + sum;
    }
}
