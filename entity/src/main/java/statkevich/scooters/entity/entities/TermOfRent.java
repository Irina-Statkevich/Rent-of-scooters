package statkevich.scooters.entity.entities;

import statkevich.scooters.entity.abstractEntities.EntityWithTitle;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "term_of_rent"
        ,uniqueConstraints = {@UniqueConstraint(columnNames = {"title","count_of_days"},name = "term_uniq")}
)
public class TermOfRent extends EntityWithTitle {

    @Column(name = "count_of_days",nullable = false)
    private int countOfDays;

    @OneToMany(mappedBy = "termOfRent", fetch = FetchType.LAZY)
    private List<PriceList> priceList;

    public TermOfRent() {
    }

    public TermOfRent(String title, int countOfDays) {
        super(title);
        this.countOfDays = countOfDays;
    }

    public int getCountOfDays() {
        return countOfDays;
    }

    public void setCountOfDays(int countOfDays) {
        this.countOfDays = countOfDays;
    }

    public List<PriceList> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<PriceList> priceLists) {
        this.priceList = priceLists;
    }

    @Override
    public String toString() {
        return "Term of rent: " +
                super.toString() +
                ", count of days= " + countOfDays;
    }
}
