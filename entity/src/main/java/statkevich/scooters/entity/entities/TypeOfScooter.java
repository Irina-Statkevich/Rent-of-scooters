package statkevich.scooters.entity.entities;

import statkevich.scooters.entity.abstractEntities.EntityWithTitle;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "type_of_scooter")
public class TypeOfScooter extends EntityWithTitle {

    @OneToMany(mappedBy = "scootersType", fetch = FetchType.LAZY)
    private List<TypesProducers> typesProducers;

    public TypeOfScooter() {
    }

    public TypeOfScooter(String title) {
        super(title);
        typesProducers = new ArrayList<>();
    }

    public List<TypesProducers> getTypesProducers() {
        return typesProducers;
    }

    public void setTypesProducers(List<TypesProducers> typesProducers) {
        this.typesProducers = typesProducers;
    }

    @Override
    public String toString() {
        return "Type of scooter: " +
                super.toString();
    }
}
