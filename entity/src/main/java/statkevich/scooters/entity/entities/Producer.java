package statkevich.scooters.entity.entities;

import statkevich.scooters.entity.abstractEntities.EntityWithName;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "producer")
public class Producer extends EntityWithName {

    @OneToMany(mappedBy = "producer", fetch = FetchType.LAZY)
    private List<TypesProducers> typesProducers;

    public Producer() {
    }

    public Producer(String name) {
        super(name);
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
        return "Producer: " +
                super.toString();
    }
}
